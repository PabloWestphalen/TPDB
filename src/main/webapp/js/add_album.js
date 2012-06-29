var tabindex = 8;
var value = 1;
	
function addTriggers(responseText)  { 
	$('#coverUploadButton').toggleClass('loading');
	$('#coverUploadButton').attr("src", "/images/covers/" + responseText);
    
} 
function addTracks() {
	var track = '<p><input type="text" value="' + (value+1) + 
	'" disabled>\n<input id="tracks[]" name="tracks[]" type="text" tabindex="' + (tabindex+1) + 
	'">\n<input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="' +(tabindex+2) + '"></p>';
	tabindex = tabindex + 2;
	$('#btnTracks').attr("tabindex", tabindex + 1);		
	$('#albumBtn').attr("tabindex", tabindex + 2);		
	value++;
	$('#newTrackNumber').attr("value", value + 1);
	
	$('#fTracks p:last-child').before(track);
	addTracksHandler();
}
function addTracksHandler() {
	$('#fTracks p:last-child').prev().find('input[name="tracks_length[]"]').keydown(function(event) {
		if(event.which === 9 && event.shiftKey === false) {
			addTracks();
		}
	});
}
$(document).ready(function(){
	
	addTracksHandler();	
	
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