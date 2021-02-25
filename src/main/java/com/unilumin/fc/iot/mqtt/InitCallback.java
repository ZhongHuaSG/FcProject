package com.unilumin.fc.iot.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.unilumin.fc.iot.code.FinalTopicConst;
import com.unilumin.fc.iot.model.Weather;
import com.unilumin.fc.iot.service.RedisService;
import com.unilumin.fc.iot.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MQTT回调函数
 *
 * @author ZhongWei
 * @since 2020/12/4
 */
@Slf4j
@Component
public class InitCallback implements MqttCallback {

  private FinalTopicConst topicConst;
  @Autowired
  private WeatherService weatherService;
  @Resource
  RedisService redisService;
  @Resource
  MQTTConnect mqttConnect;

  //处理设备ID的service


  /**
   * MQTT 断开连接会执行此方法
   */
  @Override
  public void connectionLost(Throwable cause) {
    log.error(cause.getMessage(), cause);
  }

  /**
   * publish发布成功后会执行到这里
   */
  @Override
  public void deliveryComplete(IMqttDeliveryToken token) {
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
    }
  }
}
