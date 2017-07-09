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
 * ���Ͷ��ŵĹ�����
 * @author Dylan
 * @20151028
 */
public class MsgTool{
	
	/**
	 * ����ͼԪ.netʽ��webservice�����Ͷ���
	 * @param phoneNumbers   �ֻ����룬ͨ��;���ӿɷ��Ͷ��
	 * @param content        �ֻ���������
	 * @return
	 */
	public static String sendMsgByWS(String phoneNumbers,String content){
		// 0.1 ����������ֵ��ȡ
		String result          = "failed";
		String userName        = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgUserName"))?PropertyUtil.getStrProperty("msgUserName").trim():"";
		String userPassword    = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgUserPassword"))?PropertyUtil.getStrProperty("msgUserPassword").trim():"";
		phoneNumbers           = (StringUtils.isNotBlank(phoneNumbers))?phoneNumbers.trim():"";
		content                = (StringUtils.isNotBlank(content))?content.trim():"�����յ��ǹ�ҵ��ϵͳ���ģ������";
		String msgUrl          = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgUrl"))?PropertyUtil.getStrProperty("msgUrl").trim():"";
		String msgNamespace    = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgNamespace"))?PropertyUtil.getStrProperty("msgNamespace").trim():"";
		String msgMethodName   = StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgMethodName"))?PropertyUtil.getStrProperty("msgMethodName").trim():"";
		String msgSoapActionURI= StringUtils.isNotBlank(PropertyUtil.getStrProperty("msgSoapActionURI"))?PropertyUtil.getStrProperty("msgSoapActionURI").trim():"";
		
		// 0.2 ������׼��
		Service service = new Service();
		Call call       = null;
		try {
			call = (Call) service.createCall();
			
			// 1. ��װ
			try {
				call.setTargetEndpointAddress(new java.net.URL(msgUrl));
			}catch(MalformedURLException e1) {e1.printStackTrace();}
			call.setUseSOAPAction(true); 
		    call.setSOAPActionURI(msgSoapActionURI);  
		    call.setOperationName(new QName(msgNamespace, msgMethodName));
		    call.setReturnType(XMLType.XSD_STRING);
		    
		    // 2. ����¼��
		    call.addParameter(new QName(msgNamespace,"userName"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    call.addParameter(new QName(msgNamespace,"userPassword"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    call.addParameter(new QName(msgNamespace,"content"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    call.addParameter(new QName(msgNamespace,"phoneNumbers"),org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
		    
		    // 3. ���ò���ӡ���
		    try {
				result = (String) "success,"+call.invoke(new Object[] { userName,userPassword,content,phoneNumbers });
			} catch (RemoteException e) {e.printStackTrace();}
		} catch (ServiceException e) {e.printStackTrace();}
		
		return result;
	}
	
	public static void main(String[] args){
		String abc = MsgTool.sendMsgByWS("18138226695", "���տ��֣�");
		System.out.println(abc);
	}
}