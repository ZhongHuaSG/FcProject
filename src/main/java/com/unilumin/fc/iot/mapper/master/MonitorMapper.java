package com.unilumin.fc.iot.mapper.master;

import com.unilumin.fc.iot.model.Monitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZW
 */
@Component
@Mapper
public interface MonitorMapper {

    /**
     * 创建数据表
     *
     * @param dbName    数据库名
     * @param tableName 表名
     */
    void createTable(String dbName, String tableName);

    /**
     * 单条插入数据
     *
     * @param monitor 数据
     * @return
     */
    int insert(Monitor monitor);

    /**
     * 批量插入数据
     *
     * @param monitorList 数据列表
     * @return
     */
    int batchInsert(List<Monitor> monitorList);

    /**
     * 查询数据
     *
     * @param limit  个数
     * @param offset 偏移
     * @return
     */
    List<Monitor> select(@Param("limit") Long limit, @Param("offset") Long offset);

}
