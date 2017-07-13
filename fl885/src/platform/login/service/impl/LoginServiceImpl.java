package platform.login.service.impl;

import gov.util.jpa.BaseJpaDao;
import gov.util.jpa.Query;
import gov.util.jpa.impl.BaseJpaServiceImpl;
import gov.util.jpa.operator.FilterOperator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import platform.login.bo.PubUserBo;
import platform.login.service.LoginService;
import platform.utils.MD5Utils;
import wad2.vdm.result;

import com.edubean.common.dao.CommonDao;
import com.edubean.common.util.IPUtil;

@Service("loginService")
@Component
@Transactional
public class LoginServiceImpl extends BaseJpaServiceImpl implements LoginService{
	
	@Autowired
	private CommonDao commonDao; 
	
	public BaseJpaDao getJpaDao() {
		return commonDao;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> registerPubUser(String phone, String name, String password, String ip){
		Map resultMap = new HashMap<String, String>();
		if(StringUtils.isNotBlank(phone)){
			PubUserBo pubUserBoHave = getPubUserBoBy(phone);
			if(pubUserBoHave!=null){
				resultMap.put("result", "HAVE");
				resultMap.put("user_id", pubUserBoHave.getPublic_user_id());
			}else{
				Map<String, String> ipMap = (StringUtils.isNotBlank(ip))?IPUtil.getIPMap(ip.trim()):null;
				String province_code = (ipMap!=null&&ipMap.containsKey("region_id"))?ipMap.get("region_id").toString():"";
				String province_name = (ipMap!=null&&ipMap.containsKey("region"))?ipMap.get("region").toString():"";
				String city_code = (ipMap!=null&&ipMap.containsKey("city_id"))?ipMap.get("city_id").toString():"";
				String city_name = (ipMap!=null&&ipMap.containsKey("city"))?ipMap.get("city").toString():"";
				password = (StringUtils.isNotBlank(password))?password.trim():(StringUtils.isNotBlank(phone))?phone.substring(phone.trim().length()-4, phone.trim().length()):"111111";
				String passwordMD5 = MD5Utils.MD5(password.trim());
				String pubUserCode = getMaxPubUserCodeNow();
				PubUserBo pubUserBoNew = new PubUserBo();
				pubUserBoNew.setPublic_user_code(pubUserCode);
				pubUserBoNew.setPublic_user_name(StringUtils.isNotBlank(name)?name.trim():"");
				pubUserBoNew.setCity_code(city_code);
				pubUserBoNew.setCity_name(city_name);
				pubUserBoNew.setProvince_code(province_code);
				pubUserBoNew.setProvince_name(province_name);
				pubUserBoNew.setPublic_user_tel(phone);
				pubUserBoNew.setRegister_ip(ip);
				pubUserBoNew.setPassword(passwordMD5);
				pubUserBoNew.setCreatetime(new Date());
				pubUserBoNew.setIs_removed("0");
				pubUserBoNew = (PubUserBo) commonDao.save(pubUserBoNew);
				resultMap.put("result", "SUCCESS");
				resultMap.put("user_id", pubUserBoNew.getPublic_user_id());
			}
		}else{
			resultMap.put("result", "FAIL");
			resultMap.put("user_id", "");
		}
			
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PubUserBo getPubUserBoBy(String phone){
		PubUserBo resultPubUser = null;
		if(StringUtils.isNotBlank(phone)){
			Query queryPubUser = Query.from(PubUserBo.class);
			queryPubUser.filter(FilterOperator.EQUAL, "public_user_tel", phone.trim());
			queryPubUser.filter(FilterOperator.EQUAL, "is_removed", "0");
			List<PubUserBo> listPubUser = commonDao.findAll(queryPubUser);
			if(listPubUser!=null&&listPubUser.size()>0){
				resultPubUser = listPubUser.get(0);
			}
		}
		return resultPubUser;
	}

	/**
	 * 获取当前可用的公共用户编号
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String getMaxPubUserCodeNow(){
		String sqlText = " select max(cast(public_user_code as unsigned int)) from public_user ";
		List resultList = commonDao.executeSqlQuery(sqlText, null);
		String codeStr = (resultList!=null&&resultList.size()>0&&resultList.get(0)!=null)?resultList.get(0).toString().trim():"";
		int codeInt = (StringUtils.isNotBlank(codeStr))?Integer.parseInt(codeStr)+1:1000;
		return codeInt+"";
	}

	/**
	 * 公众账号的登录
	 * @param phone
	 * @param password
	 * @return
	 */
	@Override
	public Map<String, String> loginPubUser(String phone, String password){
		Map<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNotBlank(phone)){
			PubUserBo pubUserBoHave = getPubUserBoBy(phone);
			if(pubUserBoHave!=null&&StringUtils.isNotBlank(pubUserBoHave.getPublic_user_id())){
				String passwordHave = (StringUtils.isNotBlank(pubUserBoHave.getPassword()))?pubUserBoHave.getPassword():"";
				String passwordPage = MD5Utils.MD5(password.trim());
				if(passwordHave.equalsIgnoreCase(passwordPage)){
					resultMap.put("result", "SUCCESS");
					resultMap.put("phone", pubUserBoHave.getPublic_user_tel());
				}else{
					resultMap.put("result", "PASSWORD_ERROR");
					resultMap.put("phone", pubUserBoHave.getPublic_user_tel());
				}
			}else{
				resultMap.put("result", "FAIL");
				resultMap.put("phone", "");
			}
		}
		return resultMap;
	}

}
