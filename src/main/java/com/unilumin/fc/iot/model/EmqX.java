package com.unilumin.fc.iot.model;

public class EmqX {

    //主题
    private String topic;
    //服务等级
    private String qos;
    //具体内容
    private String msg;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQos() {
        return qos;
    }

    public void setQos(String qos) {
        this.qos = qos;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
