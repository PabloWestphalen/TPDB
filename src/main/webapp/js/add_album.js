var tabindex = 9;
var value = 2;
	
function addTriggers(responseText)  { 
	$('#coverUploadButton').toggleClass('loading');
	$('#coverUploadButton').attr("src", "/images/covers/" + responseText);
    
} 
function addTracks() {
	$('p[class="newTrack"] input').unbind('focus');
	
	$('p[class="newTrack"]').toggleClass("newTrack") ;
	
	var newTrack = '<p class="newTrack"><input type="text" value="' + (value+1) + 
	'" disabled>\n<input id="tracks[]" name="tracks[]" type="text" tabindex="' + (tabindex+1) + 
	'">\n<input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="' +(tabindex+2) + '"></p>';
	
	$('#fTracks p:last-child').after(newTrack);
	$('p[class="newTrack"] input').focus(addTracks);
	
	tabindex = tabindex + 2;	
	value++;
}

$(document).ready(function(){
	
	$('p[class="newTrack"] input').focus(addTracks);
	
	$('#coverUploadButton').keypress(function(event){
	    // 13 -> ENTER
    if( event.which === 13  ) {		
		event.preventDefault();	
    	$('#coverUp').click();
		}
	});
	
	$('#coverUploadButton').click(function(event){
		event.preventDefault();
		$('#coverUp').click();
	});
	
    $('#coverUp').live('change',function(){
    	$('#albumName').focus();
    	$('#coverUploadButton').toggleClass('loading');
    	$('#coverForm').ajaxForm({
        success: addTriggers
        }).submit();
    });	
    

});