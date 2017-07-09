package platform.wegov.dao;

import gov.util.dao.BaseDao;

import java.util.List;

import org.hibernate.Session;

/**
 * <p>Title: ReceptionDAO.java</p>
 * <p>Description: ½Ó´ýDAO²ã</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Wonders Information Co., Ltd</p>
 * <p>Created on: 2009-1-6</p>
 * @author zx
 * @version 1.0
 */

public class CommonDao extends BaseDao {
	
	public List executeSql(String sql) {
		Session session = this.hibernateTemplate.getSessionFactory().openSession();
		List result =session.createSQLQuery(sql).list();
		session.close();
		return result;
		
	}

}
