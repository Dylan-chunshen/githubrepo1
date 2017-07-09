package platform.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wfc.facility.right.common.RoleInfo;

import com.wonders.security.model.Permission;

public class LoginUtil {
	
	/**
	 * 将角色BO列表转化为对应的角色名称字符串
	 * @param roleList
	 * @return
	 */
	public static String transRoleListToRoles(List<RoleInfo> roleList){
		String roleType = "000";
		if(roleList!=null&&roleList.size()>0){
            for(RoleInfo roleInfo:roleList){
                if("R50960".equals(roleInfo.roleId)){
                    roleType = "000";
                }else if("R50961".equals(roleInfo.roleId)){
                    roleType = "001";
                }else if("R50962".equals(roleInfo.roleId)){
                    roleType = "002";
                }else if("R50963".equals(roleInfo.roleId)){
                    roleType = "003";
                }else if("R50964".equals(roleInfo.roleId)){
                    roleType = "004";
                }else if("R50965".equals(roleInfo.roleId)){
                    roleType = "005";
                }
            }
		}
		return roleType;
	}
	
	/**
	 * 将角色BO列表转化为对应的角色名称字符串
	 * @param roleList
	 * @return
	 */
	public static String getRoleIdFromRoleList(List<RoleInfo> roleList){
		String roleType = "000";
		if(roleList!=null&&roleList.size()>0){
            for(RoleInfo roleInfo:roleList){
                if("R50960".equals(roleInfo.roleId)){
                    roleType = "000";
                }else if("R50961".equals(roleInfo.roleId)){
                    roleType = "001";
                }else if("R50962".equals(roleInfo.roleId)){
                    roleType = "002";
                }else if("R50963".equals(roleInfo.roleId)){
                    roleType = "003";
                }else if("R50964".equals(roleInfo.roleId)){
                    roleType = "004";
                }else if("R50965".equals(roleInfo.roleId)){
                    roleType = "005";
                }
            }
		}
		return roleType;
	}
	
	/**
	 * 将权限列表转为Ids
	 * @param roleList
	 * @return
	 */
	public static String[] transPermissionIdListToPermissionIds(List ops){
		if(ops!=null&&ops.size()>0){
			String[] permissionIds = new String[ops.size()];
			for(int i=0;i<ops.size();i++){
				Permission pm = (Permission) ops.get(i);
				permissionIds[i] = pm.getPermissionId();
			}
			return permissionIds;
		}else{
			return null;
		}
	}

	public boolean saveUrlAs(String photoUrl, String fileName) {
	     try {
	      URL url = new URL(photoUrl);
	      HttpURLConnection connection = (HttpURLConnection) url
	        .openConnection();
	      DataInputStream in = new DataInputStream(connection
	        .getInputStream());
	      DataOutputStream out = new DataOutputStream(new FileOutputStream(
	        fileName));
	      byte[] buffer = new byte[4096];
	      int count = 0;
	      while ((count = in.read(buffer)) > 0) {
	       out.write(buffer, 0, count);
	      }
	      out.close();
	      in.close();
	      return true;
	     } catch (Exception e) {
	      System.out.println(e);
	      return false;
	     }
	    }
	    /**
	     * 兼容HTTP和FTP协议
	     * @param urlString
	     * @return
	     */
	    public String getDocumentAt(String urlString) {
	     StringBuffer document = new StringBuffer();
	     try {
	      URL url = new URL(urlString);
	      URLConnection conn = url.openConnection();
	      BufferedReader reader = new BufferedReader(new InputStreamReader(
	        conn.getInputStream()));
	      String line = null;
	      while ((line = reader.readLine()) != null) {
	       document.append(line + "\n");
	      }
	      reader.close();
	     } catch (MalformedURLException e) {
	      System.out.println("Unable to connect to URL: " + urlString);
	     } catch (IOException e) {
	      System.out.println("IOException when connecting to URL: "
	        + urlString);
	     }
	     return document.toString();
	    }
    
	/**
	 * 将角色bo转化为roleMap
	 * @param roleList
	 * @return
	 */
	public static Map<String, Object> transRoleBoToMap(RoleInfo roleBo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", (roleBo!=null)?roleBo.roleId:"");
		map.put("description", (roleBo!=null)?roleBo.description:"");
		return map;
	}
	
}
