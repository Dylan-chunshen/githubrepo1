var g_path = "/js-library/easyui-common/gov/";
var oup_service="gov.adapter.oup.wegov.WegovService";
$(function(){

		load_easyui_gov();

});

function reload_easyui_gov(parent){
	var p = null==parent?$(document.body):$(parent);
	p.find("select.select-wad-code").each(function(index){
		 var tcode=$(this).attr("tcode");
		 $(this).commonCombo({
					treeAction:g_path+'select/selectWadTreeAction.jsp?tcode='+tcode,
					textAction:g_path+'select/selectWadCodeNameAction.jsp?tcode='+tcode,
					selectAction:g_path+'select/selectWadAction.jsp?tcode='+tcode
		   });
			
     });

	p.find("select.commonCombo").each(function(index){
		 $(this).commonCombo({
					selectAction:$(this).attr("selectAction")
		   });
			
     });

	p.find("select.select-remote-wad-code").each(function(index){
		 var tcode=$(this).attr("tcode");
		 $(this).remoteComboTree({
					treeAction:g_path+'select/remoteSelectWadTreeAction.jsp?tcode='+tcode,
					textAction:g_path+'select/remoteSelectWadCodeNameAction.jsp?tcode='+tcode
		   });
			
     });

	p.find("select.select-suborg").each(function(index){
			var orgId=$(this).attr("orgId");
			if(!orgId)
				orgId='';

			$(this).commonCombo({
					treeAction:g_path+'select/selectOrgTreeAction.jsp?oup_service='+oup_service+'&orgId='+orgId,
					textAction:g_path+'select/selectOrgNameAction.jsp?oup_service='+oup_service,
					selectAction:g_path+'select/selectOrgAction.jsp?oup_service='+oup_service+'&orgId='+orgId
		   });			
     });

	p.find("select.select-demo-code").each(function(index){
		 var tcode=$(this).attr("tcode");
		 $(this).commonCombo({
					treeAction:g_path+'select/selectDemoDicAction.jsp?tcode='+tcode,
					textAction:'',
					selectAction:g_path+'select/selectDemoDicAction.jsp?tcode='+tcode
		   });
			
     });
}
function load_easyui_gov(scope){
	//通过wad注入字典的方式获取下拉框
       $("select.select-wad-code",scope).each(function(index){
			 var tcode=$(this).attr("tcode");
			 $(this).commonCombo({
						treeAction:g_path+'select/selectWadTreeAction.jsp?tcode='+tcode,
						textAction:g_path+'select/selectWadCodeNameAction.jsp?tcode='+tcode,
						selectAction:g_path+'select/selectWadAction.jsp?tcode='+tcode
			   });
				
         });

		 $("select.commonCombo",scope).each(function(index){
			 $(this).commonCombo({
						selectAction:$(this).attr("selectAction")
			   });
				
         });


		 $("select.select-remote-wad-code",scope).each(function(index){
			 var tcode=$(this).attr("tcode");
			 $(this).remoteComboTree({
						treeAction:g_path+'select/remoteSelectWadTreeAction.jsp?tcode='+tcode,
						textAction:g_path+'select/remoteSelectWadCodeNameAction.jsp?tcode='+tcode
			   });
				
         });

		 $("select.select-suborg",scope).each(function(index){
				var orgId=$(this).attr("orgId");
				if(!orgId)
					orgId='';

				$(this).commonCombo({
						treeAction:g_path+'select/selectOrgTreeAction.jsp?oup_service='+oup_service+'&orgId='+orgId,
						textAction:g_path+'select/selectOrgNameAction.jsp?oup_service='+oup_service,
						selectAction:g_path+'select/selectOrgAction.jsp?oup_service='+oup_service+'&orgId='+orgId
			   });			
         });

		 $("select.select-demo-code",scope).each(function(index){
			 var tcode=$(this).attr("tcode");
			 $(this).commonCombo({
						treeAction:g_path+'select/selectDemoDicAction.jsp?tcode='+tcode,
						textAction:'',
						selectAction:g_path+'select/selectDemoDicAction.jsp?tcode='+tcode
			   });
				
         });
}

