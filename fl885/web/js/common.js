/** 选项卡*/

function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}
	
/** 滚动*/

function runstar(a,time,flag){
	if (1 == flag){runx=setInterval("run("+a+")",10)}
	else{runx=setInterval("run2("+a+")",10)}

}
function runover(){
clearInterval(runx)
}
function run(a){
scrollx=frm_frdlist.document.body.scrollLeft
scrolly=frm_frdlist.document.body.scrollTop
scrollx=scrollx+a
frm_frdlist.window.scroll(scrollx,scrolly)
}
function run2(a){
scrollx=frm_piclist.document.body.scrollLeft
scrolly=frm_piclist.document.body.scrollTop
scrollx=scrollx+a
frm_piclist.window.scroll(scrollx,scrolly)
}
function x_down(theobject){
	object=theobject
	
	while(object.filters.alpha.opacity>60){
		object.filters.alpha.opacity+=-60
	}
}
function x_up(theobject){
	object=theobject
	while(object.filters.alpha.opacity<60){
		object.filters.alpha.opacity+=60
	}
}
function wback(){
	if(frm_frdlist.history.length==0){window.history.back()}
	else{frm_frdlist.history.back()}
}

	
/** 滚动*/

function gunstar(a,time,flag){
	if (1 == flag){gunx=setInterval("gun("+a+")",10)}
	else{gunx=setInterval("gun2("+a+")",10)}

}
function gunover(){
clearInterval(gunx)
}
function gun(a){
scrollx=frm_frdlist.document.body.scrollLeft
scrolly=frm_frdlist.document.body.scrollTop
scrollx=scrollx+a
frm_frdlist.window.scroll(scrollx,scrolly)
}
function gun2(a){
scrollx=frm_piclist_2.document.body.scrollLeft
scrolly=frm_piclist_2.document.body.scrollTop
scrollx=scrollx+a
frm_piclist_2.window.scroll(scrollx,scrolly)
}
function x_down(theobject){
	object=theobject
	
	while(object.filters.alpha.opacity>60){
		object.filters.alpha.opacity+=-60
	}
}
function x_up(theobject){
	object=theobject
	while(object.filters.alpha.opacity<60){
		object.filters.alpha.opacity+=60
	}
}
function wback(){
	if(frm_frdlist.history.length==0){window.history.back()}
	else{frm_frdlist.history.back()}
}

