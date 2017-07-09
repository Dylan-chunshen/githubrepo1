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

