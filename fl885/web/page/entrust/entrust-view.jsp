<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="gov.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta content="text/html; charset=GBK" http-equiv="Content-Type" />
<title>�����һͬ����,�з���ĸ�������,���ʱ��ηָ� - ���ɰ����</title>
<meta name="keywords" content="���������ѯһ�����ʱ�ĲƲ��ָ����ˡ��������ģ�..." />
<meta name="description" content="���������ѯһ�����ʱ�ĲƲ��ָ����ˡ��������ģ� �Һ���̫̫��2006�����֤��2008���������˵�һ�׷��ӷ���֤�����Һ�̫̫2���˵����֣������ܼ�85��Ǯ��..." />
<link rel="shortcut icon" type="image/ico" href="http://www.yingcdn.com/c/static/i/favicon-v4.png" />
<link rel="stylesheet" href="../../css/consult1.css" type="text/css" />
<link rel="stylesheet" href="../../css/consult2.css" type="text/css" />
<script type="text/javascript" src="../../consult1.js"></script>
<script type="text/javascript" src="../../consult2.js"></script>
<script type="text/javascript" src="../../consult3.js"></script>
<script type="text/javascript" src="../../consult4.js"></script>	
<script type="text/javascript" src="../../consult5.js"></script>	
<%@include file="../../js-library/jsp_common_header.jsp"%>
</head>
<body id="page_consultation_detail">
        
    <div id="j_idt16" class="ui-confirm-dialog ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow ui-hidden-container">
		
			<div class="ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top">
			
				<span class="ui-dialog-title"></span>
				
					<a href="#" class="ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all">
				
						<span class="ui-icon ui-icon-closethick"></span>
						
					</a>
					
				</div>
					
					<div class="ui-dialog-content ui-widget-content">
					
					<span class="ui-icon ui-confirm-dialog-severity"></span>
					<span class="ui-confirm-dialog-message"></span>
					</div>
					
					<div class="ui-dialog-buttonpane ui-dialog-footer ui-widget-content ui-helper-clearfix">
					
						</div></div>
    <div class="wraper">
            
		
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
							<a href="/c/faces/pc/lawsuit/entry"><span class="icon icon-hammer"></span>����ί��</a>
						</li>
						<li>
							<a href="/c/faces/pc/consultation/submit"><span class="icon icon-bubble"></span>������ѯ</a>
						</li>
					</ul>
				</li>
				<li><a href="/c/faces/pc/aboutus;jsessionid=8eb2f33f6a59677181c6be5532bd" class="ui-link ui-widget">��������</a>
				</li>
			</ul>
		</nav>
		<div class="header-login">
			<a href="/c/faces/pc/login;jsessionid=8eb2f33f6a59677181c6be5532bd" class="ui-link ui-widget btn btn-default">��¼</a>
			<a href="/c/faces/pc/customer/register;jsessionid=8eb2f33f6a59677181c6be5532bd" class="ui-link ui-widget btn btn-default">ע��</a>
		</div>
	</div>
