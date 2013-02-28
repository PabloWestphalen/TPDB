package com.jin;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.jin.util.mail.Mail;

public class Utils {

	public static List<String> getMonths() {
		List<String> months = new ArrayList<String>();
		Collections.addAll(months, new DateFormatSymbols().getMonths());
		try {
            //comentário by dvlcube
			months.remove(12);
		} catch (IndexOutOfBoundsException e) {
		}
		return months;
	}

	public static int getYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}

	public static String cleanHtml(String text) {
		return Sanitizer.cleanHtml(text);
	}

	public static String trim(String msg, Integer limit) {
		if (msg.length() > limit) {
			msg = msg.substring(0, limit);
			int lastDot = msg.lastIndexOf(".") + 1;
			if (lastDot > 0) {
				msg = msg.substring(0, lastDot);
			}
		} else {
			int nl = msg.indexOf("\n\n");
			if (nl > 0) {
				msg = msg.substring(0, nl);
				msg += " [-TRIMMED-]";
			}
		}
		return msg;
	}

	public static void reportError(HttpServletRequest request, Exception ex) {
		StringBuilder msg = new StringBuilder();
		msg.append("Requested URL: " + request.getHeader("referer") + "\n\n");
		msg.append("Params:\n");
		for(Entry<String, String[]> e : request.getParameterMap().entrySet()) {
			msg.append("\"" + e.getKey() + "\" : ");
			if(e.getValue().length == 1) {
				msg.append("\"" + e.getValue()[0] + "\"\n\n");
			} else {
				msg.append("[");
				for(int i = 0; i < e.getValue().length; i++) {
					msg.append("\"" + e.getValue()[i] + "\"");
					if(i+1 < e.getValue().length) {
						msg.append(", ");
					}
				}
				msg.append("]\n\n");
			}
		}
		Writer w = new StringWriter();
		PrintWriter pw = new PrintWriter(w);
		ex.printStackTrace(pw);

		msg.append("Exception: " + w.toString());
		String subject = "A " + ex + " occurred in TPDB";
		String myAddr = "gotjin@gmail.com";
		System.out.println(msg.toString());
		new Mail(subject, msg.toString(), myAddr, myAddr).send();
	}

	public static String urlEncode(String url) {
		url = url.replace(" ", "-").toLowerCase();
		return url;
	}

	public static String urlDecode(String url) {
		return url.replace("-", " ");
	}

	public static String getCookie(Cookie[] cookies, String cookieName) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
				return (cookie.getValue());
		}
		return null;
	}
	
	public static String formatLength(int length) {
		int minutes = length / 60;
		int seconds = length % 60;
		return ((minutes < 1 ? "0" : "") + minutes + ":"
				+ (seconds < 10 ? "0" : "") + seconds);
	}

}
