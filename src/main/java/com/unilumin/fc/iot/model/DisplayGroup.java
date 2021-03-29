package com.unilumin.fc.iot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 显示屏分组实体类
 * @Author ZW
 * */
public class DisplayGroup extends Page {

    /**
     * 设备分组编号
     * */
    private Integer id ;

    /**
     * 设备分组名
     * */
    private String groupName;

    /**
     * 分组描述
     * */
    private String groupContent;

    /**
     * 分组创建时间
     * */
    private Date creatTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupContent() {
        return groupContent;
    }

    public void setGroupContent(String groupContent) {
        this.groupContent = groupContent;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }
}
