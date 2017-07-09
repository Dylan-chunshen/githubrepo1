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
}