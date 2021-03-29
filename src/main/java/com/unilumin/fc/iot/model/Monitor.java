package com.unilumin.fc.iot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 设备监控类
 *
 * @author ZW
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monitor {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Timestamp ts;

    //温度
    private int temperature;

    //湿度
    private float humidity;

    //电压
    private float voltage;

    //误码率
    private float bitErroRate;

    //电流
    private float electricity;

    //烟雾
    private float smoke;

    //工作时间
    private String workTime;

    //工作状态
    private int workState;

    //类型
    private int deviceType;

    //序列号
    private String serialNum;



}
