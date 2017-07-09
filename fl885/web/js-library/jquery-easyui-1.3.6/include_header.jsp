<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'include_header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%	
	String easyuiPath = request.getContextPath();
	easyuiPath += "/platform/assets/jquery-easyui-1.3.6";
%>
<link href="../../assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="../../assets/css/select.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=easyuiPath %>/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=easyuiPath %>/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=easyuiPath %>/demo/demo.css">
	
	<script type="text/javascript" src="<%=easyuiPath %>/jquery.min.js"></script>
	<script type="text/javascript" src="<%=easyuiPath %>/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=easyuiPath %>/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
 .datagrid-view1{
    	height:275px;
    }
    .datagrid-view2{
    	height:275px;
    }
</style>
  </head>
  
  <body>
  
  
  <script type="text/javascript">
  (function($){  
	  $.fn.serializeObject = function() {  
	    if ( !this.length ) { return false; }  
	  
	    var $el = this,  
	      data = {},  
	      lookup = data; //current reference of data  
	  
	      $el.find(':input[type!="checkbox"][type!="radio"], input:checked').each(function() {  
	        // data[a][b] becomes [ data, a, b ]  
	        var named = this.name.replace(/\[([^\]]+)?\]/g, ',$1').split(','),  
	            cap = named.length - 1,  
	            i = 0;  
	  
	        for ( ; i < cap; i++ ) {  
	            // move down the tree - create objects or array if necessary  
	            lookup = lookup[ named[i] ] = lookup[ named[i] ] ||  
	                ( named[i+1] == "" ? [] : {} );  
	        }  
	  
	        // at the end, psuh or assign the value  
	        if ( lookup.length != undefined ) {  
	             lookup.push( $(this).val() );  
	        }else {  
	              lookup[ named[ cap ] ]  = $(this).val();  
	        }  
	  
	        // assign the reference back to root  
	        lookup = data;  
	      });  
	  
	    return data;  
	  };  
	})(jQuery); 
  
  Date.prototype.format = function(fmt){
	    var year    =   this.getFullYear();
	    var month   =   this.getMonth()+1;
	    var date    =   this.getDate();
	    var hour    =   this.getHours();
	    var minute  =   this.getMinutes();
	    var second  =   this.getSeconds();

	    fmt = fmt.replace("yyyy",year);
	    fmt = fmt.replace("yy",year%100);
	    fmt = fmt.replace("MM",fix(month));
	    fmt = fmt.replace("dd",fix(this.getDate()));
	    fmt = fmt.replace("hh",fix(this.getHours()));
	    fmt = fmt.replace("mm",fix(this.getMinutes()));
	    fmt = fmt.replace("ss",fix(this.getSeconds()));
	    return fmt;
	    function fix(n){
	        return n<10?"0"+n:n;
	    }
	};
	function getDate(milsec){
		var d = new Date();
		d.setTime(milsec);
		return d.format("yyyy-MM-dd hh:mm:ss");
	}
	function getDate1(milsec){
		var d = new Date();
		d.setTime(milsec);
		return d.format("yyyy-MM-dd");
	}
  </script>
  </body>
</html>
