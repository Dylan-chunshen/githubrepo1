package com.edubean.common.util;
  
/**   
 * 此类描述的是：   根据地面距离计算经纬度范围
 */
public class DistanceUtil{
	public static double R = 6371.393; //地球半径(公里)
	
	public static double getLngLatbyDistance(double distance, double latitude, boolean longitude){
		if(longitude){ //计算经度，要用到纬度
			double r = R * Math.cos(Double.valueOf(latitude) * Math.PI / 180);
			return distance / (r * Math.PI / 180); //公里数对应的经度
		}
		else{ //计算纬度，和经度无关
			return distance / (R * Math.PI / 180); //公里数对应的纬度
		}
	}
	
	/**   
	 * 此方法的是：  判断某一点是否在参考点 的某一范围内
	 */
	public static boolean isPointBelong(double objLog, double objLat, double centerLog, double centerLat, double range)
	{
		double logLimits=getLngLatbyDistance(range,centerLat,true);
		double latLimits=getLngLatbyDistance(range,centerLat,false);
		double lngValue=Math.abs(centerLog-objLog);
		double latValue=Math.abs(centerLat-objLat);
		if(logLimits>lngValue && latLimits>latValue)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static void main(String args) {
	}
	
}
