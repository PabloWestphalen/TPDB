$(document).ready(function(){
	   $('#tags').textext({
	        plugins : 'tags prompt focus autocomplete ajax arrow',
	        prompt : 'Add one...',
	        ajax : {
	            url : '/api/',
	            dataType : 'json',
	            cacheResults : true
	        }
	    });
});