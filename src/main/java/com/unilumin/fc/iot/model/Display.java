package com.unilumin.fc.iot.model;

import java.util.Date;

/**
 * 显示屏实体类
 * @Author ZW
 * */
public class Display extends Page {

    //显示屏状态  - 0 正常
    public static final Integer NORMAL_STATUS = 0;

    //显示屏状态  - 1 警告
    public static final Integer WARNING_STATUS = 1;

    //显示屏状态  - 2报警
    public static final Integer ALERT_STATUS = 2;

    //显示屏状态  - 3离线
    public static final Integer OFFLINE_STATUS = 3;

    /**
     * 设备编号
     * */
    private Integer id;

    /**
     * 分组编号
     * */
    private Integer groupId;

    /**
     * 显示屏名称
     * */
    private String displayName;

    /**
     * 显示屏描述
     * */
    private String displayContext;

    /**
     * 序列号
     * */
    private String machineCode;

    /**
     * 显示屏状态  - 0 正常  1 警告  2报警  3离线
     * */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    public Display(Integer id, Integer groupId, String displayName, String displayContext, String machineCode, Integer status, Date createTime) {
        this.id = id;
        this.groupId = groupId;
        this.displayName = displayName;
        this.displayContext = displayContext;
        this.machineCode = machineCode;
        this.status = status;
        this.createTime = createTime;
    }

    public Display() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayContext() {
        return displayContext;
    }

    public void setDisplayContext(String displayContext) {
        this.displayContext = displayContext;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Display{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", displayName='" + displayName + '\'' +
                ", displayContext='" + displayContext + '\'' +
                ", machineCode='" + machineCode + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
