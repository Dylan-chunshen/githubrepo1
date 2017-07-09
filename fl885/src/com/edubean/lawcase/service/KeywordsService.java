package com.edubean.lawcase.service;

import gov.util.jpa.BaseJpaService;
import java.util.List;


public interface KeywordsService extends BaseJpaService{
	public List getKeywordsOfCase(String caseId);
}