package platform.wegov.bo;

import java.util.HashMap;
import java.util.Map;

import wfc.facility.right.common.RoleInfo;


public class RoleBoLocal extends RoleInfo{
	private static final long serialVersionUID = 1L;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Map<String, Object> transMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", this.roleId);
		map.put("roleName", this.roleName);
		map.put("description", this.description);
		return map;
	}
}