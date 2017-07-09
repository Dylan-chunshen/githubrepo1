<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,net.sf.json.*,gov.adapter.oup.factory.OupServiceFactory,gov.adapter.oup.*"%>

<%
   String orgName=request.getParameter("q");
   if(orgName==null)
		orgName="";
   String oup_service="gov.adapter.oup.wegov.WegovService";
   OupServiceFactory factory=OupServiceFactory.getInstance();
   OupService service=factory.getService(oup_service);

	Organ organ;
			organ=service.findOrganById("67072");

		//organ=service.findRootOrgan();

   List subs=organ.getSubOrgans();
   ArrayList result=new ArrayList();
   for(int i=0;i<subs.size();i++){
	   Organ suborgan=(Organ)subs.get(i);
	   if(!suborgan.getName().startsWith(orgName))
		   continue;
	   HashMap map=new HashMap();
	   map.put("value",suborgan.getId());
	   map.put("name",suborgan.getName());
	   result.add(map);
   }

   HashMap h=new HashMap();
   h.put("rows",result);

   JSONObject dsJSON=JSONObject.fromObject(h);
   out.print(dsJSON.toString(1));

%>
