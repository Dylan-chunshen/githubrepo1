package platform.utils;

import java.util.Properties;

/**
 * ��ȡ�����ļ�����
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
			throw new RuntimeException("��ȡ���ݿ������ļ�ʧ�ܣ�");
		}
		return param;
	} 
	
	public static void main(String[] args) {
		System.out.println(getParam("MG310117109208"));
	}
}
