package com.unilumin.fc.iot.service;

import java.util.List;

/**
 * @author ZW
 */

public interface RedisService {

    /**
     * 向Redis中存放字符串
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 向Redis中存放字符串[并设置过期时间]
     *
     * @param key
     * @param value
     * @param expire 单位s
     */
    void set(String key, Object value, Long expire);

    /**
     * 根据key值获取数据【操作字符串】
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除指定的键值【操作字符串】
     *
     * @param key
     * @return
     */
    void delete(String key);

    /**
     * 添加数据到list的顶部【操作List】
     *
     * @param key List名称
     * @param value 存放的数值
     * @return 存放完毕之后，当前List的长度
     */
    Long lpush(String key, String value);

    /**
     * 获取List集合中的数据【操作List】
     *
     * @param key List名称
     * @return list中存放的数据
     */
    List<Object> range(String key);

    /**
     * 向List集合中存放集合
     *
     * @param key 被操作的List
     * @param list 需要添加的List
     * @return 存放完毕之后，当前List的长度
     */
    Long lPushAll(String key, List<Object> list);

    /**
     * 移除List中的元素
     *
     * @param key 被操作的List
     * @param count 要删除的元素个数【为负数表示从尾部，向头部删除;正数则相反】
     * @param value 要删除的元素值
     * @return 移除完毕之后，当前List的长度
     */
    Long remove(String key, long count, Object value);
}

