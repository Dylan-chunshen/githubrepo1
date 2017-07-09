<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,net.sf.json.*,gov.adapter.oup.factory.OupServiceFactory,gov.adapter.oup.*"%>

<%@include file="../header_nocache.jsp"%>

<%

   String orgId=request.getParameter("orgId");
   String oup_service=request.getParameter("oup_service");
   OupServiceFactory factory=OupServiceFactory.getInstance();
   OupService service=factory.getService(oup_service);

   Organ organ=service.findOrganById(orgId);

   List subs=organ.getSubOrgans();
   ArrayList result=new ArrayList();
   for(int i=0;i<subs.size();i++){
	   Organ suborgan=(Organ)subs.get(i);
	   HashMap map=new HashMap();
	   map.put("value",suborgan.getId());
	   map.put("name",suborgan.getName());
	   result.add(map);
   }

   JSONArray dsJSON=JSONArray.fromObject(result);
   out.print(dsJSON.toString(1));

%>
