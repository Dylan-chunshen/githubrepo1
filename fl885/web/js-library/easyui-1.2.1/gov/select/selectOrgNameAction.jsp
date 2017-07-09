<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,net.sf.json.*,gov.adapter.oup.factory.OupServiceFactory,gov.adapter.oup.*"%>

<%@include file="../header_nocache.jsp"%>

<%

   String defaultValue=request.getParameter("defaultValue");
   String oup_service=request.getParameter("oup_service");
   OupServiceFactory factory=OupServiceFactory.getInstance();
   OupService service=factory.getService(oup_service);

   Organ organ=service.findOrganById(defaultValue);

   String result="";
   if(organ!=null){
	   result=organ.getName();
   }
   out.print(result);
%>
