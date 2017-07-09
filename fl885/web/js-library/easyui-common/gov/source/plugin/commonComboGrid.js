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

