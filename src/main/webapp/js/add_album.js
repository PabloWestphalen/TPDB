function addTriggers(responseText, statusText, xhr, $form)  { 
    alert('status: ' + statusText + '\n\nresponseText: \n' + responseText + 
        '\n\nThe output div should have already been updated with the responseText.');
    
    $('#coverUploadButton').attr("src", "/images/covers/" + responseText);
} 
$(document).ready(function(){
	var tabindex = 32;
	var value = 12;
	
	$('#btnTracks').click(function() {
		var track = '<p><input type="text" value="' + (value+1) + 
		'" disabled>\n<input id="tracks[]" name="tracks[]" type="text" tabindex="' + (tabindex+1) + 
		'">\n<input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="' +(tabindex+2) + '"></p>';
		tabindex = tabindex + 2;
		$('#btnTracks').attr("tabindex", tabindex + 1);		
		$('#albumBtn').attr("tabindex", tabindex + 2);		
		value++;
		$(this).parent().before(track);
	});
	
	$('#coverUploadButton').keypress(function(){
	    // 13 -> ENTER
    if( event.which === 13  ) {		
			$('#coverUp').click();
		}
	});
	
	$('#coverUploadButton').click(function(){
		$('#coverUp').click();
	});
	
    /* #imagem � o id do input, ao alterar o conteudo do input execurar� a fun��o baixo */
    $('#coverUp').live('change',function(){
        //$('#visualizar').html('<img src="ajax-loader.gif" alt="Enviando..."/> Enviando...');
       /* Efetua o Upload sem dar refresh na pagina */
        $('#coverForm').ajaxForm({
        success: addTriggers        	   
        }).submit();
    });
	
});