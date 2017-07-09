<%	
	String jsLibraryPath = request.getContextPath();
	
	//easyui version£¬default is 1.2.1
	String easyuiVersion = request.getParameter("ev");
	easyuiVersion = (null==easyuiVersion||"".equals(easyuiVersion))?"1.3.1":easyuiVersion;
	
	//whether load annex 
	String isAnnex = request.getParameter("isAnnex");
	//whether load tooltip
	String isTooltip = request.getParameter("isTooltip");

%>

<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/easyui-common/themes/default/easyui.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/easyui-common/themes/icon.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">

<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/datePicker/WdatePicker.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-common/ajax.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<!--script type="text/vbscript" src="<%=jsLibraryPath%>/js-library/easyui-common/ajax.vbs"></script-->

<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-<%=easyuiVersion%>/jquery.min.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-<%=easyuiVersion%>/jquery.easyui.min.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-common/gov/easyui-gov.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-common/common.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-common/easyui.validatebox.extend.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-common/easyui.datagrid.editors.extend.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-<%=easyuiVersion%>/locale/easyui-lang-zh_CN.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/easyui-common/gov/ValidateFunction.min.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>

<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/autocomplete/jquery.autocomplete.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>" />
<script type='text/javascript' src="<%=jsLibraryPath%>/js-library/plugins/autocomplete/jquery.autocomplete.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>

<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/jquery.json.min.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/json2.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/jquery.cookie.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/jquery.uuid.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/jquery.serializeObject.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>



<%if("true".equals(isAnnex)) {%>
	<script type='text/javascript' src="<%=request.getContextPath()%>/annex/core/codebase/annex/swfupload.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/annex/core/codebase/annex/handlers.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/annex/core/codebase/annex/annex.jsp"></script>
<%}%>
<%if("true".equals(isTooltip)){%>
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/tooltip/tip-darkgray/tip-darkgray.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/tooltip/tip-green/tip-green.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/tooltip/tip-skyblue/tip-skyblue.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/tooltip/tip-twitter/tip-twitter.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/tooltip/tip-violet/tip-violet.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/tooltip/tip-yellow/tip-yellow.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="<%=jsLibraryPath%>/js-library/plugins/tooltip/tip-yellowsimple/tip-yellowsimple.css?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>">
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/tooltip/jquery.poshytip.min.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<%}%>

<!-- ´úÂë±à¼­Æ÷ -->
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/editarea/edit_area_full.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript">
editAreaLoader.baseURL="<%=jsLibraryPath%>/js-library/plugins/editarea/";
</script>


<!--[if lte IE 6]>
<script type="text/javascript" src="<%=jsLibraryPath%>/js-library/plugins/DD_belatedPNG_0.0.8a.js?r=<%=Math.round(Math.random()*200000)%><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript">
    DD_belatedPNG.fix('div, ul, img, li, input , a');
</script>
<![endif]--> 
