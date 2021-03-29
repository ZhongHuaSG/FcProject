package com.unilumin.fc.iot.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.unilumin.fc.iot.code.FinalTopicConst;
import com.unilumin.fc.iot.model.Display;
import com.unilumin.fc.iot.model.Weather;
import com.unilumin.fc.iot.service.DeviceService;
import com.unilumin.fc.iot.service.RedisService;
import com.unilumin.fc.iot.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * MQTT回调函数
 *
 * @author ZhongWei
 * @since 2020/12/4
 */
@Slf4j
@Component
public class InitCallback implements MqttCallback  {

  private final String clientId = "FC_Unilumin_Server" + (int) (Math.random() * 100000000);
  private FinalTopicConst topicConst;
  @Autowired
  private WeatherService weatherService;
  @Resource
  RedisService redisService;
  @Resource
  MQTTConnect mqttConnect;
  @Resource
  DeviceService deviceService;

  private MqttClient client;
  private MqttConnectOptions options;

  //处理设备ID的service
  public InitCallback() {
  }

  public InitCallback(MqttClient client, MqttConnectOptions options) {
    this.client = client;
    this.options = options;
  }

  /**
   * MQTT 断开连接会执行此方法
   */
  @Override
  public void connectionLost(Throwable cause) {
    log.info("失去连接");
    log.error(cause.getMessage(), cause);
    //失败重连逻辑
    while (true){
      try {
        System.out.println("连接失败重连");
        client.connect(options);
        //发布相关的订阅
        String[] topic = {"msg.topic","dance.topic"};
        int[] qos = {1,1};
        client.subscribe(topic, qos);
        System.out.println("连接失败重连成功");
        break;
      } catch (MqttException e) {
        e.printStackTrace();
        System.out.println("连接失败重连失败");
      }
    }
  }

  /**
   * publish发布成功后会执行到这里
   */
  @Override
  public void deliveryComplete(IMqttDeliveryToken token) {
    log.info("好像发送成功了");
    log.info(token.toString());
  }

  /**
   * subscribe订阅后得到的消息会执行到这里
   */
  @Override
  public void messageArrived(String topic, MqttMessage message) {
    log.info("[{}] : {}", topic, new String(message.getPayload()));
    try {
      JSONObject jsonObject = JSON.parseObject(message.toString());
      String clientId = String.valueOf(jsonObject.get("clientid"));
      if (topic.endsWith(topicConst.DISCONNECTED)) {
        log.info("客户端已掉线：{}", clientId);
      } else {
        log.info("客户端已上线：{}", clientId);
      }
    } catch (JSONException e) {
      log.error("JSON Format Parsing Exception : {}", message);
    }
    if(topic.equals(topicConst.GOODS)){
      // 从缓存中写入信息
      String key = "topic_" + topic;
      Object getKey = redisService.get(key);
      if(getKey!=""||getKey!=null){
        log.info("redis获取的值{}",getKey);
      }
      log.info("消息送达{}，{}", topic, message);
      //拆解MQTT返回的信息
      byte[] payload = message.getPayload();
      JSONObject jsonObject = JSONObject.parseObject(new String(payload));
      Weather weather=new Weather();
      weather.setTemperature(jsonObject.getInteger("temperature"));
      weather.setHumidity(jsonObject.getFloat("humidity"));
      weatherService.save(weather);
      try{
        String msg = "Mr.Z" + (int) (Math.random() * 100000000);
        mqttConnect.sub("com/iot/init",1);
        mqttConnect.pub("$delayed/15/CURRENT_WORKTIME", msg);
      }catch (MqttException e){
        log.error("mqtt订阅broker错误", e);
      }
      redisService.set(key,topic+"消息送达:"+jsonObject.toString());
    }else if(topic.equals(topicConst.GET_DEVICE_ID)){
      log.info("当前机器ID收到{}，{}", topic, message);
      //拆解MQTT返回的信息
      byte[] payload = message.getPayload();
      JSONObject jsonObject = JSONObject.parseObject(new String(payload));
      JSONObject msg = jsonObject.getJSONObject("msg");
      Display display = new Display();

      List<Display> deviceList = new ArrayList<Display>();

      if(msg.get("ID")!=null&&msg.get("ID")!=""){
        display.setMachineCode(msg.get("ID").toString());
        deviceList = deviceService.selectByCondition(display);
      }
      if(msg.get("STATUS")!=null&&msg.get("STATUS")!=""){
        display.setStatus(msg.getInteger("STATUS"));
      }
      //将设备存入数据库
      //写入一个关于设备的业务-将设备ID存储起来
      Integer count = 0;
      if(deviceList.size() == 0){
        count =  deviceService.insert(display);
      }
      /**
       * 发布给设备的response
       * */
      try{
        if(msg.get("ID")!=null&&msg.get("ID")!=""){
          String responseMsg;
          if(count > 0){
            responseMsg = "\"msg\":{\"ID\":\""+msg.get("ID")+"\",\"code\":\"200\",\"message\":\"success\"}" ;
          }else{
            responseMsg = "\"msg\":{\"ID\":\""+msg.get("ID")+"\",\"code\":\"500\",\"message\":\"is Existed;\"}" ;
          }
          mqttConnect.pub("FC/"+msg.get("ID").toString()+"/setting", responseMsg);
        }else{
          log.error("mqtt设备ID为空");
        }
      }catch (MqttException e){
        log.error("mqtt订阅broker错误", e);
      }
    }
  }
}
