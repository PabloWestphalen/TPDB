// pre-submit callback 
function showRequest(formData, jqForm, options) { 
    // formData is an array; here we use $.param to convert it to a string to display it 
    // but the form plugin does this for you automatically when it submits the data 
    var queryString = $.param(formData); 
 
    // jqForm is a jQuery object encapsulating the form element.  To access the 
    // DOM element for the form do this: 
    // var formElement = jqForm[0]; 
 
    alert('About to submit: \n\n' + queryString); 
 
    // here we could return false to prevent the form from being submitted; 
    // returning anything other than false will allow the form submit to continue 
    return true; 
} 
 
// post-submit callback 
function showResponse(responseText, statusText, xhr, $form)  { 
	alert(xhr);
    alert('status: ' + statusText + '\n\nresponseText: \n' + responseText + 
        '\n\nThe output div should have already been updated with the responseText.'); 
} 


var value = 2;

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

function lightBoxOff(success) {
		$('#lightbox, #artistPanel').fadeOut(250);
		$('aside').css("opacity", ".98");			
		setTabIndex();
		if(success === true) {
			$('#month').focus();
		}			
}
function addTriggers(responseText)  { 
	$('#coverUploadButton').toggleClass('loading');
	$('#coverUploadButton').attr("src", "/images/covers/" + responseText);   
} 
function addTracks() {
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
		$('#artist_name').focus();
    $('input, textarea, select, img').not('#artistPanel input').attr('tabindex', '-1');
		$('#artistPanel form').submit(function(event) {
			event.preventDefault();			
			lightBoxOff(true);			
		});		
	});
	
	
	// quit lightbox
	$('#lightbox, #closeWindow').click(lightBoxOff);
	$('#artistPanel input, #artistPanel').keydown(function(event) {
		if(event.which == 27) {
			lightBoxOff();
		}
	});

    var options = { 
            beforeSubmit:  showRequest,  // pre-submit callback 
            success:       showResponse  // post-submit callback 
     
            // other available options: 
            //url:       url         // override for form's 'action' attribute 
            //type:      type        // 'get' or 'post', override for form's 'method' attribute 
            //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
            //clearForm: true        // clear all form fields after successful submit 
            //resetForm: true        // reset the form after successful submit 
     
            // $.ajax options can be used here too, for example: 
            //timeout:   3000 
        }; 
     
        // bind form using 'ajaxForm' 
        $('#addArtistForm').ajaxForm(options); 


});