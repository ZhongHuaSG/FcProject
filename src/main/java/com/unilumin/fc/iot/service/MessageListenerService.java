//package com.unilumin.fc.iot.service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.unilumin.fc.iot.model.Weather;
//import com.unilumin.fc.iot.mqtt.MQTTConnect;
//import lombok.extern.slf4j.Slf4j;
//import org.eclipse.paho.client.mqttv3.*;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Slf4j
//@Service
//public class MessageListenerService {
//    @Value("${broker.ip:127.0.0.1}")
//    private String brokerIp;
//    @Value("${subscribe.topic:goods}")
//    private String topic;
//    @Autowired
//    private WeatherService weatherService;
//    @Resource
//    RedisService redisService;
//    @Resource
//    MQTTConnect mqttConnect;
//
//    @PostConstruct
//    public void ini() {
//        try {
//            String host = InetAddress.getLocalHost().getHostAddress();
//            MqttClient client = new MqttClient("tcp://" + brokerIp + ":1883", host + System.currentTimeMillis(), new MemoryPersistence());
//            MqttConnectOptions options = new MqttConnectOptions();
//            options.setCleanSession(true);
//            options.setConnectionTimeout(10);
//            options.setKeepAliveInterval(15);
//            options.setUserName("userName");
//            options.setPassword("userName".toCharArray());
//            client.setCallback(new MqttCallback() {
//                @Override
//                public void connectionLost(Throwable cause) {
//                    log.warn("连接中断", cause);
//                }
//
//                @Override
//                public void messageArrived(String topic, MqttMessage message) {
//                    // 从缓存中写入信息
//                    String key = "topic_" + topic;
//                    Object getKey = redisService.get(key);
//                    if(getKey!=""||getKey!=null){
//                        log.info("redis获取的值{}",getKey);
//                    }
//                    log.info("消息送达{}，{}", topic, message);
//                    byte[] payload = message.getPayload();
//                    JSONObject jsonObject = JSONObject.parseObject(new String(payload));
//                    Weather weather=new Weather();
//                    weather.setTemperature(jsonObject.getInteger("temperature"));
//                    weather.setHumidity(jsonObject.getFloat("humidity"));
//                    weatherService.save(weather);
//                    try{
//                        String msg = "Mr.Qu" + (int) (Math.random() * 100000000);
//                        mqttConnect.sub("com/iot/init",1);
//                        mqttConnect.pub("$delayed/15/CURRENT_WORKTIME", msg);
//                    }catch (MqttException e){
//                        log.error("mqtt订阅broker错误", e);
//                    }
//                    redisService.set(key,topic+"消息送达:"+jsonObject.toString());
//                }
//
//                @Override
//                public void deliveryComplete(IMqttDeliveryToken token) {
//                    log.info("消息完成{}", token);
//                }
//            });
//
//            client.connect(options);
//            client.subscribe(topic,1);
//        } catch (UnknownHostException | MqttException e) {
//            log.error("mqtt连接broker错误", e);
//        }
//
//    }
//}
