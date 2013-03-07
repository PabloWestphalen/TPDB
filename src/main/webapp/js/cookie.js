function setCookie(name, value, days) {
	// By default, there is no expiration so the cookie is temporary
	var expires = "";

	// Specifying a number of days makes the cookie persistent
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toGMTString();
	}

	// Set the cookie to the name, value, and expiration date
	document.cookie = name + "=" + value + expires + "; path=/";
}

function getCookie(name) {
	// Find the specified cookie and return its value
	var searchName = name + "=";
	var cookies = document.cookie.split(';');
	for ( var i = 0; i < cookies.length; i++) {
		var c = cookies[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(searchName) == 0) {
			var cook = c.substring(searchName.length, c.length);
			var cookValue = cook.substr((cook.indexOf('=') + 1), cook.length);
			return cookValue;
		}
	}
	return null;
}

function eraseCookie(name) {
	// Erase the specified cookie
	writeCookie(name, "", -1);
}
