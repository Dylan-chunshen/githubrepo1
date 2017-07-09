package platform.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ��ȡ�����ļ�����
 * 
 * @author PP
 * @version 1.0
 */
public class PropertiesUtils {

	public Properties getProperties(String propName) {
		Properties props = new Properties();
		String url = this.getClass().getClassLoader().getResource(propName)
				.toString().substring(6);
		String empUrl = url.replace("%20", " ");// �������ļ�·���а����ո��Ǳض��ᱨ���
		System.out.println(empUrl);
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(empUrl));
			props.load(in);
			return props;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("û�ж�ȡ�������ļ���");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("û�ж�ȡ�������ļ���");
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
			throw new RuntimeException("��ȡ���ݿ������ļ�ʧ�ܣ�");
		}
		return param;
	} 
	public static PropertiesUtils getInstance(){
		PropertiesUtils prop = new PropertiesUtils();
		return prop;
	}
	
}
