<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,wad2.helper.DicHelper,net.sf.json.*,wad2.adapter.*"%>

<%@include file="../header_nocache.jsp"%>

<%
   String tcode=request.getParameter("tcode");
   String defaultValue=request.getParameter("defaultValue");
   DicItemAdapter dic=DicHelper.getInstance().getDicByValue(tcode,defaultValue);

   String result="";
   if(dic!=null){
	   result=dic.getName();
   }
   out.print(result);
%>
