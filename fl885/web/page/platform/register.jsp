<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="gov.util.*" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/js-library/jsp_common_header.jsp"%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>ע�ᷨ�ɰ�������˻�</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, width=device-width, minimal-ui">
<link rel="stylesheet" href="../../css/register3.css"/>
<link rel="stylesheet" href="../../css/register4.css"/>
<script type="text/javascript" src="/js/common2.js"></script>
<script>
function registerPubUser(){
	alert("77777777777777");
	var phone = $("#phone").val();
	var name = $("#name").val();
	var password = $("#password").val();
	var regPubUserUrl = "/login/registerPubUser.action";
	locationto(regPubUserUrl,"_self",{"phone":phone;"name":name;"password":password});
}
</script>
</head>
<body class="signup zh">
    <section class="content">
		<div class="topbanner">
			<div class="poswrap">
				<a class="brand" href="http://localhost/" ><h1>fl885</h1></a>
			</div>
		</div>
		<div class="form-unit">
			<div class="tips"></div>
			<form action="/login/registerPubUser.action" method="post" class="auth-form email-form form-horizontal">
				<div class="form-field">
					<input type="text" name="phone" id="phone" placeholder="�ֻ���" autocomplete="off" value="" required class="form-control email" autofocus />
				</div>
				<div class="form-field">
					<input type="text" name="name" placeholder='��������' autocomplete="off" value="" required class="form-control name" />
				</div>
				<div class="form-field passwordClear">
					<input type="text" name="password" placeholder="���루��ĸ�����֣�����6λ��" autocomplete="off" required class="form-control password" />
				</div>
				<button type="submit" data-gta="event" data-label="signup-direct" class="btn btn-primary anim-blue-all submit">
					ע ��<span class="icon icon-upload"></span>
				</button>
			</form>
			<form class="auth-form phone-form" action="" method="post" style="display: none;">
				<div class="form-field">
					<div class="phone-country form-control">
						<div class="phone-number-wrap">
							<input type="tel" name="phone" placeholder="�ֻ���" autocomplete="off" value="" class="form-control phone phone-number cea-placeholder" autofocus required autocomplete="off"/>
						</div>
					</div>
					<div class="verify-image-wrap" data-host="https://auth_services.teambition.com/captcha">
						<div class="loading-indicator">
							<span class="loader-dot"></span>
							<span class="loader-dot"></span>
							<span class="loader-dot"></span>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary verification anim-blue-all">
					��һ��<span class="icon icon-upload"></span>
				</button>
			</form>
			<form action="" method="post" class="auth-form create-bind-form" style="display: none;">
			  <input type="hidden" name="source" value="teambition">
			  <input type="hidden" name="next_url" value="">
			  <input type="hidden" name="phone" value="">
			  <div class="form-field">
				<input type="email" name="email" id="email" placeholder="����" autocomplete="off" value="" required class="form-control email" autofocus />
			  </div>
			  <div class="form-field username">
				<input type="text" name="name" placeholder='��������' autocomplete="off" value="" required class="form-control name" />
			  </div>
			  <div class="form-field passwordClear">
				<input type="text" placeholder="���루��ĸ�����֣�����6λ��" autocomplete="off" name="password" required class="form-control password" />
			  </div>
			  <button type="submit" data-gta="event" data-label="signup-direct" class="btn btn-primary submit anim-blue-all">
				<span class="btn-text">ע ��</span><span class="icon icon-upload"></span>
			  </button>
			</form>
			<div class="privacy-field">
			  ���ע�ᣬ��ͬ�����ǵ� <a href="https://www.teambition.com/privacy?p=signup&s=account" target="_blank">�û���˽����</a>
			</div>
			<div class="login-links">
			  <div class="horizontal-line"></div>
				<a href="/login?" class="btn btn-primary anim-blue">�����˺ţ���¼<span class="icon icon-upload"></span></a>
			</div>
      </div>
    </section>
  </body>
</html>