function gotoFirstPage(datagrid){
	//显示第一页数据 
	datagrid.datagrid("options").pageNumber = 1;  
	//分页栏上跳转到第一页  
	datagrid.datagrid('getPager').pagination({pageNumber: 1});  
}(function($){

//设置是否支持在没有选项符合要求时，是否允许用户随意输入值
		 function objBlur(obj){
			 var freeInput=obj.attr("freeInput");
			if(freeInput&&freeInput=='true')
				 return;

			var e = false;
			var textField = obj.combobox("options")["textField"];
			var text = obj.combobox('getText');
			var arr = obj.combobox('getData');

			for(var i=0;i<arr.length;i++){
				if(text==arr[i][textField]){
					return;
				}
			}

			if(e==false){
				obj.combobox('setText','');
				obj.combobox('setValue','');
			}
			
			var v=$(obj).parent().find('.combo-value').val();
			if(null==v||''==v)
				obj.next().children()[0].value='';
		 }
		 
		 function objTreeBlur(obj){
			var v=$(obj).parent().find('.combo-value').val();
			if(null==v||''==v)
				obj.next().children()[0].value='';
			//else
				//obj.combotree('setValue',v);
		 }


//针对字典，将textName设置到显示字典name的input框的name属性中
		 function setTextName(obj){
			 var textName=obj.attr("textName");
			 if(textName)
					obj.next().children()[0].setAttribute("name",textName);
		 }

		 function setReadOnly(obj){
			 var readOnly=obj.attr("readOnly");
			 if(readOnly!=null&&readOnly!='false'){
					obj.next().children()[0].setAttribute('readOnly','true');
					$(obj.next().children()[0]).css({ color: "#aaaaaa" });
					obj.next().children()[1].children[0].disabled='true';
			 }
		 }

		 function unsetReadOnly(obj){
			//obj.next().children()[0].setAttribute('readOnly','false');
			$(obj.next().children()[0]).attr('readOnly','false');
			$(obj.next().children()[0]).css("color","");
			if(obj.next().children()[1])
				$(obj.next().children()[1].children[0]).removeAttr("disabled");
		 }

		 function setValidProp(obj){
			 obj.next().children()[0].setAttribute("enable_blank",obj.attr("enable_blank"));
			 obj.next().children()[0].setAttribute("displayName",obj.attr("displayName"));
		 }

//根据百分比计算真实宽度
		 function getRealWidth(ct){
			 var pWidth=ct.parent().width();
				var width=ct.css("width");
				if(width.indexOf('%')!=-1){
					width=pWidth*parseFloat(width)/100;
				}else{
					if(width.indexOf('px')!=-1){
						width=width.replace('px','');
					}
				}
			return width;
		 }

		 function initDefaultValue(obj,url){
			 var defaultValue=obj.attr("defaultValue");
			 var defaultText=obj.attr("defaultText");
			 //alert(defaultValue);
			 if(!defaultValue)
				 return;

			obj.combotree('setValue',defaultValue);

			 if(defaultText){
				 obj.combotree('setText',defaultText);
				 return;
			 }

			 $.ajax({
			   type: "POST",
			   url: url+'&defaultValue='+defaultValue,
			   success: function(msg){
				   var text=jQuery.trim(msg);
				   obj.combotree('setText',text);
				   obj.attr("defaultText",text);
			   }
			});
		 }

		 function onlyTreeLeafSelected(tree,node){
			 if(null==node)
				 return true;
			 if(''==node.id&&null==node.attributes)
				 return true;
			 var isLeaf=node.attributes.leaf;
			 if(isLeaf=="false"){
				//throw "";
				return false;
			 }
			 else
				return true;
		 }



$.fn.commonCombo=function(_611,_612){
	var isTree=this.attr("tree");
	var ct=this;
	if(isTree=='true'){
		if(typeof _611=="string"){
			var _613=$.fn.commonCombo.methods[_611];
			if(_613){
				return _613(this,_612);
			}else{
				return this.combotree(_611,_612);
			}
		}

		var treeAction=_611.treeAction;
		var textAction=_611.textAction;
		var exclude=this.attr("exclude");
		if(exclude!=null&&exclude!=''){
			if(treeAction&&treeAction.indexOf("?")==-1)
				treeAction+="?";
			treeAction+="&exclude="+exclude;
		}

		var url;
		var options = {
			url:treeAction,
			hasNullOpt:true,
			width:getRealWidth(this),
			onBlur:function(){
				objTreeBlur(ct);
			},
			onExpand:function(){
				var cur=ct.combotree("getValue");
				if(cur==ct.attr("defaultValue"))
					initDefaultValue(ct,textAction);
			},
			onLoadSuccess:function(node,data){
				if(node==null)
					initDefaultValue(ct,textAction);

				commonset(ct);
			},
			onBeforeSelect:function(node){
				var allowSelected=node.attributes.allowSelected;
				if(allowSelected=='true')
					return true;
				return onlyTreeLeafSelected(ct,node);
			},
			onBeforeLoad:function(node,paras){
				if(node){
						paras.queryKey = node.attributes.queryKey;
				}
			}
		};
		var op=$.extend(_611,options);

		this.combotree(op,_612);
		
	}
	else{

		if(typeof _611=="string"){
			var _613=$.fn.commonCombo.methods[_611];
			if(_613){
				return _613(this,_612);
			}else{
				return this.combobox(_611,_612);
			}
		}

		var selectAction=_611.selectAction;
		var exclude=this.attr("exclude");
		if(exclude!=null&&exclude!=''){
			if(selectAction.indexOf("?")==-1)
				selectAction+="?";
			selectAction+="&exclude="+exclude;
		}

		var options = {
			url:selectAction,
			width:getRealWidth(this),
			valueField:'value',
			textField:'name',
			panelHeight:'auto',
			onBlur:function(){
				objBlur(ct);
			},
			onLoadSuccess:function(){
				//计算下拉框的高度
				var ops=ct.combobox("getData");
				var p=ct.combobox("panel");
				if(ops.length>10)
					p.panel("resize",{height:200});

				commonset(ct);
			}
		};
		
       var op=$.extend(_611,options);
		this.combobox(op,_612);
		//ct.combobox('setValue',ct.attr("defaultValue"));
		var dv = ct.attr("defaultValue");
		var data = new Array();
		if(null!=dv){
			data = dv.split(',');
		}
		ct.combobox('setValues',data);
	}	
}

function commonset(ct){
	setTextName(ct);
	setReadOnly(ct);
	setValidProp(ct);		
}

$.fn.commonCombo.methods={
	setReadOnly:function(jq,val){
		if(val==true||val=='true'){
			jq.attr("readOnly",'true');
			setReadOnly(jq);
		}
		else{
			jq.attr("readOnly",'false');
			unsetReadOnly(jq);
		}

	}
};

$.fn.commonCombo.parseOptions=function(_61a){
	return $.extend({},$.fn.combobox.parseOptions(_61a),$.fn.combotree.parseOptions(_61a),{});
};

$.fn.commonCombo.defaults=$.extend({},$.fn.combobox.defaults,$.fn.combotree.defaults,{});

})(jQuery);

