package com.unilumin.fc.iot.code;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author ZW
 * @description 获取Application配置数据
 */
@Getter
@Component
public class ApplicationValues {

    @Value("${http_pool.max_total}")
    private int maxTotal;

    @Value("${http_pool.default_max_per_route}")
    private int maxPerRoute;

    @Value("${http_pool.connect_timeout}")
    private int connTimeOut;

    @Value("${http_pool.connection_request_timeout}")
    private int connReqTimeOut;

    @Value("${http_pool.socket_timeout}")
    private int socketTimeout;

    @Value("${http_pool.validate_after_inactivity}")
    private int inactivity;

}
