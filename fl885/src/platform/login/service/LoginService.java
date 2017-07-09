package platform.login.service;

import java.util.Map;

import platform.login.bo.PubUserBo;


//import com.wondersgroup.framework.organization.bo.OrganNode;
//import com.wondersgroup.framework.security.bo.SecurityUser;

public interface LoginService{
	
	/**
	 * ע�����û�
	 * @param phone
	 * @param name
	 * @param password
	 * @param ip
	 * @return
	 */
	public Map<String, String> registerPubUser(String phone, String name, String password, String ip);
	
	/**
	 * ͨ���ֻ��Ż�ȡ�Ѵ����˻�
	 * @param phone
	 * @return
	 */
	public PubUserBo getPubUserBoBy(String phone);
	
//	public void saveWegovTeamInfo(OrganNode organ);
	
//	public void saveWegovUserList(OrganNode organ, List<SecurityUser> securityUsers);
	
}