</header>


        <div class="container pb-6">
        
        <div class="container mb-2">
            <ol class="breadcrumb aside-breadcrumb">
                <li>
                    <a href="http://www.yingle.com/c/faces/pc/consultation/submit">������ѯ</a>
                </li>
                <li class="active">��������</li>
            </ol>
        </div>
        <div class="w-75-5">
            <div class="list-advice advice-detail">
                <div class="right">
                    <div class="body">
                        <div class="advice-detail-title clearfix">
                                <h4><c:choose>
                                <c:when test="${entrustBo.title!=null&&entrustBo.title!=''}">${entrustBo.title}</c:when>
                                <c:otherwise>�ҵ�����</c:otherwise></c:choose>
                                </h4>
                            <div class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entrustBo.createtime}"/></div>
                        </div>
                        <p>
                            <span class="pre">${entrustBo.content}</span>
                        </p>
                        <div class="tag">
                                    <a href="http://zixun.yingle.com/pc/cat-9" class="consultation-tag">���</a>
                                    <a href="http://zixun.yingle.com/pc/cat-7" class="consultation-tag">��������</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list-advice advice-reply">
                <div class="body">
                    <div class="advice-reply-title clearfix">
                        <a class="advisor" target="_blank" href="http://www.yingle.com/c/faces/pc/advisor/consultationadvisor?conid=7336&amp;hot=1">
                            <img src="http://www.yingcdn.com/c/static/i/pc/logo-ying-round.png" />
                            <div class="advice-reply-title-text">
                                <strong>���ɰ����<span class="badge-v">V</span></strong> <span class="desc">������ѯ �������</span>
                            </div>
                        </a>
                        <div class="handle">
                            <table>
                                <tr>
                                    <td class="td1">
                                        <i class="icon icon-like"></i>
                                    </td>
                                    <td class="td2">1</td>
                                    <td class="td3">���û�����</td>
                                </tr>
                                <tr>
                                    <td class="td1">
                                        <i class="icon icon-eye"></i>
                                    </td>
                                    <td class="td2">19</td>
                                    <td class="td3">���û��鿴</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="advice-reply-content"><div class="consultation-answer-container"><div class="consultation-answer-section"><div class="consultation-answer-title">Ӯ����Ϊ������</div><div class="consultation-answer-content"><span class="pre">���ã�����Ӯ�����ķ��ɹ��ʣ����������İ������ӷ��ɵĽǶ�Ϊ�������½�𣺷������ڷ��޹�ͬ�Ʋ���ȥ�������ӵķݶ��ƽ���ָ��������Է�Э�̾�����δ����ݣ����޷�Э��һ�£����������ߴ���</span></div></div></div>
                    </div>
                </div>
                <div class="status status-warning j_affix_flag">
                    <div class="clearfix">
                        <div class="advice-detail-operate">
                            <div class="like-bg"></div><a id="j_idt50" href="#" class="ui-commandlink ui-widget like" onclick="util.modalPopup($('#share-wechat'),'show');PrimeFaces.ab({s:&quot;j_idt50&quot;});return false;"></a>
                        </div>
                        <div class="advice-detail-link">
                            <p>��û����������⣿</p>
                            <p>
                                <a href="/c/faces/pc/consultation/submit" class="btn btn-primary">������ѯ</a> <a href="/c/faces/pc/lawsuit/entry" class="btn btn-primary">����ί��</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="affix-footer"> </div><div id="j_idt52" class="ui-outputpanel ui-widget"><div class="ui-outputpanel-loading ui-widget"></div></div><script id="j_idt52_s" type="text/javascript">$(function(){PrimeFaces.cw("OutputPanel","widget_j_idt52",{id:"j_idt52",widgetVar:"widget_j_idt52",deferred:true,deferredMode:"load"});});</script>
        </div><div id="j_idt61" class="ui-outputpanel ui-widget"><div class="ui-outputpanel-loading ui-widget"></div></div><script id="j_idt61_s" type="text/javascript">$(function(){PrimeFaces.cw("OutputPanel","widget_j_idt61",{id:"j_idt61",widgetVar:"widget_j_idt61",deferred:true,deferredMode:"load"});});</script><div id="j_idt63" class="ui-outputpanel ui-widget"><div class="ui-outputpanel-loading ui-widget"></div></div><script id="j_idt63_s" type="text/javascript">$(function(){PrimeFaces.cw("OutputPanel","widget_j_idt63",{id:"j_idt63",widgetVar:"widget_j_idt63",deferred:true,deferredMode:"load"});});</script>
        </div>
    <div class="float-buttons-wrapper">
        <div class="float-buttons-container">
            <ul>
                <li>
                    <a class="float-buttons-wechat-qr-code" href="javascript:;"><i class="iconfont icon-qr-code float-button-default"></i><span class="text text-line-1 float-button-hover">��ά��</span> <span class="float-buttons-qr-code-popup"> <img src="http://www.yingcdn.com/c/static/i/pc/yingle-wechat-qrcode-v2.jpg" />
                            <p>
                                ɨһɨ����Ӯ�����ٷ�΢�ź�<br />�˽ⷨ�ɳ�ʶ��Ԥ�����ɷ���<br />��ʱ�����������ķ��ɹܼ�
                            </p>
                    </span> </a>
                </li>
                <li>
                    <a class="float-buttons-feedback" href="javascript:;"><i class="iconfont icon-feedback float-button-default"></i><span class="text text-line-2 float-button-hover">�û�<br />����
                    </span></a>
                </li>
                <li>
                    <a class="float-buttons-top display-none" href="javascript:;"><i class="iconfont icon-arrow-scroll-top"></i></a>
                </li>
            </ul>
        </div>
    </div>

    <div class="feedback-modal">
        <div class="feedback-body">
            <div class="feedback-message">
                <i class="iconfont icon-done"></i>������������Ѿ��յ���лл��
            </div>
