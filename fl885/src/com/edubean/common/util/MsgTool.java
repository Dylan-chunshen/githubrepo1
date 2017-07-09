package com.edubean.common.util;  
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang.StringUtils;
/**
 * 发送短信的工具类
 * @author Dylan
 * @20151028
 */
public class MsgTool{
	
	/**
	 * 调用图元.net式的webservice来发送短信
	 * @param phoneNumbers   手机号码，通过;联接可发送多个
	 * @param content        手机发送内容
	 * @return
	 */
	public static String sendMsgByWS(String phoneNumbers,String content){
		// 0.1 参数及参数值获取
		String result          = "failed";
		String userName        = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgUserName"))?PropertyUtil.getStrProperty("msgUserName").trim():"";
		String userPassword    = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgUserPassword"))?PropertyUtil.getStrProperty("msgUserPassword").trim():"";
		phoneNumbers           = (StringUtils.isNotBlank(phoneNumbers))?phoneNumbers.trim():"";
		content                = (StringUtils.isNotBlank(content))?content.trim():"您已收到城管业务系统发文，请查收";
		String msgUrl          = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgUrl"))?PropertyUtil.getStrProperty("msgUrl").trim():"";
		String msgNamespace    = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgNamespace"))?PropertyUtil.getStrProperty("msgNamespace").trim():"";
		String msgMethodName   = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgMethodName"))?PropertyUtil.getStrProperty("msgMethodName").trim():"";
		String msgSoapActionURI= StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgSoapActionURI"))?PropertyUtil.getStrProperty("msgSoapActionURI").trim():"";
		
		// 0.2 工具类准备
		Service service = new Service();
		Call call       = null;
		try {
			call = (Call) service.createCall();
			
			// 1. 组装
			try {
				call.setTargetEndpointAddress(new java.net.URL(msgUrl));
			}catch(MalformedURLException e1) {e1.printStackTrace();}
			call.setUseSOAPAction(true); 
		    call.setSOAPActionURI(msgSoapActionURI);  
		    call.setOperationName(new QName(msgNamespace, msgMethodName));
		    call.setReturnType(XMLType.XSD_STRING);
		    
		    // 2. 参数录入
		    call.addParameter(new QName(msgNamespace,"userName"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    call.addParameter(new QName(msgNamespace,"userPassword"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    call.addParameter(new QName(msgNamespace,"content"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    call.addParameter(new QName(msgNamespace,"phoneNumbers"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    
		    // 3. 调用并打印结果
		    try {
				result = (String) "success,"+call.invoke(new Object[] { userName,userPassword,content,phoneNumbers });
			} catch (RemoteException e) {e.printStackTrace();}
		} catch (ServiceException e) {e.printStackTrace();}
		
		return result;
	}
	
	public static void main(String[] args){
		String abc = MsgTool.sendMsgByWS("18138226695", "节日快乐！");
		System.out.println(abc);
	}
}