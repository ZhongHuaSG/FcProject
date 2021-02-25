package com.unilumin.fc.iot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 模组信息的实体类
 * @author ZW
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleInformation {

    //时间戳
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Timestamp ts;

    //ID
    private String ModuleId;

    //分辨率
    private String Resolution;

    //间距
    private float PixelPitch;

    //扫描方式
    private String Scan;

    //序列号
    private String SerialNumber;

    //状态
    private String ModuleState;



}
