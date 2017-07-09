package com.edubean.common.service.impl;

import gov.util.jpa.BaseJpaDao;
import gov.util.jpa.impl.BaseJpaServiceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.edubean.common.dao.CommonDao;
import com.edubean.common.service.FileService;
import com.edubean.common.util.CommonTool;
import com.edubean.common.util.FileUtil;

@Service("fileService")
@Transactional(rollbackFor={Exception.class})
public class FileServiceImpl extends BaseJpaServiceImpl implements FileService{
	@Autowired
	private CommonDao commonDao; 
	
	public BaseJpaDao getJpaDao() {
		return commonDao;
	}
	
	@Override
	public String insertModelWithAttach(HttpServletRequest request,String ny, String jz,CommonsMultipartFile cmf) {
		String result="fail";
		return result;
	}
}
