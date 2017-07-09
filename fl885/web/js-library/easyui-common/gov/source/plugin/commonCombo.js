(function($){

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

