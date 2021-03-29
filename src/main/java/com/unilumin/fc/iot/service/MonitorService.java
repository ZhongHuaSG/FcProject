package com.unilumin.fc.iot.service;

import com.unilumin.fc.iot.mapper.master.DatabaseMapper;
import com.unilumin.fc.iot.mapper.master.MonitorMapper;
import com.unilumin.fc.iot.model.Monitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用以监控设备信息的TDengine数据库操作类
 * @author ZW
 */
@Slf4j
@Service
public class MonitorService {

    private final DatabaseMapper databaseMapper;
    private final MonitorMapper monitorMapper;

    public MonitorService(DatabaseMapper databaseMapper,MonitorMapper monitorMapper) {
        this.databaseMapper = databaseMapper;
        this.monitorMapper = monitorMapper;
    }

    public int save(Monitor monitor) {
        return monitorMapper.insert(monitor);
    }

    public int batchSave(List<Monitor> monitorList) {
        return monitorMapper.batchInsert(monitorList);
    }

    public List<Monitor> query(Long limit,Long offset){
        return monitorMapper.select(limit,offset);
    }


}
