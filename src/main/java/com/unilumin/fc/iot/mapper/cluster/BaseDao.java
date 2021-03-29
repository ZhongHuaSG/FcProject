package com.unilumin.fc.iot.mapper.cluster;


import java.util.List;

public interface BaseDao<T> {
    //新增
    int insert(T var1);

    //删除
    int deleteByPrimaryKey(T var1);

    //更新
    Integer update(T var1);

    //根据主键查找
    T selectByPrimaryKey(T var1);

    //根据条件查找
    List<T> selectByCondition(T var1);

    default T selectByConditionFirst(T t) {
        List<T> ts = this.selectByCondition(t);
        return ts != null && ts.size() > 0 ? ts.get(0) : null;
    }

    //获取全部数据
    List<T> selectAll(T var1);
}
