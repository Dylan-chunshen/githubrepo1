<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*,wad2.helper.DicHelper,net.sf.json.*,wad2.adapter.*,wad2.onekey.topic.domain.*"%>

<%

HashMap hmReturn = new HashMap();
    	
    	Iterator itKey = request.getParameterMap().keySet().iterator();
    	while(itKey.hasNext()){
    		String sParameterName = ""+itKey.next();
			String[]  values=(String[])request.getParameterMap().get(sParameterName);

			if("".equals(values[0]))
				continue;
    	}


class InnerTestBo{
	private String innerName="innerName";

	public String getInnerName(){
		return innerName;
	}
}


class TestBo{
	private String name="";
	private String hidden="";

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setHidden(String name){
		this.hidden=name;
	}

	public String getHidden(){
		return hidden;
	}

	public InnerTestBo getInnerBo(){
		return new InnerTestBo();
	}
}


//********************************************************************************************

TestBo ti=new TestBo();
ti.setName("testname");
ti.setHidden("true");

String cols=request.getParameter("cols");
String[] colAry=cols.split(",");

String page1=request.getParameter("page");

   ArrayList list=new ArrayList();
   for(int i=0;i<10;i++){
	   HashMap map=new HashMap();
	   for(int j=0;j<colAry.length;j++){
		   String name=colAry[j];
		   Object value=wad2.util.OgnlEx.getValue(name,ti);
		   map.put(name,value);
	   }
	   list.add(map);
   }

   HashMap map=new HashMap();
   map.put("rows",list);
   map.put("total",new Integer(40));

   JSONObject dsJSON=JSONObject.fromObject(map);
   out.print(dsJSON.toString(1));

%>

