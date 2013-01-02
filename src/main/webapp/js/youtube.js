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
	var link = '<a href="#" id="enableNotifications">Enable notifications</a>';
	$('footer').append(link);
	$('#enableNotifications').click(enableNotifications);
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
var artistName = $('h2').text().match(/\((.*)\)/, "$1")[1];
var songName = "Glory Box";
var coverUrl = ".." + $('.coverImage').attr('src');

function enableNotifications() {
	window.webkitNotifications.requestPermission();
}

function showNotification() {
	if (window.webkitNotifications.checkPermission() != 0) {
		alert('permissions are not allowed');
		return 0;
	} 
	var notification = window.webkitNotifications.createNotification(coverUrl,
			'Now playing...', artistName + songName);
	notification.show();
}
