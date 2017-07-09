package com.edubean.common.util;

import java.io.InputStream;
import java.util.Properties;
  
/**   
 * 此类描述的是：   读取szlg.properties文件里面配置的信息
 */
public class PropertyUtil{
	public static Properties szlgProperty =PropertyUtil.getProperty("/fl885.properties");
	public static Properties getProperty(String path){
		 Properties prop = new Properties();      
		 try     
		 {  
			InputStream is =PropertyUtil.class.getResourceAsStream(path); 
			prop.load(is);    
			if(is!=null)      
				is.close();
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return prop; 
	}
	
	public static Properties getSzlgProperty(){
		return szlgProperty;
	}
	
	public static int getIntProperty(String propertyName){
		Object property = getSzlgProperty().get(propertyName);
		if(property==null ||property.equals("")){
			return 0;
		}
		try {
			return Integer.parseInt(property.toString());
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String getStrProperty(String propertyName){
		Object property = getSzlgProperty().get(propertyName);
		return clearBlank((property!=null)?property.toString():"");
	}
	
	public static String clearBlank(String val){
		if(val==null)
			return "";
		return val.trim();
	}
	
	public static void main(String args) {
		System.out.println("adb");
		System.out.println(PropertyUtil.getStrProperty("appSearchFar"));
	}
	
}
