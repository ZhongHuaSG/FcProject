package com.unilumin.fc.iot.service.Impl;

import com.unilumin.fc.iot.mapper.cluster.DisplayDao;
import com.unilumin.fc.iot.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 设备业务处理service实现类
 *
 * @Author ZW
 * */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    /**
     * 注入dao
     */
    @Resource
    private DisplayDao displayDao;
    /**
     * 初始化
     */
    @Override
    public DisplayDao initDao(){
        return displayDao;
    }

}
