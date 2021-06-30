package com.sbs.exam.util;

import java.text.SimpleDateFormat;
import java.util.Comparator;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String dateStr = format1.format(System.currentTimeMillis());

		return dateStr;
	}	
}

class DateItem {
	String datetime;

	DateItem(String date) {
		this.datetime = date;
	}
}

class SortByDate implements Comparator<DateItem> {
	@Override
	public int compare(DateItem a, DateItem b) {
		return a.datetime.compareTo(b.datetime);
	}
}