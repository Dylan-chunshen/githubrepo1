<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,wad2.helper.DicHelper,net.sf.json.*,wad2.adapter.*,wegov.component.pub.common.Code"%>

<%@include file="../header_nocache.jsp"%>

<%
//System.out.println("ssssss****************11:"+request.getParameter("q"));
   String tcode=request.getParameter("tcode");
   String queryKey=request.getParameter("queryKey");
   //String q=request.getParameter("q");

   if(queryKey!=null){
	   tcode=queryKey;
   }

   List result=DicHelper.getInstance().getDicNameDisplayCode(tcode);
   
   ArrayList list=new ArrayList();
   for(int i=0;i<result.size();i++){
	   Code item=(Code)result.get(i);
	   //if(q!=null&&!hasSubCodesForQ(item.getId(),item.getName(),q))
		//   continue;

	   HashMap map=new HashMap();
	   map.put("id",item.getValue());
	   map.put("text",item.getName());

	   HashMap attr=new HashMap();
	   boolean flag=this.hasSubCodes(item.getId());
	   if(flag){
		 map.put("state","closed");
		 attr.put("leaf","false");
		 attr.put("queryKey",item.getId());
	   }else
		   attr.put("leaf","true");
	   map.put("attributes",attr);
	   list.add(map);
   }
   JSONArray dsJSON=JSONArray.fromObject(list);
   out.print(dsJSON.toString(1));

%>

<%!

private boolean hasSubCodes(String code){
	List result=DicHelper.getInstance().getDicNameDisplayCode(code);
	return result.size()!=0;
}

/**
private boolean hasSubCodesForQ(String code,String text,String q){
	List result=DicHelper.getInstance().getDicNameDisplayCode(code);
	int size=result.size();
	if(text.indexOf(q)!=-1){
		return true;
	}else if(size>0){
		for(int i=0;i<size;i++){
			Code item=(Code)result.get(i);
			if(hasSubCodesForQ(item.getId(),item.getName(),q))
				return true;
		}
	}
	return false;
}**/

%>
