package com.edubean.common.service.impl;

import gov.util.jpa.BaseJpaDao;
import gov.util.jpa.impl.BaseJpaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edubean.common.dao.CommonDao;
import com.edubean.common.service.CommonService;

@Service("commonService")
@Transactional(rollbackFor={Exception.class})
public class CommonServiceImpl extends BaseJpaServiceImpl implements CommonService{
	@Autowired
	private CommonDao commonDao; 
	
	public BaseJpaDao getJpaDao() {
		return commonDao;
	}
}
