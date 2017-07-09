$.extend($.fn.validatebox.defaults.rules, {   
    number: {//整数校验
        validator: function(value, param){
        	 /**var reg = new RegExp('^[0-9]*$');
        	 return reg.test(value);*/
			return /^-?\d+$/.test(value);
        },   
        message: '只能输入整数'  
    },
    floatNum: {//浮点校验
        validator: function(value, param){
        	return /^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value);
        },   
        message: '小数格式不正确'  
    },
    email:{//邮箱校验
    	 validator: function(value, param){
			if(value){
				// var reg = new RegExp('w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*');
				return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test(value);
			}
			return false;
        },   
        message: '邮箱格式不正确'  
    },
   idcard: {//身份证校验(开头是14位或者17位数字，结尾可以是数字或者是x或者是X)
        validator: function(value, param){
        	return /^(\d{14}|\d{17})(\d|[xX])$/.test(value);
        },   
        message: '身份证格式不正确'  
    },
    carNo: {//车牌校验
        validator: function(value, param){
        	 var reg = new RegExp('^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$');
        	 return reg.test(value);
        },   
        message: '车牌格式不正确'  
    },
    minVal: {//大于下限值的校验
        validator: function(value, param){
        	var reg = (/^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value));
        	if(reg==false){
        		this.message='输入的不是数字，请重新输入';
        		return false;
        	}
        	else { 
        	 this.message='输入的值不能小于{0}'; 
        	 return value>=param[0];
        	}
        },   
        message: '输入的值应大于{0}'  
    },
    maxVal: {//小于上限值的校验
        validator: function(value, param){
        	var reg = (/^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value));
        	if(reg==false){
        		this.message='输入的不是数字，请重新输入';
        		return false;
        	}
        	else { 
        		this.message='输入的值不能大于{0}';
        		return value<=param[0];
        	}
        },   
        message: '输入的值应小于{0}'  
    },
    minMaxVal: {//最大值最小值范围校验
        validator: function(value, param){
        	var reg = (/^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value));
        	if(reg==false){
        		this.message='输入的不是数字，请重新输入';
        		return false;
        	}
        	else { 
        		this.message='输入的值的范围应在{0}和{1}之间';
                return  (value >= param[0] && value <= param[1]);
        	}
        },   
        message: '输入的值在范围{0}和{1}之间'
    },
    minMaxLength: {//字符串最大长度和最小长度范围校验
        validator: function(value, param){
        	var realValue=$.trim(value);
        	if(value.length>0&&''==realValue){
        		this.message='输入的值不能是全空格';
        		return false;
        	}
        	else { 
        	this.message='输入的文字数量应在{0}个和{1}个之间';
            return (realValue.length >= param[0] && realValue.length <= param[1]);
        	}
        },   
        message: '输入的值的长度范围在{0}和{1}之间'
    },
    minLength: { //
    	validator: function(value, param){
        	var realValue=$.trim(value);
        	if(value.length>0&&''==realValue){
        		this.message='输入的值不能是全空格';
        		return false;
        	}
        	else { 
        	this.message='输入的文字数量不能小于{0}个';
            return (realValue.length >= param[0]);
        	}
        },   
        message: '输入的值的长度应大于{0}'
    },
    maxLength: { //
    	validator: function(value, param){
        	var realValue=$.trim(value);
        	if(value.length>0&&''==realValue){
        		this.message='输入的值不能是全空格';
        		return false;
        	}
        	else { 
        	this.message='输入的文字数量不能大于{0}个';
            return (realValue.length <= param[0]);
        	}
        },   
        message: '输入的值的长度应小于{0}'
    }
});  
