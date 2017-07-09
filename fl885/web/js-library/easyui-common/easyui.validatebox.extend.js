$.extend($.fn.validatebox.defaults.rules, {   
    number: {//����У��
        validator: function(value, param){
        	 /**var reg = new RegExp('^[0-9]*$');
        	 return reg.test(value);*/
			return /^-?\d+$/.test(value);
        },   
        message: 'ֻ����������'  
    },
    floatNum: {//����У��
        validator: function(value, param){
        	return /^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value);
        },   
        message: 'С����ʽ����ȷ'  
    },
    email:{//����У��
    	 validator: function(value, param){
			if(value){
				// var reg = new RegExp('w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*');
				return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test(value);
			}
			return false;
        },   
        message: '�����ʽ����ȷ'  
    },
   idcard: {//���֤У��(��ͷ��14λ����17λ���֣���β���������ֻ�����x������X)
        validator: function(value, param){
        	return /^(\d{14}|\d{17})(\d|[xX])$/.test(value);
        },   
        message: '���֤��ʽ����ȷ'  
    },
    carNo: {//����У��
        validator: function(value, param){
        	 var reg = new RegExp('^[\u4e00-\u9fa5]{1}[A-Z0-9]{6}$');
        	 return reg.test(value);
        },   
        message: '���Ƹ�ʽ����ȷ'  
    },
    minVal: {//��������ֵ��У��
        validator: function(value, param){
        	var reg = (/^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value));
        	if(reg==false){
        		this.message='����Ĳ������֣�����������';
        		return false;
        	}
        	else { 
        	 this.message='�����ֵ����С��{0}'; 
        	 return value>=param[0];
        	}
        },   
        message: '�����ֵӦ����{0}'  
    },
    maxVal: {//С������ֵ��У��
        validator: function(value, param){
        	var reg = (/^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value));
        	if(reg==false){
        		this.message='����Ĳ������֣�����������';
        		return false;
        	}
        	else { 
        		this.message='�����ֵ���ܴ���{0}';
        		return value<=param[0];
        	}
        },   
        message: '�����ֵӦС��{0}'  
    },
    minMaxVal: {//���ֵ��Сֵ��ΧУ��
        validator: function(value, param){
        	var reg = (/^-?\d+$/.test(value) || /^-?\d+\.\d+$/.test(value));
        	if(reg==false){
        		this.message='����Ĳ������֣�����������';
        		return false;
        	}
        	else { 
        		this.message='�����ֵ�ķ�ΧӦ��{0}��{1}֮��';
                return  (value >= param[0] && value <= param[1]);
        	}
        },   
        message: '�����ֵ�ڷ�Χ{0}��{1}֮��'
    },
    minMaxLength: {//�ַ�����󳤶Ⱥ���С���ȷ�ΧУ��
        validator: function(value, param){
        	var realValue=$.trim(value);
        	if(value.length>0&&''==realValue){
        		this.message='�����ֵ������ȫ�ո�';
        		return false;
        	}
        	else { 
        	this.message='�������������Ӧ��{0}����{1}��֮��';
            return (realValue.length >= param[0] && realValue.length <= param[1]);
        	}
        },   
        message: '�����ֵ�ĳ��ȷ�Χ��{0}��{1}֮��'
    },
    minLength: { //
    	validator: function(value, param){
        	var realValue=$.trim(value);
        	if(value.length>0&&''==realValue){
        		this.message='�����ֵ������ȫ�ո�';
        		return false;
        	}
        	else { 
        	this.message='�����������������С��{0}��';
            return (realValue.length >= param[0]);
        	}
        },   
        message: '�����ֵ�ĳ���Ӧ����{0}'
    },
    maxLength: { //
    	validator: function(value, param){
        	var realValue=$.trim(value);
        	if(value.length>0&&''==realValue){
        		this.message='�����ֵ������ȫ�ո�';
        		return false;
        	}
        	else { 
        	this.message='����������������ܴ���{0}��';
            return (realValue.length <= param[0]);
        	}
        },   
        message: '�����ֵ�ĳ���ӦС��{0}'
    }
});  
