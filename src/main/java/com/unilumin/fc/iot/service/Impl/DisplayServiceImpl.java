package com.unilumin.fc.iot.service.Impl;


import com.unilumin.fc.iot.mapper.cluster.DisplayDao;
import com.unilumin.fc.iot.service.DisplayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *显示屏列表 serverImpl
 */
@Service
@Transactional
public class DisplayServiceImpl  implements DisplayService {


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
