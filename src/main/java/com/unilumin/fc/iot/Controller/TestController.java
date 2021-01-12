package com.unilumin.fc.iot.Controller;

import com.unilumin.fc.iot.model.EmqX;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * EMQ x测试操作
 *
 * @author ZhongWei
 * @since  2020/12/15
 */
@Slf4j
@RestController
public class TestController {


    /**
     * 获取EMQ X服务端的请求参数-POST请求
     * */
    @PostMapping("/testPostParam")
    public String getPostParam(HttpServletRequest request, @RequestBody EmqX emqX){
        log.info("request==="+request);
        log.info("emqX.MSG==="+emqX.getMsg());
        log.info("emqX.Qos==="+emqX.getQos());
        log.info("emqX.getTopic==="+emqX.getTopic());
        return request.toString();
    }

    /**
     * 获取EMQ X服务端的请求参数-GET请求
     * */
    @GetMapping("/testTestParam")
    public String getGetParam(@Param(value = "username") String username){
        log.info("username"+username);
        return null;
    }

}
