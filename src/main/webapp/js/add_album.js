var value = 2;

//post-submit callback 
function showResponse(response, statusText, xhr, $form)  { 
	var opt = '<option  id="createdArtist" value="' + response.id + '" selected="selected">' + response.name + '</option>';
	$('#artist optgroup').append(opt);
	$('#createdArtist').select();
	$('#newArtist').remove();
} 

//dynamically set the tabindex for every form field in the page
function setTabIndex() {
	var setTabIndex = 1;
	$('input,select, textarea, img').each(function() {
			if (this.type != "hidden") {
					var $input = $(this);
					$input.attr("tabindex", setTabIndex);
					setTabIndex++;
					tabindex = setTabIndex;
			}
	});
}

// turns off the lightbox dialog
function lightBoxOff(success) {
		$('#lightbox, #artistPanel').fadeOut(250);
		$('aside').css("opacity", ".98");			
		setTabIndex();
}

//displays the uploaded image
function addTriggers(response)  { 
	$('#coverUploadButton').toggleClass('loading');
	$('#coverUploadButton').attr("src", "/images/albums/" + response.temp_cover_name);
	var temp_cover_name = '<input type="hidden" name="temp_cover_name" value="' + response.temp_cover_name + '" />';
	$('#albumForm').prepend(temp_cover_name);
	
}

//adds the fields for a new track
function addTracks() {
	//TODO: comment this function's steps
	$('p[class="newTrack"] input').unbind('focus');
	
	$('p[class="newTrack"]').toggleClass("newTrack") ;
	
	var newTrack = '<p class="newTrack"><input type="text" value="' + (value+1) + 
	'" disabled>\n<input id="tracks[]" name="tracks[]" type="text">' +
	'\n<input id="tracks_length[]" name="tracks_length[]" type="text"></p>';
	
	$('#fTracks p:last-child').after(newTrack);
	$('p[class="newTrack"] input').focus(addTracks);

	
	value++;
	$('html,body').animate({scrollTop: $('footer').offset().top}, 0);
	setTabIndex();
}


//setups the page
$(document).ready(function(){
	
	// set fields' tabindex
	setTabIndex();
	
	// add descriptions for the tracks' fields
	var firstTrack = $('input[name="tracks[]"]').first();
	var firstLength = $('input[name="tracks_length[]"]').first();
	firstTrack.val('Name');
	firstLength.val('Length');	
	firstTrack.one("focus", function() {
		firstTrack.val('');
	});	
	firstLength.one("focus", function() {
		firstLength.val('');
	});
	
	// scroll to the tracks section when the user first clicks one of the tracks
	$('#fTracks input').one("focus", function() {
		$('html,body').animate({scrollTop: $('#fTracks').offset().top}, 1000);
	});	
	
	// new track handler
	$('p[class="newTrack"] input').focus(addTracks);
	
	// cover upload handlers
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
		$('#coverUploadButton').toggleClass('loading');
    	$('#coverForm').ajaxForm({
    		dataType: "json",
    		success: addTriggers
        }).submit();
    });	
	
		// pop up lightbox with ajax form
	$('#newArtist').click(function() {
		winH = $(document).height();
		winW = $(document).width();
		$('#lightbox').css("height", winH);
		$('#lightbox').css("width", winW-30);			
		$('#lightbox, #artistPanel').fadeIn(250);
		$('aside').css("opacity", "1");
    $('input, textarea, select, img').not('#artistPanel input').attr('tabindex', '-1');
		$('#artistPanel form').submit(function(event) {
			event.preventDefault();			
			lightBoxOff(true);			
		});		
	});
	
	
	// quit lightbox
	$('#closeWindow').click(lightBoxOff);
	$('#artistPanel input, #artistPanel').keydown(function(event) {
		if(event.which == 27) {
			lightBoxOff();
		}
	});
 
    // set up new artist ajax form
    $('#addArtistForm').ajaxForm({
    	dataType:      "json",
        success:       showResponse,  // post-submit callback
    }); 


});

$('#name').blur(function(){
	var artistName = $('#artist option:selected').text();
	var albumName = $('#name').val();
});