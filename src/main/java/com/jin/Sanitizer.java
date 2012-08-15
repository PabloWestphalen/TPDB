package com.jin;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class Sanitizer {

	public static String clean(String text) {
		text = text.replace("\r\n", "[-LB-]");
		text = text.replace("\n", "[-LB-]");
		text = text.replace("\r", "[-LB-]");
		text = Jsoup.clean(text, Whitelist.none());
		text = text.replace("[-LB-][-LB-]", "\n\n");
		text = text.replace("[-LB-]", "\n");
		return text;
	}

	public static String cleanHtml(String text) {
		text = text.replace("\r\n", "[-LB-]");
		text = text.replace("\n", "[-LB-]");
		text = text.replace("\r", "[-LB-]");
		Whitelist allowed = new Whitelist();
		allowed.addTags("p", "br");
		text = text.replace("[-LB-][-LB-]", "</p><p>");
		text = text.replace("[-LB-]", "<br />");
		//text = "<p>" + text + "</p>";
		text = Jsoup.clean(text, allowed);
		return text;

	}
}
