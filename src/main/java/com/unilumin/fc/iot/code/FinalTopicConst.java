package com.unilumin.fc.iot.code;

/**
 * @author ZW
 * EMQ X 对应订阅主题的常量池
 */
public class FinalTopicConst {

    //topic名称
    public static final String GOODS = "goods";

    public static final String MODPARAS = "COMMON_MODPARAS";

    public static final String RECPARAS = "COMMON_RECPARAS";

    public static final String CONTRAST = "CONTRAST";

    public static final String WORKTIME = "CURRENT_WORKTIME";

    //下线状态---设备失去连接

    public static final String DISCONNECTED = "/disconnected" ;

    //FC项目Topic

    //第一次设备广播的主题、服务端接收的主题包
    public static final String GET_DEVICE_ID = "/FC/getDeviceId";


}
