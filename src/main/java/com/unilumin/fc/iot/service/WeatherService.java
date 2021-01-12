package com.unilumin.fc.iot.service;

import com.unilumin.fc.iot.mapper.master.DatabaseMapper;
import com.unilumin.fc.iot.mapper.master.WeatherMapper;
import com.unilumin.fc.iot.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class WeatherService {

    private final DatabaseMapper databaseMapper;
    private final WeatherMapper weatherMapper;

    @Resource
    RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    public WeatherService(DatabaseMapper databaseMapper, WeatherMapper weatherMapper) {
        this.databaseMapper = databaseMapper;
        this.weatherMapper = weatherMapper;
    }

    public boolean init() {
        try {
            // 删除
            databaseMapper.dropDatabase("db");
            // 创建 databaseMapper.createDatabase( "db")
            Map<String, String> map = new HashMap<>(4);
            map.put("dbName", "db");
            map.put("keep", "36500");
            map.put("days", "30");
            map.put("blocks", "4");
            databaseMapper.creatDatabaseWithParameters(map);
            // 选择
            databaseMapper.useDatabase("db");
            // 创建表
            weatherMapper.createTable("db", "weather");
            return true;
        } catch (Exception e) {
            log.error("创建数据表出错", e);
        }
        return false;
    }

    public int save(Weather weather) {
        return weatherMapper.insert(weather);
    }

    public int batchSave(List<Weather> weatherList) {
        return weatherMapper.batchInsert(weatherList);
    }

    public List<Weather> query(Long limit, Long offset) {
        List <Weather> list = new ArrayList<>();
        // 从缓存中获取信息
        String key = "Offset_" + offset;
        ValueOperations<String, Weather> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);

        if(hasKey){
            list = (List<Weather>) operations;
        }else{
            list = weatherMapper.select(limit, offset);
            redisService.set(key,list);
        }
        return weatherMapper.select(limit, offset);
    }
}