(function($){

		function objBlur(obj){
			var freeInput=obj.attr("freeInput");
			if(freeInput&&freeInput=='true')
				 return;
			var textField = $(obj).combogrid("options")["textField"];
			var text = $(obj).combogrid('getText');
			var value = $(obj).combogrid('getValue');
			var idField = $(obj).combogrid("options")["idField"];
		    var panelClosed=$(obj).combogrid('panel').panel("options").closed;
			if(text==''||!panelClosed)
				return;
			var arr = $(obj).combogrid('grid').datagrid('getData').rows;
			for(var i=0;i<arr.length;i++){
				if(value==arr[i][idField]&&text==arr[i][textField]){
					return;
				}
			}
			$(obj).combogrid('clear');
			$(obj).combogrid('grid').datagrid('reload',{});
		}

		 function setTextName(obj){
			 var textName=obj.attr("textName");
			 //alert(textName);
			 if(textName)
					obj.next().children()[0].setAttribute("name",textName);
		 }

		 function setReadOnly(obj){
			 var readOnly=obj.attr("readOnly");
			 if(readOnly!=null){
					obj.next().children()[0].setAttribute('readOnly','true');
					$(obj.next().children()[0]).css({ color: "#aaaaaa" });
					obj.next().children()[1].children[0].disabled='true';
			 }
		 }

		 function setValidProp(obj){
			 obj.next().children()[0].setAttribute("enable_blank",obj.attr("enable_blank"));
			 obj.next().children()[0].setAttribute("displayName",obj.attr("displayName"));
		 }

		 function getRealWidth(ct){
			 var pWidth=ct.parent().width();
				var width=ct.css("width");
				if(width.indexOf('%')!=-1){
					width=pWidth*parseFloat(width)/100;
				}else{
					if(width.indexOf('px')!=-1){
						width=width.replace('px','');
					}
				}
			return width;
		 }

		 function initDefaultValue(obj){
			 var defaultValue=obj.attr("defaultValue");
			 var defaultText=obj.attr("defaultText");

			 if(!defaultValue)
				 return;

			obj.combogrid('setValue',defaultValue);

			 if(defaultText){
				 obj.combogrid('setText',defaultText);
				 return;
			 }
		 }

		 function isReadOnly(ct){
			 var readOnly=ct.attr("readOnly");
			 return readOnly!=null;
		 }

	$.fn.commonComboGrid=function(_611,_612){
		if(typeof _611=="string"){
			var _613=$.fn.commonComboGrid.methods[_611];
			if(_613){
				return _613(this,_612);
			}else{
				return this.combogrid(_611,_612);
			}
		}

		var ct=this;
		var first=true;
		var defaultValue=ct.attr("defaultValue");
		var defaultText=ct.attr("defaultText");
		var options = {
			width:getRealWidth(this),
			onLoadSuccess:function(){
				if(first||isReadOnly(ct)){
				     initDefaultValue(ct);
					 first=false;
				}
			},
			onBlur:function(){
				if(!isReadOnly(ct))
					objBlur(ct);
			},
			queryParams:{defaultValue:defaultValue,defaultText:defaultText},
			onBeforeLoad:function(){
				$(ct).combogrid('grid').datagrid("options").pageNumber = 1;  
				$(ct).combogrid('grid').datagrid('getPager').pagination({pageNumber: 1}); 
			}
		};
		
	var op=$.extend(_611,options);

	this.combogrid(op,_612);
	first=true;

	var panel=this.combogrid("panel");
	panel.panel(
        {
           onClose:function(){
             objBlur(ct);
           }
        }
   	  );

	  initDefaultValue(ct);
		
	setTextName(this);
	setReadOnly(this);
	setValidProp(this);		
}

$.fn.commonComboGrid.methods=$.extend({},$.fn.combogrid.methods,{
});

$.fn.commonComboGrid.parseOptions=function(_61a){
	var t=$(_61a);
	return $.extend({},$.fn.combogrid.parseOptions(_61a),{});
};

$.fn.commonComboGrid.defaults=$.extend({},$.fn.combogrid.defaults,{});

})(jQuery);

