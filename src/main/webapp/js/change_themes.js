function changeColor() {
	var styleSheet = document.getElementsByTagName("link")[1];
	var chosenTheme;
	if(this.className === "changeOrange") {
		styleSheet.href = "css/orange.css";
		chosenTheme = "theme=orange";
	} else if(this.className === "changeBlue") {
		styleSheet.href = "css/blue.css";
		chosenTheme = "theme=blue";
	} else if(this.className === "changeGreen") {
		styleSheet.href = "css/green.css";
		chosenTheme = "theme=green";
	} else if(this.className === "changeNone") {
		styleSheet.href = "";
		chosenTheme = "theme=";
	} else {
		styleSheet.href = "css/orange.css";
	}
	//setCookie("theme", chosenTheme);
}

window.onload = function() {
	/*var color = getCookie("theme");
	if(color != "") {
		document.getElementsByTagName("link")[1].href="css/"+color+".css";
	}*/

	var sidebarLinks = document.getElementById("sidebar").getElementsByTagName("a");
	for(var i = 0; i < sidebarLinks.length; i++) {
		var link = sidebarLinks[i];
		link.addEventListener("click", changeColor, false);
	}
	
	if(document.getElementById("testeid")) {
		x = document.getElementById("testeid");
		x.addEventListener("click", makeRequest, false);
	}
	
	for (var i = 0; i < document.getElementsByTagName("input").length; i++) {
		var j = document.getElementsByTagName("input")[i];			
		if(j.type == "password") {
			j.addEventListener("focus", formValidation.handleFocus, false);
			j.addEventListener("blur", formValidation.handleBlur, false);
		} else if(j.type == "text") {
			j.addEventListener("focus", formValidation.handleFocus, false);				
			j.addEventListener("blur", formValidation.handleBlur, false);				
			if(j.name == "telPrefix") {
				j.addEventListener("keyup", formValidation.changeFocus, false);
			}
			if(j.name == "telephone") {
				j.addEventListener("keyup", formValidation.changeFocus, false);
			}
		}  else if(j.type == "submit" || j.type == "password") {
			j.addEventListener("submit", formValidation.handleSubmit, false);
			j.addEventListener("click", formValidation.handleSubmit, false);
		}
	}
}