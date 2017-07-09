package com.edubean.common.util;

import gov.base.dict.bo.DictBo;
import gov.base.dict.service.DictService;
import gov.util.spring.SpringContext;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import wegov.platform.RightQHelper;
import wfc.facility.right.exception.RightException;
import wfc.facility.util.string.StringDateUtil;
public class CommonTool {
/**
 * ��ȡ�����ֵ�
 * appId:ҵ������
 * dictId�����ڵ�id
 * */ 
@SuppressWarnings({ "rawtypes", "unchecked" })
public static List getTwoTree(String appId,String dictId){ 
	List treeList=new ArrayList();  
	List<DictBo> oneList=list(appId,dictId);
	for (DictBo dictBo : oneList) { 
		JSONObject oo = new JSONObject();
		oo.element("pid", dictBo.getPid());
		oo.element("id", dictBo.getId());
		oo.element("text", dictBo.getName());
		List<DictBo> twoList = getTwoTree(dictBo.getAppId(),dictBo.getId());
		if(twoList != null && twoList.size() > 0){
			oo.element("state","closed");
			oo.element("leaf", "false");
			oo.element("children", twoList);
		}else{
			oo.element("state","close");
			oo.element("leaf", "true");
		}
		treeList.add(oo);  
	}  
	return treeList;
} 
/**
 * ��ȡ�ֵ�
 *appId:ҵ������
 * dictId�����ڵ�id 
 * */
public static List<DictBo> list(String appId,String dictId){
	DictService dictService = (DictService) SpringContext.getInstance().getBean("gov.dictService");
	List<DictBo> list = dictService.getDictsByAppIdAndPid(appId,dictId);
	return list;
}
/**
 * ʱ���ַ���֮���ת��
*/
public static final Date string2Date(String date){
	if(date == null || "".equals(date))
		return new Date(0); 
	return StringDateUtil.getString2Date(date,"yyyy-mm-dd hh:MM:ss");
}

public static final String date2String(Date date){
	if(date == null)
		return "";
	return StringDateUtil.getDate2String(date, "yyyy-mm-dd hh:MM:ss");
}
	
	/**
	 * ת�� jsp ������ conditionStr �� HashMap
	 * @param conditionStr
	 * @return HashMap<String, Object>
	 */
	public static HashMap<String, Object> transStrToMap(String conditionStr){
		HashMap<String, Object> conditions = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(conditionStr)){
			try {
				conditionStr = URLDecoder.decode(conditionStr,"GBK");
				String[] strArrs = conditionStr.split("&");
				if(strArrs!=null&&strArrs.length>0){
					for(int i=0;i<strArrs.length;i++){
						String[] KeyValue = strArrs[i].split("=");
						if(KeyValue!=null&&KeyValue.length>1){
							conditions.put(KeyValue[0], KeyValue[1]);
						}
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		
		return conditions;
		
	}
	
	/**
	 * ��ĳ�����Ӽ�����Ŀ��·��
	 * @param path
	 * @return String ����·��
	 */
	public static String addWebRoot(Object pathObj){
		String wholePath = "";
		if(pathObj!=null&&pathObj instanceof String){
			String path = (String)pathObj;
			String webroot =System.getProperty("web.root"); 
			if(StringUtils.isNotBlank(path)&&StringUtils.isNotBlank(webroot)){
				wholePath = (webroot+path).replace("//", "/");
			}
		}
		return wholePath;
	}
	
	/**
	 * ת��ĳ���ļ�·��Ϊ��������
	 * @param path
	 * @return String ����·��
	 */
	@SuppressWarnings("resource")
	public static byte[] getBytesFromFilePath(String filePath){
		if(StringUtils.isNotBlank(filePath)){
			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(filePath.toString().trim()));
				if(bis!=null){
					byte[] buf = new byte[bis.available()];
					while ((bis.read(buf)) != -1) {}
					if(buf!=null){return buf;}
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * ͨ��java������ƻ�ȡĳ����ĳ�����ӹ�ĳ������ֵ
	 * @param className
	 * @param methodName
	 * @param paramValue
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getResultByClassMethodParam(String className, String methodName, String paramValue){
		String result = "";
		try {
			if(StringUtils.isNotBlank(className)&&StringUtils.isNotBlank(methodName)){
				Object object = Class.forName(className).newInstance();
				Class clazz   = object.getClass();
				Method getMethod = clazz.getMethod(methodName,String.class);
				Object value = getMethod.invoke(object, paramValue);
				if(value!=null){
					result = value.toString().trim();
				}
			}
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��ȡĳ��·���µĶ���������
	 * @param path
	 * @return String ����·��
	 */
	public static byte[] getBytesOfImagePath(Object pathObj){
		byte[] bytesR = null;
		if(pathObj!=null&&pathObj instanceof String){
			bytesR = getBytesFromFilePath(addWebRoot(pathObj));
		}
		return bytesR;
	}
	
	/**
	 * ��ȡ��������
	 * @param jzType   ���ִ���
	 * @return String  ��������
	 */
	public static String getJzName(String jzType){
		String jzName = "";
		if(StringUtils.isNotBlank(jzType)){
			RightQHelper roleHelper;
			try {
				roleHelper = new RightQHelper();
				jzName = roleHelper.getRole("JXKH", jzType).description;
			} catch (RightException e) {
				e.printStackTrace();
			}
		}
		return jzName;
	}
	
	/**
	 * �������ִ��ĳ�����set����
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object invokeMethodOfSet(Object classEntity, String methodName, Object[] args){
		Object object = null;
		Class ownerClass = classEntity.getClass();
		Class[] argsClass= new Class[args.length];
		for(int i=0;i<args.length;i++){
			argsClass[i] = args[i].getClass();
		}
		Method method = null;
		try {
			method = ownerClass.getMethod(methodName, argsClass);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		try {
			object = method.invoke(classEntity, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * �������ִ��ĳ�����set���� ����ֻ��һ��
	 * @param owner
	 * @param methodName
	 * @param arg ����ֻ��һ��
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object invokeMethodOfSet(Object classEntity, String methodName, String arg){
		Object object = null;
		Class ownerClass = classEntity.getClass();
		Class[] argsClass= new Class[1];
		argsClass[0] = arg.getClass();
		
		Method method = null;
		try {
			method = ownerClass.getMethod(methodName, argsClass);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		try {
			object = method.invoke(classEntity, arg);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;
	}
}
