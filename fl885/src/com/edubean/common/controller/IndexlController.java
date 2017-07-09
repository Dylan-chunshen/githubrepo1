package com.edubean.common.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import wegov2.platform.core.bo.UserBo;

@Controller
@RequestMapping("/szlg")
public class IndexlController {
	
	@Autowired
	private Mapper mapper;
	
	
	@SuppressWarnings("unchecked")  
	@RequestMapping("/getDynamic.ajax")
	public String getDynamic(HttpServletRequest request,
			HttpServletResponse response,@RequestParam HashMap<String, Object> conditions,
			Map<String, Object> model) {
//		UserBo user =  (UserBo)request.getSession().getAttribute("currentPerson");
		return "indexDynamic";
	}

}
