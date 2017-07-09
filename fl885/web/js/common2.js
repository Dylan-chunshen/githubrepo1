function getWidth(cell) {
    if (!cell)return 0;
    return parseInt(domUtils.getComputedStyle(cell, "width"), 10);
}

//顶部导航
function ontop(selfObj){
	var ul=document.getElementById("top_navigation").getElementsByTagName("li");
	var ullength = ul.length;
	for(i=0; i<ullength; i++){
		ul[i].className = "";
	}
	    selfObj.parentNode.className = "selected";
}

function outtop(selfObj){
	var ul=document.getElementById("top_navigation").getElementsByTagName("li");
	var ullength = ul.length;
	for(i=0; i<ullength; i++){
		ul[i].className = "";
	}
	    selfObj.parentNode.className = " ";
}

//左侧菜单
function showMenu(self,obj) {
    if(obj.style.display=="") {
		self.className="c_actived"
		obj.style.display="none";
		
	} else {
	    obj.style.display="";
		self.className="c"
	}
}

//TAB选项卡特效
function selectTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
	
}
 
 
 /**
  * 重新加载dadagrid
  * @param formId 
  * @param gridId
  * @return
  */
 function form_query(formId,gridId){
		var param = new Object();

		var fields = $("#"+formId).serializeArray();
		$(fields).each(function() {
			if(param[this.name]!=null)
				param[this.name]+=";"+this.value;
			else
				param[this.name]=this.value;
		});
		//显示第一页数据 
		$("#"+gridId).commonGrid('options').pageNumber = 1;  
        //分页栏上跳转到第一页  
		$("#"+gridId).commonGrid('getPager').pagination({pageNumber: 1});  
		$("#"+gridId).commonGrid("reload",param);
	}
//隐藏工具栏时表格自动调整
window.onresize=function resizeGrid() {
   try {
     var num=document.body.clientWidth-20;
     $('#tt,#result').datagrid('resize', {width:num});
   } catch(ex) {
   }
}
/** 页面跳转*/
function locationto(winURL,target,params){
	var f = document.createElement("form");
	document.body.appendChild(f);
	f.action=winURL;
	f.method="post";
	f.target=target;
	if(params!=undefined) {
	  try{
		for(var elem in params){
			var input=document.createElement("input");
			input.setAttribute("type","hidden");  //设置属性
			input.setAttribute("name",elem);
			input.setAttribute("value",String(params[elem]));
			f.appendChild(input); 
		}
	  } catch(ex){}
	}
	f.submit();
	document.removeChild(f);
} 
function openAllscreenwin(winURL)
{
	var Allscreenwin = window.open(winURL,"","height=720,width=1015,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
	Allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}
  
function openWindowInCenter(winURL,height,width)
{
	var top = (750 - height)/2;
	var left = (1259 - width)/2;
	var InCenterwin = window.open(winURL,"","height=" + height + ",width=" + width + ",top=" + top + ",left=" + left + ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");

}

function getQueryString(sName) 
{ 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");    
	var r = window.location.search.substr(1).match(reg);    
	if (r != null) return unescape(r[2]); 
	return null;    
}
function assembleToObj(formElement,fieldName,omitSelectName){
    var obj=new Object();
    var inputs=formElement.getElementsByTagName("input");
    var textareas=formElement.getElementsByTagName("textarea");
    var selects=formElement.getElementsByTagName("select");

    if(!fieldName)
    	fieldName="id";
    for(var i=0;i<inputs.length;i++){
    	$(obj).attr($(inputs[i]).attr(fieldName),inputs[i].value);
    }  

    for(var i=0;i<textareas.length;i++){
    	$(obj).attr($(textareas[i]).attr(fieldName),textareas[i].value);
    } 

    for(var i=0;i<selects.length;i++){
   	    $(obj).attr($(selects[i]).attr(fieldName),$(selects[i]).combobox('getValue'));
		if(!omitSelectName||omitSelectName=='false'){
			var textName=$(selects[i]).attr("textName");
			if(textName){
				$(obj).attr(textName,$(selects[i]).combobox('getText'));
			}
			else
    			$(obj).attr($(selects[i]).attr(fieldName)+'Name',$(selects[i]).combobox('getText'));
		}
    }

    return obj;
}

/**
 * 开打一个ifrmae窗口（关闭方法 在子页面执行parent.closeIframe()）
 * @param src
 * @param title
 * @param width
 * @param heigth
 * @return
 */
function openIframe(src,title,width,height) {
	if (typeof jQuery == 'undefined') {
		alert("请引入jquery环境！")
	}
	var openDivId="_openDiv";
	var openIframeId="_openIframe";
	var openDiv=$('<div id="'+openDivId+'" style="height:'+height+'px;width:'+width+'px;display:none" ><iframe id="'+openIframeId+'" width="100%"  frameborder="0" height="98%"  scrolling="auto"></iframe></div>');
	if($("#"+openDivId).length==0) {
		$("body").append(openDiv);
		$("body").append("<script>function closeIframe(){$('#"+openDivId+"').window('close')}</script>");
	}
	$('#'+openDivId).show().window({
		  title:title,
		  width:width,
		  height:height,
		  modal: true,
		  shadow: true,
		  maximizable:false,
		  minimizable:false,
		  collapsible:false,
		  closed: false,
		  resizable:false,
		  onClose:function(){
			$('#'+openIframeId).attr("src","");
		  }
	});
	$('#'+openIframeId).attr("src",src);
	$('#'+openIframeId).attr("width",width-15);
	$('#'+openIframeId).attr("height",height-38);
}
/** 
 * datagrid宽度
 */
function getWidth(percent){
    return document.body.clientWidth*percent; 
}
/** 
 * Form表单的值转化成一个Javascript对象
 * 
 */
$.fn.serializeObject = function() {
	 var o = {};
	 var a = this.serializeArray();
	 $.each(a, function() {
	 if (o[this.name] !== undefined) {
	 if (!o[this.name].push) {
	 o[this.name] = [o[this.name]];
	}
	 o[this.name].push(this.value || '');
	 } else {
	 o[this.name] = this.value || '';
	}
	});
	 return o;
	};
