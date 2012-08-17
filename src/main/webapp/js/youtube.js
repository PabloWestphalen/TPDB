var player;
var playUrl;
$(document).ready(function() {
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
	$('#player').fadeOut(0);
	$('#tracksListing li').click(function(event) {
		var video = $(this).data('video');
		if (video != null) {
			playUrl = video;
			//$('#player').fadeIn(400, 'swing', playVideo);
			$('#player').fadeIn();
			onPlayerReady(null);
		}
	});
}

function playVideo() {
}

function onPlayerReady(evt) {
	player.cueVideoById(playUrl);
	player.playVideo();
}
function onPlayerStateChange(evt) {
}
function stopVideo() {
	player.stopVideo();
}