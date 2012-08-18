/*firefox = ok
ie deia = ok
chrome deia = ok
meu ie = crash
meu opera = ok
meu chrome = ok
*/
var player;
var playUrl;
var loaded = false;
$(document).ready(function() {
	$('#player').addClass('playerLoading');
	var tag = document.createElement('script');
	tag.src = "https://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
	$('#tracksListing li a').click(function(event) {
		event.preventDefault();
	});
});
function onYouTubeIframeAPIReady() {
	player = new YT.Player('player', {
		height : '200',
		width : '200',
		events : {
			'onReady' : onPlayerReady,
			'onStateChange' : onPlayerStateChange
		}
	});
}

function onPlayerReady(evt) {
	$('#tracksListing li').click(function(event) {
		var video = $(this).data('video');
		if (video != null) {
			player.loadVideoById(video);
			$('#player').removeClass('playerLoading');
			$('#player').fadeIn();
		}
	});
	$('#player').fadeOut(0);
}