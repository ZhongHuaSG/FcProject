package com.unilumin.fc.iot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 接收卡信息的实体类
 *
 * @author ZW
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverInformation {

    //时间戳
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Timestamp ts;

    //接收卡ID
    private String recId;

    //型号
    private String type;

    //版本
    private String version;

    //序列号
    private String serialNumber;

    //累计工作时间
    private int  cumulativeWorkingTime;

    //温度
    private int temperature;

    //电压
    private float voltage;

    //误码率
    private float symbolErrorRate;

    //湿度
    private float humidity;

    //电流
    private float current;

    //烟雾
    private String swog;


}
