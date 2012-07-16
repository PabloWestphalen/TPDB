$(document).ready(function() {

	var active = "#default";
	var inactive;
	var tmp;

	$('#artistsNav a').click(function(event) {
	target = '#' + event.target.className;
	event.preventDefault();

	$(active).fadeToggle(200, function() {
		$(target).fadeToggle(200);
		tmp = active;
		active = target;
		inactive = tmp;
		});
	});
});