<form id="j_idt74" name="j_idt74" method="post" action="/c/zixun/pc/detail.xhtml;jsessionid=fafefb5d2e3c44afc02c913ba013" enctype="application/x-www-form-urlencoded">
<input type="hidden" name="j_idt74" value="j_idt74" />

                <div class="feedback-dialog">
                    <div class="feedback-title clearfix">
                        <h3>�������</h3>
                        <a href="javascript:;" class="j_close"><i class="iconfont icon-dialog-close"></i></a>
                    </div>
                    <div class="feedback-content mt-2">
                        <div class="form-group">
                            <p class="form-group-title">��������������������������������</p><textarea id="j_idt74:question" name="j_idt74:question" cols="20" rows="4" maxlength="1000" aria-required="true" class="ui-inputfield ui-inputtextarea ui-widget ui-state-default ui-corner-all ui-inputfield ui-inputtextarea ui-widget ui-state-default ui-corner-all form-control ui-inputtextarea-resizable" data-p-rmsg="���������ķ��������ǽ���ʱ�����Ľ�" data-p-required="true"></textarea><script id="j_idt74:question_s" type="text/javascript">$(function(){PrimeFaces.cw("InputTextarea","widget_j_idt74_question",{id:"j_idt74:question",widgetVar:"widget_j_idt74_question",autoResize:true,maxlength:1000});});</script>
                        </div>
                        <div class="form-group">
                            <p class="form-group-title">������������ϵ��ʽ</p><input id="j_idt74:mobilePhone" name="j_idt74:mobilePhone" type="text" placeholder="�ֻ�����" class="ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all form-control" data-p-vmsg="��������Ч��11λ�ֻ�����" data-p-regex="^1\d{10}" data-p-val="javax.faces.RegularExpression" /><script id="j_idt74:mobilePhone_s" type="text/javascript">PrimeFaces.cw("InputText","widget_j_idt74_mobilePhone",{id:"j_idt74:mobilePhone",widgetVar:"widget_j_idt74_mobilePhone"});</script><div id="j_idt74:j_idt77" class="ui-messages ui-widget" style="display:block;" aria-live="polite" data-global="false" data-summary="data-summary" data-severity="all,error" data-redisplay="true"></div><div id="j_idt74:j_idt78" class="ui-messages ui-widget" style="display:block;" aria-live="polite" data-global="false" data-summary="data-summary" data-severity="all,error" data-redisplay="true"></div>
                        </div>
                        <div class="buttons-holder mt-3"><a id="j_idt74:j_idt80" href="#" class="ui-commandlink ui-widget btn btn-primary" onclick="PrimeFaces.ab({s:&quot;j_idt74:j_idt80&quot;,u:&quot;j_idt74&quot;});return false;">�ύ����</a>
                        </div>
                    </div>
                </div><input type="hidden" name="javax.faces.ViewState" id="j_id1:javax.faces.ViewState:0" value="2560523692475670758:-6692912466449903225" autocomplete="off" />
