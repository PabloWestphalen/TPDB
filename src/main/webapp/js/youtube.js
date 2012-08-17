var player;
var playUrl;
var queue = false;
$(document).ready(function() {
	var tag = document.createElement('script');
	tag.src = "https://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
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
	$('#tracksListing li a').click(function(event) {
		event.preventDefault();
	});
	$('#tracksListing li').click(function(event) {
		var video = $(this).data('video');
		if (video != null) {
			playUrl = video;
			if(!queue) {
				$('#player').fadeIn();
			} else {
				player.cueVideoById(playUrl);
				player.playVideo();
				queue = true;
			}
		}
	});
}

function playVideo() {
	//player.cueVideoById(playUrl);
	//player.playVideo();
}

function onPlayerReady(evt) {
	if(playUrl != null) {
		player.cueVideoById(playUrl);
		player.playVideo();
		queue = true;
		
	}
}
function onPlayerStateChange(evt) {
}
function stopVideo() {
	player.stopVideo();
}