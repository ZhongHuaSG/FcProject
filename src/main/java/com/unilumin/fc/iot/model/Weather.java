package com.unilumin.fc.iot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 天气的实体类
 *
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Timestamp ts;

    //温度
    private int temperature;

    //湿度
    private float humidity;

}
