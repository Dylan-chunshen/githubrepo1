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
