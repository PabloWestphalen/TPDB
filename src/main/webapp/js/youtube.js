var player;
$(document).ready(function() {
	// videoId: 'JW5meKfy3fY',
	// Load player api asynchronously.
	var tag = document.createElement('script');
	tag.src = "http://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
});
function onYouTubeIframeAPIReady() {
	player = new YT.Player('player', {
		/*height : '150',
		width : '170',*/
		height : '200',
		width : '200',
		events : {
			'onReady' : onPlayerReady,
			'onStateChange' : onPlayerStateChange
		}
	});
}
function onPlayerReady(evt) {
	$('#tracksListing li').click(function() {
		var video = $(this).data('video');
		if (video != null) {
			if ($(this).data('playing') == true) {
				player.pauseVideo();
				$(this).data('playing', false);
			} else {
				player.cueVideoById(video);
				player.playVideo();
				$('#player').fadeIn(1000);
				//$('.playing').toggleClass('playing');
				//$(this).toggleClass("playing");
			}
		}
	});

}
function onPlayerStateChange(evt) {
	/*if (evt.data == YT.PlayerState.PLAYING) {
		setTimeout(stopVideo, 6000);
		
	}*/
}
function stopVideo() {
	player.stopVideo();
}