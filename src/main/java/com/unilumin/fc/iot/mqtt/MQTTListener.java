package com.unilumin.fc.iot.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目启动 监听主题
 *
 * @author ZhongWei
 * @since 2020/12/4 0018
 */
@Slf4j
@Component
public class MQTTListener implements ApplicationListener<ContextRefreshedEvent> {

  @Value("${mqtt.username}")
  private String username;
  @Value("${mqtt.password}")
  private String password;
  private final MQTTConnect server;
  private final InitCallback initCallback;

  @Autowired
  public MQTTListener(MQTTConnect server, InitCallback initCallback) {
    this.server = server;
    this.initCallback = initCallback;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    try {
      server.setMqttClient(username, password, initCallback);
      server.sub("COMMON_MODPARAS");
      server.sub("COMMON_RECPARAS");
      server.sub("CONTRAST");
      server.sub("CURRENT_WORKTIME");
      server.sub("goods");
    } catch (MqttException e) {
      log.error(e.getMessage(), e);
    }
  }
}


