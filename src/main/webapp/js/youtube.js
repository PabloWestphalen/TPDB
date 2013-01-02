var player;
var tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

$(document).ready(function() {
	$('#player').addClass('playerLoading');
	$('#tracksListing li a, .playAll').click(function(event) {
		event.preventDefault();
	});
	showNotification();
});

function onYouTubeIframeAPIReady() {
	player = new YT.Player('player', {
		height : '220',
		width : '200',
		events : {
			'onReady' : onPlayerReady
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
	$('#player').hide();
	// start playAll
	$('.playAll').click(function() {
		var playlist = new Array();
		var i = 0;
		$('ol li').each(function() {
			var video = $(this).data('video');
			if (video != null) {
				playlist[i++] = video;
			}
		});
		player.loadPlaylist(playlist, 0, 0, "small");
		$('#player').removeClass('playerLoading');
		$('#player').fadeIn();
	});
	// finish playAll
}
//var artistName = "Portishead";
var artistName = $('h2').text().replace("/\((.*)\)/", $1);
var songName = "Glory Box";
var coverUrl = ".." + $('.coverImage').attr('src');

var notifier = window.webkitNotifications;

function showNotification() {
	if (notifier.checkPermission() != 0) {
		notifier.requestPermission(permissionGranted);
		return 0;
	}
	var notification = notifier.createNotification(coverUrl, 'Now playing...',
			artistName + songName);
	notification.show();
}

function permissionGranted() {
	if (window.webkitNotifications.checkPermission() == 0) {
		showNotification();
	}
}