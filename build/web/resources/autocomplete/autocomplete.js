// Expose plugin as an AMD module if AMD loader is present:
;(function (factory) {
	'use strict';
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as an anonymous module.
		define(['jquery'], factory);
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {
	'use strict';
	
	var defaults = {
		'url' 			: '',	
		'width' 		: 'auto',
		'minChars' 		: 2,					
		'maxHeight' 	: 200,
		'params' 		: {},
		'dataType' 		: 'json',
		'zIndex' 		: 9999,
		'type' 			: 'POST',
		'cache' 		: false,
		
		// CALLBACKS
		onSelectItem: function() {} // function (elemento, data) {}		
	};
	
    $.fn.autocomplete = function(options) {
		
		var countInstancias = this.length; // total de instâncias do plugin		
		if(countInstancias == 0) return this;
		// suporte para multiplas instâncias
		if(countInstancias > 1){
			this.each(function(){$(this).autocomplete(options)});
			return this;
		}
		
		var autocomplete 	= {}; // cria uma variavel para ser usada no plugin		
		var el 				= this;
		autocomplete.el 	= this; // cria uma referência para o elemento instanciado

		autocomplete.keys = {
            ESC: 27,
            TAB: 9,
            RETURN: 13,
            UP: 38,
            DOWN: 40
        };
		
		var init = function(){			
			autocomplete.settings 		= $.extend({}, defaults, options);
			autocomplete.offsetLeft 	= autocomplete.el.offset().left;
			autocomplete.offsetBottom 	= autocomplete.el.outerHeight() + autocomplete.el.offset().top;
			autocomplete.width 			= (autocomplete.settings.width == 'auto' ? autocomplete.el.outerWidth() : autocomplete.settings.width);
			autocomplete.classref 		= 'class_'+Math.random().toString(36).slice(-8);			
			autocomplete.indexSelected	= -1;
			setup(); // executa todas modificações no DOM
		}
		
		var setup = function(){
			autocomplete.el.attr('autocomplete', 'off');
			autocomplete.el.attr('class', autocomplete.classref);
			$('body').append('<div class="fn_autocomplete '+autocomplete.classref+'"><ul></ul></div>');
			autocomplete.bloco = $('div.'+autocomplete.classref);
			autocomplete.lista = autocomplete.bloco.find('ul');
			autocomplete.countItens = -1;
			autocomplete.bloco.css({ 
				'width': autocomplete.width, 
				'maxHeight': autocomplete.settings.maxHeight, 
				'left': autocomplete.offsetLeft, 
				'top': autocomplete.offsetBottom,
				'zIndex' : autocomplete.settings.zIndex
			});			
			events();			
		}
		
		var events = function() {			
			autocomplete.el.on('keyup', function (event) { keyUp($(this), event); });
			autocomplete.el.on('keypress', function (event) { keyPress(event); });
			autocomplete.lista.on('click', 'li', function () { selectItem($(this).index()); });            
		}

		var setPositionList = function() {
			autocomplete.offsetLeft 	= autocomplete.el.offset().left;
			autocomplete.offsetBottom 	= autocomplete.el.outerHeight() + autocomplete.el.offset().top;
			autocomplete.bloco.css({
				'left': autocomplete.offsetLeft, 
				'top': autocomplete.offsetBottom
			});
		}
		
		var cleanList = function() {
			autocomplete.lista.find('li').removeClass('active');
			autocomplete.lista.html('');
			autocomplete.bloco.hide();
			autocomplete.indexSelected = -1;
		}
		
		var keyUp = function(obj, e) {
			var code = e.keyCode || e.which;
			var index = parseInt(autocomplete.indexSelected);

			switch (code) {
                case autocomplete.keys.ESC:
                    cleanList();
                    break;
				
				case autocomplete.keys.TAB:
                    if (index >= 0)
                    	selectItem(index);
                    return;

                case autocomplete.keys.RETURN:
                	if (index >= 0 )
                    	selectItem(index);					
                    return;

                case autocomplete.keys.UP:
                	if ((index-1) >= 0)
                    	setItemActive(index-1);
                    break;

                case autocomplete.keys.DOWN:
                	if ((index+1) >= 0 && (index+1) < autocomplete.countItens)
                    	setItemActive(index+1);
                    break;

				default:
					if (obj.val().length >= autocomplete.settings.minChars) {				
						ajaxRequest();
					} else {
						cleanList();
					}
                    return;
            }
            e.stopImmediatePropagation();
            e.preventDefault();
		}
		var keyPress = function(e) {
			var code = e.keyCode || e.which;
			if(code == autocomplete.keys.RETURN)
				e.preventDefault();
		}

		var ajaxRequest = function() {
			if (autocomplete.request) {
				autocomplete.request.abort();
				cleanList();
			}					
			autocomplete.settings.params.q = autocomplete.el.val(); // variavel 'q' = value do input
			autocomplete.request = $.ajax({
				type: autocomplete.settings.type,					
				cache: autocomplete.settings.cache,
				url: autocomplete.settings.url,
				data: autocomplete.settings.params,
				dataType: autocomplete.settings.dataType
			}).done(function (data) {
				autocomplete.request.abort();
				if(JSON.stringify(data) != 'null' && JSON.stringify(data) != '[]' && JSON.stringify(data) != '') {
					populateList(data);
					autocomplete.bloco.show();
				}
			}).fail(function (jqXHR, textStatus, errorThrown) {
				console.log('Erro: '+jqXHR.responseText);
			});
		}

		var selectItem = function(index) {
			var obj = autocomplete.lista.find('li:eq('+index+')');
			autocomplete.el.val(obj.text());			
			cleanList();
			return autocomplete.settings.onSelectItem(autocomplete.el, obj.data('data'));
		}

		var setItemActive = function(index) {
			autocomplete.lista.find('li').removeClass('active');
			autocomplete.lista.find('li:eq('+index+')').addClass('active');
			autocomplete.indexSelected = index;
		}
		
		var populateList = function(data) {
			cleanList();
			setPositionList();
			$.each(data,function(i, val){
				var value = val[0];
				var datas = JSON.stringify(val[1]);
				autocomplete.lista.append('<li data-data=\''+datas+'\'>'+value+'</li>');
			});
			autocomplete.countItens = autocomplete.lista.find('li').length;
			setItemActive(0);
		}

		$('body:not(.fn_autocomplete)').click(function(){
			if($('.fn_autocomplete').is(':visible')){
				$('.fn_autocomplete').hide();
				cleanList();
			}
		});
		
		init();
				
		return this;
    };
	
}));