package com.unilumin.fc.iot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.unilumin.fc.iot.mapper.cluster.BaseDao;
import com.unilumin.fc.iot.model.Page;

import java.util.List;

public interface BaseService<T extends Page, D extends BaseDao<T>> {
    D initDao();

    default int deleteByPrimaryKey(T t) {
        D baseDao = this.initDao();
        return baseDao.deleteByPrimaryKey(t);
    }

    default int insert(T t) {
        D baseDao = this.initDao();
        return baseDao.insert(t);
    }

    default Page selectByPrimaryKey(T t) {
        D baseDao = this.initDao();
        return (Page)baseDao.selectByPrimaryKey(t);
    }

    default List<T> selectByCondition(T t) {
        D baseDao = this.initDao();
        return baseDao.selectByCondition(t);
    }

    default Page selectByConditionFirst(T t) {
        D baseDao = this.initDao();
        return (Page)baseDao.selectByConditionFirst(t);
    }

    default T selectAllByPaging(T t) {
        D baseDao = this.initDao();
        PageHelper.startPage(t.getPage(), t.getPageSize());
        List<T> lists = baseDao.selectAll(t);
        PageInfo pageInfo = new PageInfo(lists);
        t.setRows(lists);
        t.setTotal((new Long(pageInfo.getTotal())).intValue());
        return t;
    }

    default int update(T t) {
        BaseDao<T> baseDao = this.initDao();
        return baseDao.update(t);
    }

    default List<T> selectAll(T t) {
        BaseDao<T> baseDao = this.initDao();
        return baseDao.selectAll(t);
    }
}
