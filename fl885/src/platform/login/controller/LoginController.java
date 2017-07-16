package platform.login.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.login.bo.PubUserBo;
import platform.login.service.LoginService;
import platform.utils.MD5Utils;
import wegov.platform.OrgQHelper;
import wfc.facility.org.common.UserInfo;
import wfc.facility.right.common.RoleInfo;

import com.edubean.common.service.CommonService;
import com.edubean.common.util.Fl885Const;

@Controller
@RequestMapping(value = "login/")
public class LoginController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param account      用户名
	 * @param password     密码
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	@RequestMapping(value = "userLogin")
	public String userLogin(HttpServletRequest request,HttpServletResponse response, 
			String account, String password,Map<String, Object> model){
		try {
			OrgQHelper    helper    = new OrgQHelper();
			if(!(StringUtils.isNotBlank(account)&&StringUtils.isNotBlank(password))){
				model.put("errorMessage", "账号或密码输入未输入，请重新输入！");
				return "/login.jsp";
			}else if(helper.getUserByAccount(account.trim()) == null){
				model.put("errorMessage", "用户不存在！请重新输入！");
				return "/login.jsp";
			}else{
				UserInfo currentPerson = helper.getUserByAccount(account.trim());
				if(StringUtils.isNotBlank(currentPerson.password)&&currentPerson.password.trim().equalsIgnoreCase(MD5Utils.MD5(password.trim()))){
//					获取用户角色
					List<RoleInfo> roleList = helper.getUserRoles(currentPerson);
					RoleInfo roleInfo       = (roleList!=null&&roleList.size()>0)?roleList.get(0):new RoleInfo();
					String roleTypeCode     = roleInfo.roleId;
					String roleTypeName     = roleInfo.description;
					currentPerson.setRolesByUser(roleList);
					
//					装入session，cookie。记入日志
					request.getSession().setAttribute("roleList", roleList);
					request.getSession().setAttribute("roleTypeCode", roleTypeCode);
					request.getSession().setAttribute("roleTypeName", roleTypeName);
					request.getSession().setAttribute("currentPerson", currentPerson);
					response.addCookie(this.createCookie("account", account.trim()));
					response.addCookie(this.createCookie("password", password.trim()));
					
					if(Fl885Const.ROLE_ID_SJ.equalsIgnoreCase(roleTypeCode)){
						return "/indexSj";
					}else if(Fl885Const.ROLE_ID_QX.equalsIgnoreCase(roleTypeCode)){
						return "/indexQx";
					}else {
						return "/indexJz";
					}
				}else{
					model.put("errorMessage", "密码错误，请重新输入！");
					return "/login.jsp";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put("errorMessage", "登陆异常！请联系管理员修复！");
			return "/login";
		}
	}
	
	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userLogout.html")
	public String userLogout(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model){
		request.getSession().removeAttribute("CURRENTROLE");
		request.getSession().removeAttribute("CURRENTPERSON");
		return "/index";
	}

	private Cookie createCookie(String name, String value) {
		Date date = new Date();
		Cookie c = new Cookie(name, date.toString());
		c.setValue(value);
		c.setPath("/");
		c.setMaxAge(60 * 60 * 24 * 30);
		return c;
	}
	/**
	 * 跳转到用户注册页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "registerPubUser.html")
	public String registerCommon(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model){
		request.getSession().removeAttribute("roleType");
		request.getSession().removeAttribute("currentPerson");
		return "/page/platform/registerPubUser";
	}
	
	/**
	 * 跳转到用户注册页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loginPubUser.html")
	public String loginPubUserHtml(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model){
		request.getSession().removeAttribute("roleType");
		request.getSession().removeAttribute("currentPerson");
		return "/page/platform/loginPubUser";
	}
	
	/**
	 * 公众用户的注册action处理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "registerPubUser.action")
	@ResponseBody
	public String registerPubUser(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model){
		String phone = (request.getParameter("phone")!=null)?request.getParameter("phone").toString().trim():"";
		String name = (request.getParameter("name")!=null)?request.getParameter("name").toString().trim():"";
		String password = (request.getParameter("password")!=null)?request.getParameter("password").toString().trim():"";
		String ip = "58.251.160.199";
		String ip2 = request.getRemoteAddr();
		System.out.println("============="+ip2);
		Map<String, String> resultMap = loginService.registerPubUser(phone, name, password, ip);
		
//		return "/page/platform/register";
		return resultMap.get("result").toString();
	}

	/**
	 * 公众用户的注册action处理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loginPubUser.action")
	public String loginPubUserAction(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model){
		String phone = (request.getParameter("phone")!=null)?request.getParameter("phone").toString().trim():"";
		String password = (request.getParameter("password")!=null)?request.getParameter("password").toString().trim():"";
		Map<String, String> resultMap = loginService.loginPubUser(phone, password);
		if(resultMap.containsKey("result")&&"SUCCESS".equalsIgnoreCase(resultMap.get("result").toString().trim())){
			HttpSession session = request.getSession();
			PubUserBo pubUserBo = loginService.getPubUserBoBy(phone);
			session.setAttribute("CURRENTPERSON", pubUserBo);
			session.setAttribute("CURRENTROLE", "PUBUSER");
			return "/index";
		}else{
			return "/page/platform/loginPubUser";
		}
	}
}
