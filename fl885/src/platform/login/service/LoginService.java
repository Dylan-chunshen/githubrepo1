package platform.login.service;

import java.util.Map;

import platform.login.bo.PubUserBo;

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
	 * �����˺ŵĵ�¼
	 * @param phone
	 * @param password
	 * @return
	 */
	public Map<String, String> loginPubUser(String phone, String password);
	
	/**
	 * ͨ���ֻ��Ż�ȡ�Ѵ����˻�
	 * @param phone
	 * @return
	 */
	public PubUserBo getPubUserBoBy(String phone);
	
	/**
	 * ��ȡ��ǰ���õĹ����û����
	 * @return String
	 */
	public String getMaxPubUserCodeNow();
	
//	public void saveWegovTeamInfo(OrganNode organ);
	
//	public void saveWegovUserList(OrganNode organ, List<SecurityUser> securityUsers);
	
}
