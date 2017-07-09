<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,wad2.helper.DicHelper,net.sf.json.*,wad2.adapter.*"%>

<%@include file="../header_nocache.jsp"%>

<%

   String tcode=request.getParameter("tcode");
   String exclude=request.getParameter("exclude");
   List result=DicHelper.getInstance().getDicNameDisplayCode(tcode);
   Comparator orderCom = new Comparator(){ 
		public int compare(Object o1, Object o2){ 
			DicItemAdapter b1=(DicItemAdapter)o1; 
			DicItemAdapter b2=(DicItemAdapter)o2; 
			return (b1.getOrder().intValue()-b2.getOrder().intValue()); 
		} 
   }; 
   Collections.sort(result,orderCom);

   ArrayList list=new ArrayList();
   for(int i=0;i<result.size();i++){
	   DicItemAdapter item=(DicItemAdapter)result.get(i);
	   if(exclude!=null&&!"".equals(exclude)&&exclude.indexOf(item.getValue())!=-1)
		   continue;
	   HashMap map=new HashMap();
	   map.put("name",item.getName());
	   map.put("value",item.getValue());
	   list.add(map);
   }

   JSONArray dsJSON=JSONArray.fromObject(list);
   out.print(dsJSON.toString(1));

%>
