<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,net.sf.json.*,gov.adapter.oup.factory.OupServiceFactory,gov.adapter.oup.*"%>

<%@include file="../header_nocache.jsp"%>

<%

   String orgId=request.getParameter("orgId");
   String q=request.getParameter("q");
   String exclude=request.getParameter("exclude");

   String oup_service=request.getParameter("oup_service");
   OupServiceFactory factory=OupServiceFactory.getInstance();
   OupService service=factory.getService(oup_service);

   Organ organ=null;

   if(id!=null){
	   organ=service.findOrganById(id);
   }
   else if(orgId!=null&&!orgId.equals(""))
		organ=service.findOrganById(orgId);
   else
		organ=service.findRootOrgan();

   ArrayList list=this.findSubOrgans(organ,exclude);

   JSONArray dsJSON=JSONArray.fromObject(list);
   out.print(dsJSON.toString(1));

%>

<%!

private ArrayList findSubOrgans(Organ organ,String exclude){
	List subs=organ.getSubOrgans();
	ArrayList result=new ArrayList();
	for(int i=0;i<subs.size();i++){
	   Organ suborgan=(Organ)subs.get(i);
	   //System.out.println("exclude:"+exclude);
	   //System.out.println("suborgan:"+suborgan.getId());
	   //System.out.println(exclude!=null&&exclude.indexOf(suborgan.getId())!=-1);
	   if(exclude!=null&&exclude.indexOf(suborgan.getId())!=-1)
		   continue;

	   HashMap map=new HashMap();
	   map.put("id",suborgan.getId());
	   map.put("text",suborgan.getName());

		HashMap attr=new HashMap();
	   boolean flag=suborgan.hasSubOrgans();
	   if(flag){
			map.put("state","closed");
			attr.put("leaf","false");
	   }else
		   attr.put("leaf","true");
	   map.put("attributes",attr);
	   result.add(map);
   }
   return result;
}

private HashMap createOrgan(Organ organ){
   List subs=organ.getSubOrgans();
   ArrayList result=new ArrayList();
   for(int i=0;i<subs.size();i++){
	   Organ suborgan=(Organ)subs.get(i);

	   HashMap map=this.createOrgan(suborgan);
	   result.add(map);
   }

   HashMap self=new HashMap();
   self.put("id",organ.getId());
   self.put("text",organ.getName());
   self.put("children",result);
   self.put("state","closed");
   return self;
}

%>
