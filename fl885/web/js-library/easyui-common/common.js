
/**------------ loading object begin---------- **/

/**
 * ����װ�ض�����ʾ�����أ�����easyui-window
 * @type 
 * @author taohui
 * @since 2011-08-23
 */
var Loading = {
	id:"loading_" + Math.round(Math.random()*10000),
	show:function(imageUrl,title){
		var url = (undefined==imageUrl||""==imageUrl)?"/js-library/easyui-common/images/loading.gif":imageUrl;
		var loadingDiv = $('<div id="win_'+this.id+'"><div id="'+this.id+'" align="center"><img src="'+url+'"></img></div></div>')
			.appendTo($(document.body))
			.window({
				draggable:false,resizable:false,title:title,modal:true,
				noheader:(undefined==title||""==title)?true:false,
				collapsible:false,minimizable:false,maximizable:false,
				closable:false,width:204
			});
	},
	hide:function(){
		$("#win_" + this.id).window("close",true).remove();
	}
}

/**------------ loading object end---------- **/

/**
 * �ַ���ȫ�滻
 */
$.replaceStringAll = function(str,find,rep){
	var raRegExp = new RegExp(find.replace(/([\(\)\[\]\{\}\^\$\+\-\*\?\.\"\'\|\/\\])/g,"\\$1"),"ig");  
	return str.replace(raRegExp,rep);  
}
/**
 * ���16������ɫ
 */
$.randomHexColor = function(){
	return '#'+Math.floor(Math.random()*16777215).toString(16);
}
/**
 * ��ȡ��ַ������ֵ
 */
$.getLocationHrefParam = function(name){
	var reg = new RegExp('(^|\\?|&)'+name+'=([^&]*)(\\s|&|$)', 'i');  
    if(reg.test(location.href)) 
    	return RegExp.$2.replace(/\+/g, ' ');
    else
    	return '';
}

/**
 * ���ڸ�ʽ������
 */
/**
* ʱ�����ĸ�ʽ��
*/
$.dateFormat = function(date,format){
	var result = '';
	var o = {
		"M+" : date.getMonth() + 1,
		"d+" : date.getDate(),
		"h+" : date.getHours(),
		"m+" : date.getMinutes(),
		"s+" : date.getSeconds(),
		"q+" : Math.floor((date.getMonth() + 3) / 3),
		"S" : date.getMilliseconds()
	}

	if (/(y+)/.test(format)){
		format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4
		- RegExp.$1.length));
	}

	for (var k in o){
		if (new RegExp("(" + k + ")").test(format)){
			format = format.replace(RegExp.$1, RegExp.$1.length == 1
			? o[k]
			: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	result = format;
	return result;
}


/**
 * �������תΪrequest�����������
 * [{id:'100',name:'tom'},{id:'200',name:'jerry'},...]
 * transfer to
 * {id:['100','200',....],name:['tom','jerry',....]}
 * 
 **/
$.objInArray2Obj=function(arr){
	var result = new Object();
	for(var i=0;i<arr.length;i++){
		var r = arr[i];
		for (prop in r){
			var key = prop,value=r[prop];
			var arrKey = result[key];
			if(null==arrKey)
				arrKey = new Array();
			else
				arrKey = result[key];
			
			arrKey.push(value);
			result[key]=arrKey;
		}
	}
	return result;
};

/**
 * �ڶ����괦����ָ��ֵv 
 */
$.insertAtCursor=function(elementId, v){
	var ele = $('#'+elementId)[0];
    //IE support
    if (document.selection){
        ele.focus();
        sel = document.selection.createRange();
        sel.text = v;
        sel.select();
    }
    //MOZILLA/NETSCAPE support
    else if (ele.selectionStart || ele.selectionStart == '0'){
        var startPos = ele.selectionStart;
        var endPos = ele.selectionEnd;

        var restoreTop = ele.scrollTop;
        ele.value = ele.value.substring(0, startPos) + v + ele.value.substring(endPos, ele.value.length);
        if (restoreTop > 0)
            ele.scrollTop = restoreTop;
        
        ele.focus();
        ele.selectionStart = startPos + v.length;
        ele.selectionEnd = startPos + v.length;
    } else {
        ele.value += v;
        ele.focus();
    }
};
/**------------ form to json object begin---------- **/
/*
 * ��form�е�Ԫ��תΪjson����
 */
$.fn.extend({
	serializeObject:function(options){
		var p = new Object();
		$(this).find('input,select,radio,textarea').each(function(){
			var key = $(this).attr('name');
			var value = $(this).val();
			
			var type = $(this).attr('type');
			if(('radio'==type||'checkbox'==type)&&$(this).attr('checked')!='checked'){
				value = null;
			}
			
			if(null!=key){
				var arr = p[key];
				if(null==arr)
					arr = new Array();
				else
					arr = p[key];
				
				if(null!=value){
					arr.push(value);
					p[key]=arr;
				}
			}
		});
		return p;
	},
    fillForm:function(options){  
        var settings = jQuery.extend({  
            prefix:'' //����nameǰ׺  
            ,data:{} //����Ĭ��ֵ  
        },options);  
	    this.each(function(){  
	        var that =jQuery(this);  
	        if($.inArray(that.attr('tagName').toLowerCase(),['input','select','radio','textarea'])>-1){  
	        	if(null!=settings.data[that.attr('name')])
	        		that.val(settings.data[that.attr('name')]);  
	        }else{  
	            for(var item in settings.data){  
	                try{  
	                	if(null!=settings.data[item])
	                		that.find('[name=\''+settings.prefix+item+'\']').val(settings.data[item]);  
	                }catch(e){  
	                    if(window.console){  
	                        console.error('δ�ܻ�ȡԪ��:'+settings.prefix+item);  
	                    }  
	                }  
	            }  
	        }  
	    });  
    }  
    ,getForm:function(options){  
        var settings = jQuery.extend({  
            prefix:'' //����nameǰ׺  
            ,data:{} //����Ĭ��ֵ  
        },options);  

        var that =$(this);
        var o ={};  
        that.find('input,select,radio,textarea').each(function(){  
            var el = $(this);  
            var fieldFullName = el.attr('name');  
            if(null!=fieldFullName){
                var fieldName =fieldFullName.replace(settings.prefix,'');  
                if(settings.data[fieldName]){  
                    o[fieldName] =settings.data[fieldName];  
                    return;  
                }  
                if(settings.prefix && fieldFullName.indexOf(settings.prefix)<0 ){  
                    //������ǰ׺��������Ĭ��ֵ  
                    return;  
                }
                var values = o[fieldName];
                if(!values){
                	values = [];
                }
                values.push(el.val());
                o[fieldName] = values;
            }
        });  

        return o;  
    }  
});  
/**------------ form to json object end---------- **/


function gotoUrl(url,target){
	//var width = screen.availWidth-10;   
	//var height = screen.availHeight-20;
	//window.open('',_target,"top=0,left=0,width="+width+",height="+height+",scrollbars=yes,resizable=yes");
	_form_submit_url(url,null==target?'_blank':target);
}
function gotoLocation(url){
	_form_submit_url(url,'_self');
}
function _form_submit_url(url,target){
	url += (url.indexOf('?')>=0?'&':'?')+'r='+new Date().getTime();
	try{
		var random_form_id = new Date().getTime();
		var a = $('<a id="'+random_form_id+'" href="'+url+'" target="'+target+'"></a>').appendTo($(document.body));
		a[0].click();
		//$('<form id="'+random_form_id+'" action="'+url+'" target="'+target+'" method="post"></form>').appendTo($(document.body)).submit();
	}
	catch(e){
		alert(e);
	}
}
/**ȫ����һ���´���*/
function openAllscreenwin(winURL)
{
	var Allscreenwin = window.open(winURL,"","height=720,width=1015,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
	Allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}

/** ���д�һ���´��ڣ��߶ȣ�����Զ���*/

function openWindowInCenter(winURL,height,width)
{
	var top = (750 - height)/2;
	var left = (1259 - width)/2;
	var InCenterwin = window.open(winURL,"","height=" + height + ",width=" + width + ",top=" + top + ",left=" + left + ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");

}