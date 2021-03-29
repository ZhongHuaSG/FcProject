package com.unilumin.fc.iot.service.Impl;


import com.unilumin.fc.iot.mapper.cluster.DisplayGroupDao;
import com.unilumin.fc.iot.service.DisplayGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *显示屏分组表 serverImpl
 */
@Service
@Transactional
public class DisplayGroupServiceImpl   implements DisplayGroupService {


	/**
	 * 注入dao
	 */
	@Resource
	private DisplayGroupDao displayGroupDao;
	/**
	 * 初始化
	 */
	@Override
	public DisplayGroupDao initDao(){
		return displayGroupDao;
	}


}
