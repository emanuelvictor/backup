package br.edu.udc.sistemas.ia6.emanuelvictor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeConverter {
	private static SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	public static Date toDate(String strCalendar) {
		try {
			return formatter.parse(strCalendar);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date toTime(String strCalendar) {
		try {
			return formatter.parse(strCalendar);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date toDateTime(String strCalendar) {
		try {
			return formatter.parse(strCalendar);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	// Vai pegar o objeto, vai ver se é um date, datetime ou time e converter para string
	public static String toString(Calendar cldString) {
		return formatter.format(cldString.getTime());
	}
}
