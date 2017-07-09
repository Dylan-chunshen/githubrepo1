package com.edubean.lawcase.service.impl;

import gov.util.jpa.BaseJpaDao;
import gov.util.jpa.Query;
import gov.util.jpa.filter.Order;
import gov.util.jpa.impl.BaseJpaServiceImpl;
import gov.util.jpa.operator.FilterOperator;
import gov.util.jpa.operator.OrderOperator;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edubean.common.dao.CommonDao;
import com.edubean.lawcase.bo.KeywordsBo;
import com.edubean.lawcase.service.KeywordsService;

@Service("keywordsService")
@Transactional(rollbackFor={Exception.class})
public class KeywordsServiceImpl extends BaseJpaServiceImpl implements KeywordsService{
	@Autowired
	private CommonDao commonDao; 
	
	public BaseJpaDao getJpaDao() {
		return commonDao;
	}

	@SuppressWarnings("rawtypes")
	@Override 
	public List getKeywordsOfCase(String caseId) {
		Query query = Query.from(KeywordsBo.class);
		query.filter(FilterOperator.EQUAL, "id", (StringUtils.isNotBlank(caseId)?caseId.trim():""));
		query.filter(FilterOperator.EQUAL, "isremoved", "0");
		query.orderBy(new Order(OrderOperator.DESC, "createdTime"));
		List listKeywords = commonDao.findAll(query);
		return listKeywords;
	}
}
