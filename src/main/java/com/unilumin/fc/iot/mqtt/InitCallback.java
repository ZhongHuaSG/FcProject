package com.unilumin.fc.iot.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * MQTT回调函数
 *
 * @author ZhongWei
 * @since 2020/12/4
 */
@Slf4j
@Component
public class InitCallback implements MqttCallback {

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
    /*try {
      JSONObject jsonObject = JSON.parseObject(msg);
      String clientId = String.valueOf(jsonObject.get("clientid"));
      if (topic.endsWith("/disconnected")) {
        log.info("客户端已掉线：{}", clientId);
      } else {
        log.info("客户端已上线：{}", clientId);
      }
    } catch (JSONException e) {
      log.error("JSON Format Parsing Exception : {}", msg);
    }*/
  }
}
