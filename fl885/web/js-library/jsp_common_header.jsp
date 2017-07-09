<%	String jsLibraryPath = request.getContextPath(); %>
<style type="text/css">
.formbody{
    width:document.body.clientWidth;
	height:document.body.clientHeight;
}   
.tablelist td{
	text-align: center;
	text-indent: 0px;
}
.tablelist th{
	text-align: center;
	text-indent: 0px;
}

.progressbar-text {
  text-align: left;
  color: black;
  position: absolute;
}
.pagin .paginList{
  position: absolute;
  left: 600px; 
  top: 0px;
  text-align:left;
  width:400px;
}
.datagrid-row-selected a:link , .datagrid-row-selected a:visited {
	color:#fff;
}
</style>
<!-- link href="/css/public.css" rel="stylesheet" type="text/css"/>
<link href="/css/select.css" rel="stylesheet" type="text/css"/-->

<link href="<%=jsLibraryPath%>/js-library/easyui-1.2.1/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=jsLibraryPath%>/js-library/easyui-1.2.1/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=jsLibraryPath%>/js-library/jquery-easyui-1.3.6/themes/gray/datagrid.css" rel="stylesheet" type="text/css" />
<script src="<%=jsLibraryPath%>/js/jquery.js" type="text/javascript"></script>
<script src="<%=jsLibraryPath%>/js-library/jquery-easyui-1.3.6/jquery.min.js" type="text/javascript" ></script>
<script src="<%=jsLibraryPath%>/js/jquery.idTabs.min.js" type="text/javascript"></script>
<script src="<%=jsLibraryPath%>/js/select-ui.min.js" type="text/javascript"></script>
<script src="<%=jsLibraryPath%>/js-library/jquery-easyui-1.3.6/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="<%=jsLibraryPath%>/js-library/datePicker/WdatePicker.js" type="text/javascript"></script>
<script src="<%=jsLibraryPath%>/js/common2.js" type="text/javascript"></script>
<script src="<%=jsLibraryPath%>/js-library/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
