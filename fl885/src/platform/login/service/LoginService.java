package platform.login.service;

import java.util.Map;

import platform.login.bo.PubUserBo;

public interface LoginService{
	
	/**
	 * 注册新用户
	 * @param phone
	 * @param name
	 * @param password
	 * @param ip
	 * @return
	 */
	public Map<String, String> registerPubUser(String phone, String name, String password, String ip);
	
	/**
	 * 公众账号的登录
	 * @param phone
	 * @param password
	 * @return
	 */
	public Map<String, String> loginPubUser(String phone, String password);
	
	/**
	 * 通过手机号获取已存在账户
	 * @param phone
	 * @return
	 */
	public PubUserBo getPubUserBoBy(String phone);
	
	/**
	 * 获取当前可用的公共用户编号
	 * @return String
	 */
	public String getMaxPubUserCodeNow();
	
//	public void saveWegovTeamInfo(OrganNode organ);
	
//	public void saveWegovUserList(OrganNode organ, List<SecurityUser> securityUsers);
	
}
