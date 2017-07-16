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
<%@include file="/js-library/jsp_common_dropdown.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="-1">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>���ɰ����-��ˮ���߷��ɷ���ƽ̨</title>
<meta name="keywords" content="���ɰ���������ɷ�������ʦ�����˾����ѷ�����ѯ" />
<meta name="description" content="���ɰ����- רҵ����O2Oƽ̨������������ʦ���״���ʦ����ģʽ�������������󻯣�ƽ̨�ݹ���ʦ����ѣ���ʦ��������������⸶������ʦ�����˾����ѷ�����ѯ�����ס�" />
<link rel="shortcut icon" type="image/ico" href="http://www.yingle.com/c/static/i/favicon-v4.png" />
<link rel="stylesheet" href="/css/index1.css" type="text/css" />
<link rel="stylesheet" href="/css/index4.css" type="text/css" />
<script type="text/javascript" src="/js/common2.js"></script>
<script>
$(document).ready(function(){
  $(".ui.menu .ui.dropdown").dropdown({
    on: 'hover'
  });
  $('.ui.menu a.item').on('click', function(){
      $(this)
        .addClass('active')
        .siblings()
        .removeClass('active')
      ;
    });
});

function MyAutoRun(){
	var a = document.body.scrollWidth;
	var b = document.body.scrollHeight;
	var c = (a-1080)/2;
	document.getElementById("indexDiv").style.marginLeft = c+'px';
}

function locationTo(url){
	locationto(url, "_blank",null);
}

/** ��ת��ע����� **/
function registerLocal(){
	var regUrl = "/login/registerPubUser.html";
	locationto(regUrl,"_blank",null);
}

/** ��ת����¼����   **/
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
							<a href="/c">��ҳ</a>
						</li>
						<li><a href="/c/faces/pc/secure/myyingle/home;jsessionid=8eb2f33f6a59677181c6be5532bd" class="ui-link ui-widget">�ҵİ��</a>
						</li>
						<li class="s-law dropdown ">
							<a href="javascript:;" class="j_dropdown" data-trigger="hover">���ɷ��� <span class="aw"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="/c/faces/pc/lawsuit/entry"><span class="icon1 icon-hammer"></span>����ί��</a>
								</li>
								<li>
									<a href="/c/faces/pc/consultation/submit"><span class="icon1 icon-bubble"></span>������ѯ</a>
								</li>
							</ul>
						</li>
						<li><a href="/c/faces/pc/aboutus;jsessionid=8eb2f33f6a59677181c6be5532bd" class="ui-link ui-widget">��������</a>
						</li>
					</ul>
				</nav>
				<%if(StringUtils.isNotBlank(userId)){%>
				<div class="header-logined style="float:left;">
					<div class="ui secondary menu" style="width:110px;margin-top:8px;">
						<div class="ui dropdown item">
							<table style="margin-top:0px;">
								<tr>
									<td><span><img src="/image/login/account.png" style="width:28px;height:28px;margin-top:0px;"/></span></td>
									<td style="font-size:12px;">&nbsp;<span><%=userName%><br/>
							          &nbsp;ID:<%=userCode %>
							    	</span></td>
								</tr>
					    	</table><i class="dropdown icon"></i>
						    <div class="menu">
						        <a class="item">������Ϣ</a>
						        <div class="divider"></div>
						        <a class="item">�ҵİ���</a>
						        <div class="divider"></div>
						        <a class="item">�ҵĹ�ע</a>
						        <div class="divider"></div>
						        <a onclick="javascript:locationTo('/login/userLogout.html','_self')" class="item">�˳���¼</a>
						      </div>
					    </div>
					    <div style="width:100px;margin-top:8px;display:inline;">
							<div style="width:30px;float:left;"><img src="/image/login/message.png" style="width:32px;height:32px;margin-top:0px;"/></div>
						</div>
					</div>
				</div>
				<%}else{%>
				<div class="header-login">
					<a href="#" onClick="javascript:loginLocal();" class="ui-link ui-widget btn btn-default">��¼</a>
					<a href="#" onClick="javascript:registerLocal();" class="ui-link ui-widget btn btn-default">ע��</a>
				</div>
				<%}%>
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
				<h2 style="height:80px;padding-bottom:5px;margin-bottom:20px;">��ˮ���Լ������߷��ɷ���ƽ̨</h2>
			</div>
		</div>
    </div>
</div>
 </body>
</html>