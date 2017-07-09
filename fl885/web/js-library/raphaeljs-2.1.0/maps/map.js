$.WMAP = {
	attr:{
    "fill": "#97d6f5",
    "stroke": "#eee",
    "stroke-width": 1,
    "stroke-linejoin": "round"
	},
	textAttr:{
    "fill": "#000","font-size": "12px","cursor": "pointer"
	}
};

function WMap(options){
	
	var mapKey = options.map;
	var data = $.extend({},options.data);
	var svgDatas = $.WMAP[mapKey];
	var result = Raphael(options.id, options.width, options.height);
	var elemAttr = null==options.elemAttr?$.WMAP.attr:options.elemAttr;
	var textAttr = null==options.textAttr?$.WMAP.textAttr:options.textAttr;
	var elemArr = [],len=svgDatas.length;
  
	for(var i=0;i<len;i++){
		var e = result.path(svgDatas[i].path).attr(elemAttr);
		elemArr.push(e);
	}
  
	for(var i=0;i<len;i++){
		var elem = elemArr[i];
  	
	  	(function (elem, district) {
	  		elem.id=district.code;
	  		elem.data("name",district.name);
	  		district.data=$.extend($.extend({},data[district.name]),data[district.code]);
		  	
	  		var offsetX = null==district.offsetX?0:district.offsetX;
	  		var offsetY = null==district.offsetY?0:district.offsetY;
		  	var xx = elem.getBBox().x + (elem.getBBox().width / 2)+offsetX;
		    var yy = elem.getBBox().y + (elem.getBBox().height / 2)+offsetY;
		   
		    elem['text']=result.text(xx, yy, district.name).attr(textAttr);
		    elem['text'][0].firstChild.onclick=function(event){
		    		if(null!=options.onClick&&typeof(options.onClick)==='function')
							options.onClick.call(event,elem,district);
			};
			elem[0].onclick = function(event){
				if(null!=options.onClick&&typeof(options.onClick)==='function')
					options.onClick.call(event,elem,district);
			};
			elem[0].onmouseover = function(event) {
				if(null!=options.onMouseover&&typeof(options.onMouseover)==='function')
					options.onMouseover.call(event,elem,district);
		    };
		    elem[0].onmouseout = function (event) {
		    	if(null!=options.onMouseout&&typeof(options.onMouseout)==='function')
					options.onMouseout.call(event,elem,district);
		    };
	  	})(elem, svgDatas[i]);
	}
	
	return result;
}