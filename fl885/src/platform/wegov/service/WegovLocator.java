package platform.wegov.service;

//import gov.menu.domain.Menu;
//import gov.menu.domain.menutype.CommonMenuType;
//import gov.menu.domain.menutype.GadMenuType;
//import gov.menu.domain.menutype.SimpleMenuConfigBo;
//import gov.menu.domain.menutype.SimpleMenuType;
//import gov.menu.service.AccessService;
//import gov.menu.service.MenuTypeService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import platform.wegov.common.WegovConst;

public class WegovLocator {
	
	private static WegovLocator instance = new WegovLocator();
	
	private WegovLocator() {		
	}
	
	public static WegovLocator getInstance() {
		return instance;
	}
	
	private ApplicationContext context;
	
	private ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(WegovConst.FILE_ROUTE);
		}
		return context;
	}
	
//	public Menu getMenu() {
//		return (Menu)this.getContext().getBean(WegovConst.MENU);
//	}
//	
//	public MenuTypeService getMenuTypeService() {
//		return (MenuTypeService)this.getContext().getBean(WegovConst.MENU_TYPE_SERVICE);
//	}
//	
//	public AccessService getAccessService() {
//		return (AccessService)this.getContext().getBean(WegovConst.ACCESS_SERVICE);
//	}
//	
//	public CommonMenuType getCommonMenuType() {
//		return (CommonMenuType)this.getContext().getBean(WegovConst.COMMON_MENU_TYPE);
//	}
//	
//	public GadMenuType getGadMenuType() {
//		return (GadMenuType)this.getContext().getBean(WegovConst.GAD_MENU_TYPE);
//	}
//	
//	public SimpleMenuType getWegovAdminMenuType() {
//		return (SimpleMenuType)this.getContext().getBean(WegovConst.WEGOVE_ADMIN_MENU_TYPE);
//	}
//	
//	public SimpleMenuConfigBo getUserAdmin() {
//		return (SimpleMenuConfigBo)this.getContext().getBean(WegovConst.USER_ADMIN);
//	}
//	
//	public SimpleMenuConfigBo getOrgAdmin() {
//		return (SimpleMenuConfigBo)this.getContext().getBean(WegovConst.ORG_ADMIN);
//	}
//	
//	public SimpleMenuConfigBo getRoleAdmin() {
//		return (SimpleMenuConfigBo)this.getContext().getBean(WegovConst.ROLE_ADMIN);
//	}
//	
//	public SimpleMenuConfigBo getUserInfoAdmin() {
//		return (SimpleMenuConfigBo)this.getContext().getBean(WegovConst.USER_INFO_ADMIN);
//	}
//	
//	public SimpleMenuConfigBo getCheckRoleAdmin() {
//		return (SimpleMenuConfigBo)this.getContext().getBean(WegovConst.CHECK_ROLE_ADMIN);
//	}
			
}
