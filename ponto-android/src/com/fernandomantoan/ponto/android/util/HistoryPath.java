package com.fernandomantoan.ponto.android.util;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import android.os.Environment;

public class HistoryPath implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<String> history;

	private static HistoryPath historyPath;

	private HistoryPath() {
		if (HistoryPath.isExternalStorageReadable()) {
			if (HistoryPath.isExternalStorageWritable()) {
				this.history = new LinkedList<String>();
				this.history.add(Environment.getExternalStorageDirectory()
						.getAbsolutePath());
			}
		}
	}

	public static HistoryPath getInstance() {
		if (historyPath == null) {
			historyPath = new HistoryPath();
		}
		return historyPath;
	}

	public void restart() {
		historyPath = new HistoryPath();
	}

	public boolean isRoot() {
		return this.history.size() == 1;
	}

	public void decrement() {
		this.history.remove(this.history.size() - 1);
	}

	public void increment(Object obj) {
		this.history.add((String) obj);
	}


	public Object getCurrent() {
		try {
			return this.history.get(this.history.size() - 1);
		} catch (Exception e) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}

	}

	// Funções auxiliares de verificação do storage
	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}

	public static boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	 public static boolean isExternalStorageAvailable(){
		 if (isExternalStorageReadable()) {
			if (isExternalStorageWritable()) {
				return true;
			}
		}
		 return false;
	 }

}
