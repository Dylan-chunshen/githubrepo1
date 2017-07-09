package com.edubean.common.util;
  
/**   
 * �����������ǣ�   ���ݵ��������㾭γ�ȷ�Χ
 */
public class DistanceUtil{
	public static double R = 6371.393; //����뾶(����)
	
	public static double getLngLatbyDistance(double distance, double latitude, boolean longitude){
		if(longitude){ //���㾭�ȣ�Ҫ�õ�γ��
			double r = R * Math.cos(Double.valueOf(latitude) * Math.PI / 180);
			return distance / (r * Math.PI / 180); //��������Ӧ�ľ���
		}
		else{ //����γ�ȣ��;����޹�
			return distance / (R * Math.PI / 180); //��������Ӧ��γ��
		}
	}
	
	/**   
	 * �˷������ǣ�  �ж�ĳһ���Ƿ��ڲο��� ��ĳһ��Χ��
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
