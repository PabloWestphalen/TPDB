var $username, $email, $commentText;
var userNameError, emailError, commentError;

function showResponse(response) {
	$('textarea[name="commentBody"]').val('');
	var comment = '<div class="comment" id="cid' + response.id + '">';
	comment += '<div class="commentUser">';
	comment += '<img src="' + response.imageUrl + '" />';
	comment += '</div>';
	comment += '<div class="arrow-left"></div>';
	comment += '<div class="commentBody">';
	comment += response.comment;
	comment += '</div>';
	comment += '<span>' + response.userName + ' @ ' + response.date + '</span>';
	comment += '</div>';
	var new_comment = $(comment).fadeToggle(400);
	$('#comments_wrapper').append(new_comment);
	$('html,body').animate({scrollTop: $('footer').offset().top}, 0);
	/* new_comment.fadeToggle(400); */
}

$(document).ready(function() {
	$username = $('input[name="userName"]');
	$email = $('input[name="email"]');
	$commentText = $('textarea[name="commentBody"]');

	$username.change(userNameTest);
	$email.change(emailTest);
	$commentText.change(commentTest);

	if (getCookie("userName") == null) {
		$username.val('Your name');
		$username.one("focus", function() {
			$username.val('');
		});
	} else {
		$username.val(getCookie("userName"));
	}

	if (getCookie("email") == null) {
		$email.val('Email');
		$email.one("focus", function() {
			$email.val('');
		});
	} else {
		$email.val(replaceAll(getCookie("email"), "\"", ""));
	}

	$('#commentForm').submit(function(event) {
		event.preventDefault();
		commentTest();
		userNameTest();
		emailTest();
		if (!userNameError && !emailError && !commentError) {
			$(this).ajaxSubmit({
				dataType : "json",
				success : showResponse, // post-submit callback
			});
		}
	});

});

function commentTest() {
	if ($commentText.val() == '') {
		$commentText.addClass("input_invalid");
		commentError = true;
	} else {
		$commentText.removeClass("input_invalid");
		commentError = false;
	}
}

function userNameTest() {
	if ($username.val() == '') {
		$username.addClass("input_invalid");
		userNameError = true;
	} else {
		$username.removeClass("input_invalid");
		userNameError = false;
	}
}

function emailTest() {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	if ($email.val() == '' || !emailReg.test($email.val())) {
		$email.addClass("input_invalid");
		emailError = true;
	} else {
		$email.removeClass("input_invalid");
		emailError = false;
	}
}

function replaceAll(string, token, newtoken) {
	while (string.indexOf(token) != -1) {
		string = string.replace(token, newtoken);
	}
	return string;
}

function getCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for ( var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}