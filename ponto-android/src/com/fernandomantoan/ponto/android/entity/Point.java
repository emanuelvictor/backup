package com.fernandomantoan.ponto.android.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.ContentValues;

@SuppressLint("SimpleDateFormat")
public class Point {


	private Long id;
	private Calendar date;
	private String obs;

	public Point() {
//		super();
		// TODO Auto-generated constructor stub
		this.id = null;
		this.date = null;
		this.obs = null;
	}

	
	public Point(Long id, String date, String obs) {
		this.setId(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		this.date = Calendar.getInstance();
		try {
			this.date.setTime(df.parse(date));
		} catch (Exception e) {

		}

		if (obs != null) {
			this.setObs(obs);
		}
	}

	public Point(Calendar calendar) {
		this.date = calendar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public ContentValues toContentValues() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ContentValues contentValues = new ContentValues();
		if (this.getDate() != null) {
			contentValues.put("date", df.format(this.date.getTime()));
//			Log.i("Nao null", "Nao null");
		} else {
			df = new SimpleDateFormat("yyyy-MM-dd");
			contentValues.put("date", df.format(this.date.getTime()));
//			Log.i("null", "Nao null");
		}
		if (this.getObs() != null) {
			contentValues.put("obs", this.obs);
		}
		return contentValues;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", date=" + date + ", obs=" + obs + "]";
	}
}