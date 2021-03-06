(function(e){function n(c){var h=c.originalEvent.changedTouches[0],g="";switch(c.type){case "touchmove":g="mousemove";break;case "touchend":g="mouseup";break;default:return}var e=document.createEvent("MouseEvent");e.initMouseEvent(g,!0,!0,window,1,h.screenX,h.screenY,h.clientX,h.clientY,!1,!1,!1,!1,0,null);h.target.dispatchEvent(e);c.preventDefault()}e.fn.rateit=function(c,h){var g={},k="init";if(0==this.length)return this;var i=e.type(c);if("object"==i||void 0===c||null==c)g=e.extend({},e.fn.rateit.defaults,
c);else{if("string"==i&&void 0===h)return this.data("rateit"+(c.charAt(0).toUpperCase()+c.substr(1)));"string"==i&&(k="setvalue")}return this.each(function(){var d=e(this),a=function(a,b){arguments[0]="rateit"+(a.charAt(0).toUpperCase()+a.substr(1));return d.data.apply(d,arguments)};d.hasClass("rateit")||d.addClass("rateit");var i="rtl"!=d.css("direction");if("setvalue"==k){if(!a("init"))throw"Can't set value before init";"readonly"==c&&!a("readonly")&&(d.find(".rateit-range").unbind(),a("wired",
!1));"value"==c&&null==h&&(h=a("min"));if(a("backingfld")){var b=e(a("backingfld"));"value"==c&&b.val(h);"min"==c&&b[0].min&&(b[0].min=h);"max"==c&&b[0].max&&(b[0].max=h);"step"==c&&b[0].step&&(b[0].step=h)}a(c,h)}if(!a("init")){a("min",a("min")||g.min);a("max",a("max")||g.max);a("step",a("step")||g.step);a("readonly",void 0!==a("readonly")?a("readonly"):g.readonly);a("resetable",void 0!==a("resetable")?a("resetable"):g.resetable);a("backingfld",a("backingfld")||g.backingfld);a("starwidth",a("starwidth")||
g.starwidth);a("starheight",a("starheight")||g.starheight);a("value",a("value")||g.min);a("ispreset",void 0!==a("ispreset")?a("ispreset"):g.ispreset);if(a("backingfld")){b=e(a("backingfld"));a("value",b.hide().val());a("readonly",b[0].disabled);if("INPUT"==b[0].nodeName&&("range"==b[0].type||"text"==b[0].type))a("min",parseInt(b.attr("min"))||a("min")),a("max",parseInt(b.attr("max"))||a("max")),a("step",parseInt(b.attr("step"))||a("step"));"SELECT"==b[0].nodeName&&1<b[0].options.length&&(a("min",
Number(b[0].options[0].value)),a("max",Number(b[0].options[b[0].length-1].value)),a("step",Number(b[0].options[1].value)-Number(b[0].options[0].value)))}d.append('<div class="rateit-reset"></div><div class="rateit-range"><div class="rateit-selected" style="height:'+a("starheight")+'px"></div><div class="rateit-hover" style="height:'+a("starheight")+'px"></div></div>');i||(d.find(".rateit-reset").css("float","right"),d.find(".rateit-selected").addClass("rateit-selected-rtl"),d.find(".rateit-hover").addClass("rateit-hover-rtl"));
a("init",!0)}var f=d.find(".rateit-range");f.width(a("starwidth")*(a("max")-a("min"))).height(a("starheight"));var j="rateit-preset"+(i?"":"-rtl");a("ispreset")?d.find(".rateit-selected").addClass(j):d.find(".rateit-selected").removeClass(j);if(null!=a("value")){var l=(a("value")-a("min"))*a("starwidth");d.find(".rateit-selected").width(l)}var b=d.find(".rateit-reset"),m=function(b,d){var c=(d.changedTouches?d.changedTouches[0].pageX:d.pageX)-e(b).offset().left;i||(c=f.width()-c);c>f.width()&&(c=
f.width());0>c&&(c=0);return l=Math.ceil(c/a("starwidth")*(1/a("step")))};a("readonly")?b.hide():(a("resetable")?b.click(function(){a("value",a("min"));f.find(".rateit-hover").hide().width(0);f.find(".rateit-selected").width(0).show();a("backingfld")&&e(a("backingfld")).val(a("min"));d.trigger("reset")}):b.hide(),a("wired")||(f.bind("touchmove touchend",n),f.mousemove(function(b){var b=m(this,b),c=b*a("starwidth")*a("step"),e=f.find(".rateit-hover");e.data("width")!=c&&(f.find(".rateit-selected").hide(),
e.width(c).show().data("width",c),b=[b*a("step")+a("min")],d.trigger("hover",b).trigger("over",b))}),f.mouseleave(function(){f.find(".rateit-hover").hide().width(0).data("width","");d.trigger("hover",[null]).trigger("over",[null]);f.find(".rateit-selected").show()}),f.mouseup(function(b){var b=m(this,b),c=b*a("step")+a("min");a("value",c);a("backingfld")&&e(a("backingfld")).val(c);a("ispreset")&&(f.find(".rateit-selected").removeClass(j),a("ispreset",!1));f.find(".rateit-hover").hide();f.find(".rateit-selected").width(b*
a("starwidth")*a("step")).show();d.trigger("hover",[null]).trigger("over",[null]).trigger("rated",[c])}),a("wired",!0)),a("resetable")&&b.show())})};e.fn.rateit.defaults={min: 0, max: 5, step: 1, starwidth: 16, starheight: 16, readonly: false, resetable: false, ispreset: false};e(function(){e("div.rateit").rateit()})})(jQuery);

$(document).ready(function() {
	$('.rateit').bind('rated', function(e) {
		
		var star = $(this);
		var vl = $(this).rateit('value');
		var albumId = e.target.id;
		
		$.ajax({
			 url: '../vote', 
			 data: { id: albumId, value: vl, type: 'album' }, 
			 type: 'POST',
			 success: function (data) {
				 star.rateit('readonly', true);
			 },
			 error: function (jxhr, msg, err) {
				 alert(err);
			 }
		});		
	});
});