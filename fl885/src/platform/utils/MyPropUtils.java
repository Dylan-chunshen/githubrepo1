package platform.utils;

import java.util.Properties;

/**
 * 读取配置文件内容
 * 
 * @author PP
 * @version 1.0
 */
public class MyPropUtils {

	public static String getParam(String column){
		Properties p = new Properties();
		String param = "";
		try {
			p.load(MyPropUtils.class.getClassLoader()
					.getResourceAsStream("unitCode.properties"));
			param = p.getProperty(column);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取数据库配置文件失败！");
		}
		return param;
	} 
	
	public static void main(String[] args) {
		System.out.println(getParam("MG310117109208"));
	}
}
