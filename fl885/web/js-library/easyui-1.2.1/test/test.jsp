<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.List,java.util.ArrayList,wad2.report.handler.matrix.*,wad2.report.web.*"%>
<html>

<script type="text/javascript" src="../gov/ValidateFunction.js"></script>
<link href="/sqjz/css/style.css" rel="stylesheet" type="text/css" />

	<%@include file="../../jslib_header.jsp"%>
<head>
<title>统计图</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">


</head>
<body >

<form id='ff' action="test2.jsp">
<table>
<tr><td>
<div id='c'>
 <select id="s1" name="s1" class="select-wad-code" style="width:40%" required="true" tcode="SQJZ_XZQH" textName="s1textName"  enable_blank="false" displayName="aa" defaultValue="2097152"></select>
</div>
</td></tr>
<tr><td>

<div id='d' width="100%">
  <select id="s4" name="s4" editable="true" class="select-wad-code" required="true" tcode="SQJZ_ZCLX" tree="true" defaultValue="262656"  displayName="组织机构" defaultText='过失' style="width:30%" textName="s4textName" freeInput="true"></select>

    <select id="s44" name="s44" editable="true" class="query-wad-code" required="true" tcode="SQJZ_ZCLX" tree="true" defaultValue="SQJZ_ZCLX_177"  displayName="组织机构" defaultText='过失致人死亡罪' style="width:30%" textName="s4textName" freeInput="true"></select>
</div>
</td></tr>

<tr><td class="table_content">

    <select id="s2" name="s2" class="select-suborg" style="width:200px;" required="true" orgId="67072" textName="s2textName" >
    </select>
  
  <select id="s3" name="s3" exclude="T0" class="select-suborg" style="width:200px;" defaultValue='65795' required="true" tree="true" textName="s3textName" onlyLeafSelected="true">
  </select>
<b>爱爱爱</b>
  <input class="input_bg" >
</td></tr>
<tr><td>

<div id='ttt'>
  <select id="s5" name="s5" style="width:250px;" defaultText='dd' defaultValue='11' freeInput="true" disabled></select>
</div>

</td></tr>

</table>
<!--demotest:<select id="demotest" name="demotest" class="select-demo-code" style="width:200px;" required="true" tcode="tcodetest"></select>-->


<input type="checkbox" checked name="cktest" value="true">
  <input type="submit"  value="submit" >
</form>


<input type="button"  value="aaa" onclick="onclick2()">

<input type="button"  value="bbb" onclick="onclick5()">


<input type="button" id='aaa' value="aaa" onclick="onclick5()">


<div id="loadDiv">
</div>



<script>

  $("#s3"). commonCombo(
        {
           onLoadSuccess:function(){
			   //alert('dd');
              $('#s3').combotree("setText","2222");
           }
        });
 
//$('#s3').combobox("onLoad","2222");
//$("#loadDiv").load("loadTest.jsp");

function onclick5(){
	alert($('#c').html());
	//alert($('#loadDiv').html());
	//$("#s44").combotree('setValue','SQJZ_ZCLX_177');
}


	
function onclick2(){
	
	//alert($('#c').find("input")[1].value);
	//alert($('s1').next().html());
	//var cur=$("#s3").combotree('getValue');
	//alert(cur);
	//tttt();
	//validValue(document.getElementById('s4'));	
	//alert($('#d').html());
	//validForm(document.getElementById('ff'));
	//$($(obj).next()).css({ width: "100%" });
	//alert($('#d').html());
	//$("#ff").form("validate")
	//alert('dd');
	$('#s1').combobox("setText","");
	
	//alert($('#ttt').html());
	//$('#s1').commonCombo('setReadOnly',false);

}

	$('#s1').commonCombo('setReadOnly',false);





$(function(){

	$("#s1"). commonCombo(
        {
           onChange:function(newValue, oldValue){
              //alert('d');
           }
        }
       );

	

	//setReadOnly($('#s1'));
	//alert('here');
	//document.write($('#s1').next().children()[2]+"");
	//$('#s1').combobox("disable");
	//$('#s1').next().children()[2].setAttribute('disabled','true');

	//alert($('#s1').next().children()[2]);

	//$('#s1').combobox('setValue','');
	//$('#s1').combobox("disable");


	$('#s5').commonComboGrid({
				panelWidth:450,
				idField:'value',
				textField:'name',
				mode:'remote',
				editable:true,
				url:'combogridTestAction.jsp',
				columns:[[
					{field:'value',title:'Value',width:60},
					{field:'name',title:'Name',width:100}
				]],
				onBlur:function(){
					//alert('b');
					objBlur(this);
				}
	});

	//$('#s5').combo("disable");
});



function objBlur(obj){
			var textField = $(obj).combogrid("options")["textField"];
			var text = $(obj).combogrid('getText');
		    var panelClosed=$(obj).combogrid('panel').panel("options").closed;
			if(text==''||!panelClosed)
				return;
			var arr = $(obj).combogrid('grid').datagrid('getData').rows;
			for(var i=0;i<arr.length;i++){
				if(text==arr[i][textField]){
					return;
				}
			}
			$(obj).combogrid('clear');
			//$(obj).combogrid('grid').datagrid('clearSelections');
			$(obj).combogrid('grid').datagrid('reload',{});
}




</script>

</body>


</html>