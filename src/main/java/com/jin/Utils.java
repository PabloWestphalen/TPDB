package com.jin;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Utils {

	public static List<String> getMonths() {
		List<String> months = new ArrayList<String>();
		Collections.addAll(months, new DateFormatSymbols().getMonths());
		try {
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

	public static String trimLong(String msg, int limit) {
		if (msg.length() > limit) {
			msg = msg.substring(0, limit);
		} else {
			msg = msg.substring(0, msg.indexOf("\n\n"));
		}
		return msg;
	}

}