(function($){
	$.fn.commonGrid=function(_611,_612){
		if(typeof _611=="string"){
			var _613=$.fn.commonGrid.methods[_611];
			
			if(_613){
				return _613(this,_612);
			}else{
				return this.datagrid(_611,_612);
			}
		}

		var ct=this;

		var options = {
			onBeforeLoad:function(param){
				if($(this).datagrid("options").pagination){
					$(this).datagrid("getPager").pagination("options").pageNumber=param.page;
				}
				else{
					$.extend(param,{"rows":$(this).datagrid("options").pageSize});
				}

				var fsstr=getColParam(this);
				$.extend(param,fsstr);	
			}
		};
		
		var op=$.extend(_611,options);

		this.datagrid(op,_612);
}

function getColParam(obj){
	var fs=$(obj).datagrid("getColumnFields");
	var frofs=$(obj).datagrid("getColumnFields",true);
	var c=frofs.concat(fs); 

	var hiddenColumns=$(obj).datagrid("options").hiddenColumns;
	var fsstr="";
	var ftitleStr="";
	for(var i=0;i<c.length;i++){
		var fo=$(obj).datagrid("getColumnOption",c[i]);
		if(fo.type=='local')
			continue;
		
		if(fsstr!=''){
			fsstr+=","
			ftitleStr+=","
		}
		fsstr+=c[i];
		ftitleStr+=fo.title;
	}
	for(var i=0;hiddenColumns&&i<hiddenColumns.length;i++){
		if(fsstr!='')
			fsstr+=","
		fsstr+=hiddenColumns[i];
	}
	return {"cols":fsstr,"colNames":ftitleStr};
}
$.fn.commonGrid.methods=$.extend({},$.fn.datagrid.methods,{
});

$.fn.commonGrid.parseOptions=function(_61a){
	var t=$(_61a);
	return $.extend({},$.fn.datagrid.parseOptions(_61a),{});
};

$.fn.commonGrid.defaults=$.extend({},$.fn.datagrid.defaults,{});

})(jQuery);

