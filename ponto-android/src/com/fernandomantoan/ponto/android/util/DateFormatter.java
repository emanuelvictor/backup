package com.fernandomantoan.ponto.android.util;

public class DateFormatter {
	public static String fromMilis(long milis) {
		String signal = "";
		
		if (milis < 0)
			signal = "-";
		
		milis = Math.abs(milis);
		
		//int seconds = (int) (milis / 1000) % 60 ;
		int minutes = (int) ((milis / (1000 * 60)) % 60);
		int hours   = (int) ((milis / (1000 * 60 * 60)) % 24);
		
		return String.format("%s%02d:%02d", signal, hours, minutes);
	}
}