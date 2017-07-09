package com.edubean.common.service;

import gov.util.jpa.BaseJpaService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface FileService extends BaseJpaService{
	public String insertModelWithAttach(HttpServletRequest request,String ny,String jz,CommonsMultipartFile cmf);
}
