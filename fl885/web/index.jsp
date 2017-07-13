<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="platform.login.bo.PubUserBo"%>
<%
String currentRole = (String)session.getAttribute("CURRENTROLE");
String userId = "";
String userCode = "";
String userName = "";
if(StringUtils.isNotBlank(currentRole)&&"PUBUSER".equalsIgnoreCase(currentRole)){
	PubUserBo currentPerson = (session.getAttribute("CURRENTPERSON")!=null)?(PubUserBo)session.getAttribute("CURRENTPERSON"):null;
	if(currentPerson!=null){
		userId = currentPerson.getPublic_user_id();
		userCode = currentPerson.getPublic_user_code();
		userName = currentPerson.getPublic_user_name();
	}
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/js-library/jsp_common_header.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>法律帮帮网-丽水在线法律服务平台</title>
<meta name="keywords" content="法律帮帮网，法律服务，找律师，打官司，免费法律咨询" />
<meta name="description" content="法律帮帮网- 专业法律O2O平台，海量优秀律师，首创律师竞标模式，让你的利益最大化；平台暂管律师服务费，律师服务出问题先行赔付。找律师，打官司，免费法律咨询更靠谱。" />
<link rel="shortcut icon" type="image/ico" href="http://www.yingle.com/c/static/i/favicon-v4.png" />
<link rel="stylesheet" href="/css/index1.css" type="text/css" />
<link rel="stylesheet" href="/css/index4.css" type="text/css" />
<script type="text/javascript" src="/js/common2.js"></script>
<script>
function MyAutoRun(){
	var a = document.body.scrollWidth;
	var b = document.body.scrollHeight;
	var c = (a-1080)/2;
	document.getElementById("indexDiv").style.marginLeft = c+'px';
}

function locationTo(url){
	locationto(url, "_blank",null);
}

/** 跳转到注册界面 **/
function registerLocal(){
	var regUrl = "/login/registerPubUser.html";
	locationto(regUrl,"_blank",null);
}

/** 跳转到登录界面   **/
function loginLocal(){
	var regUrl = "/login/loginPubUser.html";
	locationto(regUrl,"_blank",null);
}
</script>
</head>
<body onLoad="javascript:MyAutoRun();">
<div class="wraper" style="background:rgb(255,255,255);">
	<form id="headerForm" name="headerForm" method="post" action="/c/faces/pc/index;jsessionid=8eb2f33f6a59677181c6be5532bd" enctype="application/x-www-form-urlencoded">
		<input type="hidden" name="headerForm" value="headerForm" />
		<header class="index-header" style="border-bottom-color:rgb(204,204,204);border-bottom: 1px solid rgb(204,204,204);">
			 <div class="index-container">
				<div class="brand index-brand">
					<a href="/c"></a>
				</div>
				<nav class="index-nav-main">
					<ul>
						<li class="index-header-first active">
							<a href="/c">首页</a>
						</li>
						<li><a href="/c/faces/pc/secure/myyingle/home;jsessionid=8eb2f33f6a59677181c6be5532bd" class="ui-link ui-widget">我的帮帮</a>
						</li>
						<li class="s-law dropdown ">
							<a href="javascript:;" class="j_dropdown" data-trigger="hover">法律服务 <span class="aw"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="/c/faces/pc/lawsuit/entry"><span class="icon icon-hammer"></span>案件委托</a>
								</li>
								<li>
									<a href="/c/faces/pc/consultation/submit"><span class="icon icon-bubble"></span>法律咨询</a>
								</li>
							</ul>
						</li>
						<li><a href="/c/faces/pc/aboutus;jsessionid=8eb2f33f6a59677181c6be5532bd" class="ui-link ui-widget">关于我们</a>
						</li>
					</ul>
				</nav>
				<div class="header-login">
				<%if(StringUtils.isNotBlank(userId)){%>
				    <a href="#" class="ui-link ui-widget btn btn-default"><%=userName+"("+userCode+")" %></a>
				<%}else{%>
					<a href="#" onClick="javascript:loginLocal();" class="ui-link ui-widget btn btn-default">登录</a>
					<a href="#" onClick="javascript:registerLocal();" class="ui-link ui-widget btn btn-default">注册</a>
				<%}%>
				</div>
			</div>
		</header>	
		<input type="hidden" name="javax.faces.ViewState" id="j_id1:javax.faces.ViewState:0" value="-1579162567859092081:-7547364213251950871" autocomplete="off" />
	</form>
        
	<div class="index-content">
		<div class="index-banner">
			<div class="banner-main" style="height:360px;margin-bottom:0px;">
				<div id="indexDiv" style="height:500px;background-image:url(/image/index_1.png);background-repeat:no-repeat;width:1080px;height:350px;margin-top:0px;padding-top:0px;">
					<div onmouseover="this.style.cursor='pointer'" style="height:360px;width:50%;float:left;" onclick="javascript:locationTo('/entrust/entrust-edit.html')"></div>
					<div onmouseover="this.style.cursor='pointer'" style="height:360px;width:50%;float:left;" onclick="javascript:locationTo('/consult/consult-edit.html')"></div>
				</div>
				<h2 style="height:80px;padding-bottom:5px;margin-bottom:20px;">丽水人自己的在线法律服务平台</h2>
			</div>
		</div>
    </div>
            
</div>
 </body>
</html>