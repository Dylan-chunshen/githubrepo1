package com.edubean.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.edubean.common.util.ApiTool.RequestMethodType;

import net.sf.json.JSONObject;


/**
 * IP处理工具类
 * @author Dylan
 * @on 2017-07-02
 */
public class IPUtil {
	private static String taobaoSearchUrl = "http://ip.taobao.com/service/getIpInfo.php";
	
	/**
	 * 获取IP的具体位置信息
	 * @param ip
	 * @return
	 */
	public static String getIPInfoOf(String ip){
		String result = null;
		if(StringUtils.isNotBlank(ip)){
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("ip", ip.trim());
			result = ApiTool.request(taobaoSearchUrl,paramMap,RequestMethodType.POST);
		}
		return result;
	}
	
	/**
	 * 获取指定Ip的地理位置信息
	 * @param ip
	 * @return
	 */
	public static Map<String, String> getIPMap(String ip){
		Map<String, String> ipMap = new HashMap<String, String>();
		String ipString = IPUtil.getIPInfoOf(ip);
		if(StringUtils.isNotBlank(ipString)){
			JSONObject jsonObj = (JSONObject) JSONObject.fromObject(ipString);
			if(jsonObj!=null){
				JSONObject ipDataJObj = jsonObj.getJSONObject("data");
				if(ipDataJObj!=null){
					String region = (ipDataJObj.containsKey("region"))?Native2AsciiUtils.ascii2Native(ipDataJObj.get("region").toString()):"";
					String region_id = (ipDataJObj.containsKey("region_id"))?ipDataJObj.get("region_id").toString():"";
					String city = (ipDataJObj.containsKey("city"))?Native2AsciiUtils.ascii2Native(ipDataJObj.get("city").toString().trim()):"";
					String city_id = (ipDataJObj.containsKey("city_id"))?Native2AsciiUtils.ascii2Native(ipDataJObj.get("city_id").toString().trim()):"";
					ipMap.put("region", region);
					ipMap.put("region_id", region_id);
					ipMap.put("city", city);
					ipMap.put("city_id", city_id);
				}
			}
		}
		return ipMap;
	}
	
	public static void main(String[] args){
		Map<String, String> ipMap = IPUtil.getIPMap("113.251.56.78");
		System.out.println("=========="+ipMap.get("region"));
		System.out.println("=========="+ipMap.get("region_id"));
		System.out.println("=========="+ipMap.get("city"));
		System.out.println("=========="+ipMap.get("city_id"));
	}
	
}
