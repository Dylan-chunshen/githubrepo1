$(function(){
			$('#tagmenu').tree({
				onClick:function(node){
					$('#mainpanel').attr("src",'tags/'+jQuery.trim(node.text)+".html");
				}
			});
});