package platform.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件内容
 * 
 * @author PP
 * @version 1.0
 */
public class PropertiesUtils {

	public Properties getProperties(String propName) {
		Properties props = new Properties();
		String url = this.getClass().getClassLoader().getResource(propName)
				.toString().substring(6);
		String empUrl = url.replace("%20", " ");// 如果你的文件路径中包含空格，是必定会报错的
		System.out.println(empUrl);
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(empUrl));
			props.load(in);
			return props;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("没有读取到配置文件！");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("没有读取到配置文件！");
		}
		return null;
	}
	
	public static String getTownname(String column){
		Properties p = new Properties();
		String param = "";
		try {
			p.load(PropertiesUtils.class.getClassLoader()
					.getResourceAsStream("townname.properties"));
			param = p.getProperty(column);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取数据库配置文件失败！");
		}
		return param;
	} 
	public static PropertiesUtils getInstance(){
		PropertiesUtils prop = new PropertiesUtils();
		return prop;
	}
	
}