</form>
        </div>
    </div><div id="j_idt83" class="ui-outputpanel ui-widget"><div class="ui-outputpanel-loading ui-widget"></div></div><script id="j_idt83_s" type="text/javascript">$(function(){PrimeFaces.cw("OutputPanel","widget_j_idt83",{id:"j_idt83",widgetVar:"widget_j_idt83",deferred:true,deferredMode:"load"});});</script>
    
    <div id="modal-notification-center" class="notification">
<form id="notificationMessages" name="notificationMessages" method="post" action="/c/zixun/pc/detail.xhtml;jsessionid=fafefb5d2e3c44afc02c913ba013" enctype="application/x-www-form-urlencoded">
<input type="hidden" name="notificationMessages" value="notificationMessages" />

            <div class="header">
                <div class="container">
                    <nav><a id="notificationMessages:j_idt94" href="#" class="ui-commandlink ui-widget active" onclick="PrimeFaces.ab({s:&quot;notificationMessages:j_idt94&quot;,u:&quot;headerForm:messageCount notificationMessages&quot;});return false;">δ����Ϣ</a><a id="notificationMessages:j_idt95" href="#" class="ui-commandlink ui-widget " onclick="PrimeFaces.ab({s:&quot;notificationMessages:j_idt95&quot;,u:&quot;headerForm:messageCount notificationMessages&quot;});return false;">ȫ����Ϣ</a>
                    </nav>
                    <div class="right"><a id="notificationMessages:j_idt97" href="#" class="ui-commandlink ui-widget" onclick="PrimeFaces.ab({s:&quot;notificationMessages:j_idt97&quot;,u:&quot;headerForm:messageCount notificationMessages&quot;});return false;">
                            <span class="icon icon-hook-gray"></span>���ȫ���Ѷ�
                    </a>
                        <span class="line"></span> <a class="nclose j_close" href="javascript:;"><span class="icon icon-close-xs"></span>�ر�</a>
                    </div>
                </div>
            </div>
            <div class="body">
                <div class="container"><div id="notificationMessages:j_idt100" class="ui-datatable ui-widget ui-datatable-scrollable notification-table"><div class="ui-widget-header ui-datatable-scrollable-header"><div class="ui-datatable-scrollable-header-box"><table role="grid"><thead id="notificationMessages:j_idt100_head"><tr role="row"><th id="notificationMessages:j_idt100:j_idt101" class="ui-state-default" role="columnheader"><span class="ui-column-title"></span></th></tr></thead></table></div></div><div class="ui-datatable-scrollable-body" tabindex="-1" style="height:500px"><table role="grid"><tbody id="notificationMessages:j_idt100_data" class="ui-datatable-data ui-widget-content"><tr class="ui-widget-content ui-datatable-empty-message"><td colspan="1">û��֪ͨ��Ϣ</td></tr></tbody></table></div><div class="ui-widget-header ui-datatable-scrollable-footer"><div class="ui-datatable-scrollable-footer-box"><table role="grid"></table></div></div><input type="hidden" id="notificationMessages:j_idt100_scrollState" name="notificationMessages:j_idt100_scrollState" autocomplete="off" value="0,0" /></div><script id="notificationMessages:j_idt100_s" type="text/javascript">$(function(){PrimeFaces.cw("DataTable","widget_notificationMessages_j_idt100",{id:"notificationMessages:j_idt100",widgetVar:"widget_notificationMessages_j_idt100",scrollable:true,liveScroll:true,scrollStep:20,scrollLimit:0,scrollHeight:"500",liveScrollBuffer:0});});</script>
                </div>
            </div><input type="hidden" name="javax.faces.ViewState" id="j_id1:javax.faces.ViewState:0" value="2560523692475670758:-6692912466449903225" autocomplete="off" />
</form>
    </div>
    </div>        
    </body>
</html>