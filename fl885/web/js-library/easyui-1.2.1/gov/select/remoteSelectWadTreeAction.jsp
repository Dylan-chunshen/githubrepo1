<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,wad2.helper.DicHelper,net.sf.json.*,wad2.adapter.*,wegov.component.pub.common.Code"%>

<%@include file="../header_nocache.jsp"%>

<%
   String tcode=request.getParameter("tcode");
   String queryKey=request.getParameter("queryKey");
   String q=request.getParameter("q");

   List result=DicHelper.getInstance().getDicNameDisplayCode(tcode);
   
   ArrayList list=hasSubCodesForQ(result,q);

   JSONArray dsJSON=JSONArray.fromObject(list);
   out.print(dsJSON.toString(1));

%>

<%!

private ArrayList hasSubCodesForQ(List codes,String q){
	ArrayList list=new ArrayList();
	for(int i=0;i<codes.size();i++){
		Code item=(Code)codes.get(i);
		String text=item.getName();
		HashMap map=new HashMap();
		List children=DicHelper.getInstance().getDicNameDisplayCode(item.getId());
		ArrayList realChildren=hasSubCodesForQ(children,q);
		if(q==null||text.indexOf(q)!=-1||realChildren.size()>0){
			map.put("id",item.getValue());
			map.put("text",text);
			HashMap attr=new HashMap();
			if(children.size()!=0){
				map.put("state","closed");
				map.put("children",realChildren);
				attr.put("leaf","false");
			}else
				attr.put("leaf","true");
			map.put("attributes",attr);
			list.add(map);
		}				
	}
	return list;
}

%>
