package com.vcr.app.helpers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class TimeStampUtility {

	public static long currentMiliSecond() {
		System.out.println(Timestamp.valueOf(LocalDateTime.now()).getTime());
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

	public static void milliSecondToDate(long milliSeconds) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		System.out.println(formatter.format(calendar.getTime()));
	}

	public static void main(String[] args) {
		milliSecondToDate(currentMiliSecond());

	}

}
