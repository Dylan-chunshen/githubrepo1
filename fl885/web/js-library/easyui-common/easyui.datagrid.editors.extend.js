
$.extend($.fn.datagrid.defaults.editors, {   
    text: {   
        init: function(container, options){
        	var events = null==options?{}:options.events;
        	events = null==events?'':events;
            var input = $('<input type="text" '+events+' />');
            input.appendTo(container).validatebox(options);
            return input;   
        },   
        getValue: function(target){   
            return $(target).val();   
        },   
        setValue: function(target, value){   
            $(target).val(value);   
        },   
        resize: function(target, width){   
            var input = $(target);   
            if ($.boxModel == true){   
                input.width(width - (input.outerWidth() - input.width()));   
            } else {   
                input.width(width);   
            }   
        }   
    },
    textarea: {   
        init: function(container, options){
        	var events = null==options?{}:options.events;
        	events = null==events?'':events;
            var input = $('<textarea '+events+' ></textarea>');
            input.appendTo(container).validatebox(options);
            return input;   
        },   
        getValue: function(target){   
            return $(target).val();   
        },   
        setValue: function(target, value){   
            $(target).val(value);   
        },   
        resize: function(target, width){   
            var input = $(target);   
            if ($.boxModel == true){   
                input.width(width - (input.outerWidth() - input.width()));   
            } else {   
                input.width(width);   
            }   
        }   
    }
});  
