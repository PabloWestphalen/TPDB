var player;
var trackNames;
var currentTrack;
var tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

$(document).ready(function() {
	$('#player').addClass('playerLoading');
	$('#tracksListing li a, .playAll').click(function(event) {
		event.preventDefault();
	});
	var link = '<a href="#" id="enableNotifications">Enable notifications</a>';
	$('footer').append(link);
	$('#enableNotifications').click(function(event) {
		event.preventDefault();
		enableNotifications();
	});
    // set trackNames
	var i = 0;
	trackNames = new Array();
	$('ol li').each(function() {
		var video = $(this).data('video');
		if (video != null) {
			trackNames[i++] = $(this).data('trackname');
		}
	});

});

function onYouTubeIframeAPIReady() {
	player = new YT.Player('player', {
		height : '220',
		width : '200',
		events : {
			'onReady' : onPlayerReady,
			'onStateChange' : onPlayerStateChange,
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
// var songName = "Glory Box";
var coverUrl = ".." + $('.coverImage').attr('src');

function enableNotifications() {
	window.webkitNotifications.requestPermission();
}

function showNotification(songName) {
	if (window.webkitNotifications && window.webkitNotifications.checkPermission() == 0) {
		var notification = window.webkitNotifications.createNotification(coverUrl,
				'\u266c ' + songName, "by " + artistName);
		notification.show();
		setTimeout(function() {
			notification.close();
		}, 3000);
	}
	else {
		var icon = 'background: url(' + coverUrl + ') no-repeat 10px 10px;';
		var notificationDiv = ' <div class="notification" style="' + icon + '"> ' +
            '<h1>\u266c ' + songName + '</h1>' +
            '<h2>by ' + artistName + '</h2></div>';
		  $.blockUI({ 
	            message: notificationDiv, 
	            fadeIn: 700, 
	            fadeOut: 700, 
	            timeout: 2000, 
	            showOverlay: false, 
	            centerY: false, 
	            css: { 
	                width: '350px', 
	                top: '10px', 
	                left: '', 
	                right: '10px', 
	                border: 'none', 
	                padding: '5px', 
	                backgroundColor: '#222', 
	                '-webkit-border-radius': '10px', 
	                '-moz-border-radius': '10px', 
	                //opacity: .5, 
	                color: '#fff' 
	            } 
	        }); 
	}
}

function onPlayerStateChange(event) {
	if(player.getPlaylistIndex() >= 0) {
		if(event.data == 1 && currentTrack != player.getPlaylistIndex()) {
			currentTrack = player.getPlaylistIndex();
			showNotification(trackNames[currentTrack]);
		}
	}
}
//