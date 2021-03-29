package com.unilumin.fc.iot.Controller;

import com.unilumin.fc.iot.model.EmqX;
import com.unilumin.fc.iot.model.Monitor;
import com.unilumin.fc.iot.service.MonitorService;
import io.swagger.annotations.ApiOperation;
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


    private final MonitorService monitorService;
    public TestController(MonitorService monitorService){
        this.monitorService = monitorService;
    }
    /**
     * 获取EMQ X服务端的请求参数-POST请求
     * */
    @PostMapping("/testPostParam")
    public String getPostParam(HttpServletRequest request, @RequestBody EmqX emqX){
        log.info("规则引擎请求：request==="+request);
        log.info("规则引擎请求：emqX.MSG==="+emqX.getMsg());
        log.info("规则引擎请求：emqX.Qos==="+emqX.getQos());
        log.info("规则引擎请求：emqX.getTopic==="+emqX.getTopic());
        return request.toString();
    }

    /**
     * 获取EMQ X服务端的请求参数-GET请求
     * */
    @GetMapping("/testTestParam")
    public String getGetParam(@Param(value = "username") String username,@RequestBody EmqX emqX,@Param(value = "msg") String hello){
        log.info("msg===="+hello);
        log.info("username"+username);
        log.info("规则引擎请求：emqX.MSG==="+emqX.getMsg());
        log.info("规则引擎请求：emqX.Qos==="+emqX.getQos());
        log.info("规则引擎请求：emqX.getTopic==="+emqX.getTopic());
        return null;
    }

    /**
     * 测试监控数据的请求方法
     * */
    @ApiOperation(value = "测试监控方法")
    @PostMapping("/testMonitor")
    public String testMonitor(){
        Monitor monitor = new Monitor();
        monitor.setBitErroRate((float) 1.00);
        monitor.setDeviceType(1);
        monitor.setElectricity((float) 2.05);
        monitor.setHumidity((float) 3.05);
        monitor.setSmoke((float) 2.05);
        monitor.setSerialNum("10000000a26105bc");
        monitor.setVoltage((float) 3.51);
        monitor.setWorkTime(System.currentTimeMillis()+"");
        monitor.setWorkState(0);
        monitorService.save(monitor);
        long offset = 0;
        monitorService.query((long) 1,offset);
        return monitorService.save(monitor)+"";
    }

}