(function($){


//针对字典，将textName设置到显示字典name的input框的name属性中
		 function setTextName(obj){
			 var textName=obj.attr("textName");
			 if(textName)
					obj.next().children()[0].setAttribute("name",textName);
		 }

		 function setReadOnly(obj){
			 var readOnly=obj.attr("readOnly");
			 if(readOnly!=null&&readOnly!='false'){
					obj.next().children()[0].setAttribute('readOnly','true');
					$(obj.next().children()[0]).css({ color: "#aaaaaa" });
					obj.next().children()[1].children[0].disabled='true';
			 }
		 }

		 function unsetReadOnly(obj){
			//obj.next().children()[0].setAttribute('readOnly','false');
			$(obj.next().children()[0]).attr('readOnly','false');
			$(obj.next().children()[0]).css("color","");
			if(obj.next().children()[1])
				$(obj.next().children()[1].children[0]).removeAttr("disabled");
		 }

		 function setValidProp(obj){
			 obj.next().children()[0].setAttribute("enable_blank",obj.attr("enable_blank"));
			 obj.next().children()[0].setAttribute("displayName",obj.attr("displayName"));
		 }

//根据百分比计算真实宽度
		 function getRealWidth(ct){
			 var pWidth=ct.parent().width();
				var width=ct.css("width");
				if(width.indexOf('%')!=-1){
					width=pWidth*parseFloat(width)/100;
				}else{
					if(width.indexOf('px')!=-1){
						width=width.replace('px','');
					}
				}
			return width;
		 }

		 function initDefaultValue(obj,url){
			 var defaultValue=obj.attr("defaultValue");
			 var defaultText=obj.attr("defaultText");
			 //alert(defaultValue);
			 if(!defaultValue)
				 return;

			obj.combotree('setValue',defaultValue);
		 }

		 function onlyTreeLeafSelected(tree,node){
			 var isLeaf=node.attributes.leaf;
			 if(isLeaf=="false"){
				//throw "";
				return false;
			 }
			 else
				return true;
		 }



$.fn.remoteComboTree=function(_611,_612){
	var ct=this;
	if(typeof _611=="string"){
		var _613=$.fn.remoteComboTree.methods[_611];
		if(_613){
			return _613(this,_612);
		}else{
			return this.combotree(_611,_612);
		}
	}

	var treeAction=_611.treeAction;
	var textAction=_611.textAction;
	var exclude=this.attr("exclude");
	if(exclude!=null&&exclude!=''){
		if(treeAction&&treeAction.indexOf("?")==-1)
			treeAction+="?";
		treeAction+="&exclude="+exclude;
	}

	var url,queryKey;
	first=true;
	var options = {
		url:treeAction,
		width:getRealWidth(this),
		editable:true,
		keyHandler: {
			up: function(){},
			down: function(){},
			enter: function(){},
			query: function(q){queryKey=q;ct.combotree("reload",treeAction+"&q="+q);}
		},
		onLoadSuccess:function(node,data){
			if(queryKey){
				ct.combotree('clear');ct.next().children()[0].value=queryKey;
			}
		},
		onBeforeSelect:function(node){
			return onlyTreeLeafSelected(ct,node);
		},
		onBlur:function(){
			var value=ct.combotree('getValue');
			if(!value){
				ct.combotree('setValue',value);			}
		}
	};
	var op=$.extend(_611,options);

	this.combotree(op,_612);
	initDefaultValue(ct);
	commonset(ct);	
}

function commonset(ct){
	setTextName(ct);
	setReadOnly(ct);
	setValidProp(ct);		
}

$.fn.remoteComboTree.methods={
	setReadOnly:function(jq,val){
		if(val==true||val=='true'){
			jq.attr("readOnly",'true');
			setReadOnly(jq);
		}
		else{
			jq.attr("readOnly",'false');
			unsetReadOnly(jq);
		}

	}
};

$.fn.remoteComboTree.parseOptions=function(_61a){
	return $.extend({},$.fn.combotree.parseOptions(_61a),{});
};

$.fn.remoteComboTree.defaults=$.extend({},$.fn.combotree.defaults,{});

})(jQuery);
