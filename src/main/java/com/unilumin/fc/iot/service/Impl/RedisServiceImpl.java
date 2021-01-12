package com.unilumin.fc.iot.service.Impl;

import com.unilumin.fc.iot.service.RedisService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZW
 * Redis实现类
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 获取操作字符串的ValueOperations
     */
    public ValueOperations<String,Object> getValueOperations(){
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo;
    }

    @Override
    public void set(String key, Object value) {
        getValueOperations().set(key, value);
    }

    @Override
    public Object get(String key) {
        return getValueOperations().get(key);
    }

    @Override
    public void delete(String key) {
        RedisOperations<String, Object> operations = getValueOperations().getOperations();
        operations.delete(key);
    }

    public Long lpush(String key, String value){
        //获取操作list的对象
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        Long length = opsForList.leftPush(key,value);
        return length;
    }

    @Override
    public List<Object> range(String key) {
        //获取操作list的对象
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.range(key,0,-1);
    }

    @Override
    public Long lPushAll(String key, List<Object> list) {
        //获取操作list的对象
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.leftPushAll(key,list);
    }

    @Override
    public Long remove(String key, long count, Object value) {
        //获取操作list的对象
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        return opsForList.remove(key,count,value);
    }

    @Override
    public void set(String key, Object value, Long expire) {
        redisTemplate.opsForValue().set(key,value);
        //设置过期时间
        redisTemplate.expire(key,expire, TimeUnit.SECONDS);
    }


}
