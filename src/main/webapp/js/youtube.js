var player;
var playUrl;
$(document).ready(function() {
	// videoId: 'JW5meKfy3fY',
	// Load player api asynchronously.
	var tag = document.createElement('script');
	tag.src = "https://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
});
function onYouTubeIframeAPIReady() {
	player = new YT.Player('player', {
		/*height : '150',
		width : '170',*/
		height : '200',
		width : '250',
		events : {
			'onReady' : onPlayerReady,
			'onStateChange' : onPlayerStateChange
		}
	});
	//alert('escondendo.');
	$('#player').fadeOut(0);
	//alert('escondido.');
	//alert('player api ready event fired');
	$('#tracksListing li').click(function() {
		//alert('handling click');
		var video = $(this).data('video');
		if (video != null) {
			//alert('mostrando');
			//alert('erro no fadeIn?');
			//$('#player').fadeIn(1000);
			//alert('mostrado');
			//alert('mostrando de verdade agora...');
			$('#player').fadeIn(1000);
			//alert('done!');
			//$('#player').show();
			player.cueVideoById(video);
			//$('.playing').toggleClass('playing');
			//$(this).toggleClass("playing");
		}
	});
}
function onPlayerReady(evt) {
	player.playVideo();
}
function onPlayerStateChange(evt) {
	/*if (evt.data == YT.PlayerState.PLAYING) {
		setTimeout(stopVideo, 6000);
		
	}*/
}
function stopVideo() {
	player.stopVideo();
}