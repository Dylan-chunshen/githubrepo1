<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*,java.util.ArrayList,wad2.report.handler.matrix.*,wad2.report.web.*"%>


<%

HashMap hmReturn = new HashMap();
    	
    	Iterator itKey = request.getParameterMap().keySet().iterator();
    	while(itKey.hasNext()){
    		String sParameterName = ""+itKey.next();
			String[]  values=(String[])request.getParameterMap().get(sParameterName);

			if("".equals(values[0]))
				continue;

			String vstr="";
			if(values.length>1){
				for( int i=0;i<values.length;i++)
					vstr+=","+values[i];
				out.print(sParameterName+" = "+vstr+"<br>");
			}else
				out.print(sParameterName+" = "+values[0]+"<br>");
    	}

%>