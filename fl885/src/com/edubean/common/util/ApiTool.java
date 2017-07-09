package com.edubean.common.util;  
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
/**
 * 调用接口工具类
 * @author NoGun,Dylan
 * @20150901
 */
public class ApiTool{
	
	/**
	 * 编码类型
	 */
	public static final class EncodeType{
		/** GBK **/
		public static final String GBK = "GBK";
		/** UTF-8 **/
		public static final String UTF = "UTF-8";
		/** ISO-8859-1 **/
		public static final String ISO = "ISO-8859-1";
	}
	
	/**
	 * 接口调用方法
	 */
	public static final class RequestMethodType{
		/** POST **/
		public static final String POST = "POST";
		/** GET **/
		public static final String GET = "GET";
	}
	
	/**
	 * 获取httpUrl的结果集
	 * @param <E>
	 * @param httpUrl
	 * @param httpArg
	 * @return
	 */
	public static String request(String httpUrl,String requestMethodType){
//		返回值、是否压缩编码状态值、默认编码类型[UTF-8],接口调用方式[默认POST]
		StringBuffer result = new StringBuffer();
		boolean gzipFlag = false;
		String encodType = EncodeType.UTF;
		requestMethodType = (StringUtils.isNotBlank(requestMethodType))?requestMethodType.trim():RequestMethodType.POST;
		
	    if(StringUtils.isNotBlank(httpUrl)){
	    	try {
	 	       URL url = new URL(httpUrl);
	 	       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	 	       connection.setRequestMethod(requestMethodType);
	 	       
//	 	                 获取编码模式和格式
	 	       List<String> encodList     = connection.getHeaderFields().get("Content-Encoding");
	 	       List<String> encodTypeList = connection.getHeaderFields().get("Content-Type");
	 	       if(encodList!=null&&encodList.contains("gzip")){
	 	    	  gzipFlag = true;
	 	       }
	 	       if(encodTypeList!=null&&encodTypeList.size()>0){
	 	    	  for(int i=0;i<encodTypeList.size();i++){
	 	    		  String charset = (String)encodTypeList.get(i);
	 	    		  if(charset.indexOf("charset")>-1){
	 	    			  if(charset.indexOf("gbk")>-1){
	 	    				 encodType = EncodeType.GBK;
	 	    			  }else if(charset.indexOf("iso")>-1){
	 	    				 encodType = EncodeType.ISO;
	 	    			  }
	 	    		  }
	 	    	  } 
	 	       }
	 	       
//	 	       connection链接获取二进制流
	 	       connection.connect();
	 	       InputStream is = connection.getInputStream();
	 	       byte[] bytes = null;
	 	       if(gzipFlag){
	 	    	   GZIPInputStream gzipstream=new GZIPInputStream(is);
		 	       bytes = inputStreamToByte(gzipstream);
	 	       }else{
	 	    	   bytes = inputStreamToByte(is);
	 	       }
	 	       
//	 	                 生成字符串
	 		   BufferedInputStream isNew = new BufferedInputStream(new ByteArrayInputStream(bytes));
	 		   BufferedReader readStr = new BufferedReader(new InputStreamReader(isNew, encodType));
	 		   int c = 0;
	 		   while ((c=readStr.read())!=-1){
	 			  result.append((char) c);
	 		   }
	 	       readStr.close();
	 	     } catch (Exception e) {
	 	       e.printStackTrace();
	 	     }
	    } 
	    
	     return result.toString();
    }
	
	/**
	 * 二进制流转InputStream
	 * @param buf
	 * @return
	 */
	public static final InputStream byteToInputStream(byte[] buf){  
		return new ByteArrayInputStream(buf);  
	}
	
	/**
	 * InputStream转二进制流
	 * @param InputStream
	 * @return
	 * @throws IOException
	 */
	public static final byte[] inputStreamToByte(InputStream inStream)throws IOException{  
	    ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	    byte[] buff = new byte[100];  
	    int rc = 0;  
	    while ((rc = inStream.read(buff, 0, 100)) > 0) {  
	       swapStream.write(buff, 0, rc);  
	    }  
	    byte[] in2b = swapStream.toByteArray();  
	    return in2b;  
	}
	
	/**
	 * POST 调用接口url获取string结果
	 * @param httpUrl  基本url(without 参数)
	 * @param paramMap 参数map
	 * @param requestMethodType POST/GET
	 * @return
	 */
	public static String request(String httpUrl,Map<String, String> paramMap, String requestMethodType){
		String jsonResultStr = "";
		List<NameValuePair> parametersBodyList = new ArrayList<NameValuePair>();
		
		if(StringUtils.isNotBlank(httpUrl)){
//			基本工具类与基本属性准备
			HttpClient httpClient = new HttpClient();
	    	PostMethod method = new PostMethod(httpUrl);
	    	method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
	    	
//	    	获取POST参数与值，处理并set进method
	    	if(paramMap!=null&&paramMap.size()>0){
				Iterator<Entry<String, String>> iter = paramMap.entrySet().iterator();
				while(iter.hasNext()){
					Entry<String, String> entry = (Entry<String, String>)iter.next();
					String key  = entry.getKey();
					String value= entry.getValue();
					parametersBodyList.add(new NameValuePair(key,value));
				}
			}
			if(parametersBodyList!=null&&parametersBodyList.size()>0){
				NameValuePair[] nameValuePairArr = new NameValuePair[parametersBodyList.size()];
		    	parametersBodyList.toArray(nameValuePairArr);
		    	method.setRequestBody(nameValuePairArr);
			}
			
//			发起调用获取结果string串
			try {
				httpClient.executeMethod(method);
				jsonResultStr = method.getResponseBodyAsString();
			} catch (HttpException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	    return jsonResultStr.toString();
    }
	
    public static void main(String[] args){
    	String httpUrl = "http://ip.taobao.com/service/getIpInfo.php";
//	     String httpUrl = "http://112.90.87.26:8088/smartcityservice.ashx?a=getsitedetail&sign=810EC6FD3BDE0F4A3AAB729CC87303CF";
//	     String httpUrl2 = "http://112.90.87.26:8088/smartcityservice.ashx";
//	     String httpUrl = "http://www.2773456.com:8088/main?xwl=23XJB738C8LX&orderType=asc";
//	     String jsonResult = request(httpUrl,RequestMethodType.POST);
//	     System.out.println(jsonResult);
	     
	     Map<String, String> paramMap = new HashMap<String, String>();
	     paramMap.put("ip", "58.251.160.199");
	     String jsonResult2 = request(httpUrl,paramMap,RequestMethodType.POST);
	     System.out.println(jsonResult2);
	     System.out.println(gov.util.date.DateUtil.getStrDate(new Date(), "yyyy-MM-dd hh:mm:ss"));
	     
//	     JSONObject jsonObj = (JSONObject) JSONObject.fromObject(jsonResult);
//	     net.sf.json.JSONArray jsonArray = jsonObj.getJSONArray("data");
//	     JSONObject jObj = jsonArray.getJSONObject(0);
//	     System.out.println(jObj.getString("name"));
    }
}