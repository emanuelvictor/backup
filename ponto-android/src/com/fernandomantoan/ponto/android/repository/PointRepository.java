package com.fernandomantoan.ponto.android.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fernandomantoan.ponto.android.entity.Daily;
import com.fernandomantoan.ponto.android.entity.Point;
import com.fernandomantoan.ponto.android.support.DatabaseHelper;

@SuppressLint("SimpleDateFormat")
public class PointRepository {
	protected DatabaseHelper database;
	protected String[] columns = { "id", "date", "obs" };

	public PointRepository(DatabaseHelper database) {
		this.database = database;
	}

//	private void insert(Point point) {
//		database.getWritableDatabase().insert("Ponto", null,
//				point.toContentValues());
//	}

	public void save(Point point) {
		Log.i("Salvando ponto: ", point.toString() );
		if (point.getId() == null) {
//			this.insert(point);
			database.getWritableDatabase().insert("Ponto", null,
					point.toContentValues());
		} else {
			database.getWritableDatabase().update("Ponto",
					point.toContentValues(), "id = ?",
					new String[] { point.getId().toString() });
		}
	}

//	public void remove(Point point) {
//		database.getWritableDatabase().delete("Ponto", "id = ?",
//				new String[] { point.getId().toString() });
//	}

	public void updateAll(List<Point> points) throws Exception {
		SQLiteDatabase db = database.getWritableDatabase();

		db.beginTransaction();
		try {
			db.delete("Ponto", null, null);

			for (Point point : points) {
				db.insertOrThrow("Ponto", null, point.toContentValues());
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	public List<Point> findAllByDate(Calendar calendar) {
		ArrayList<Point> points = new ArrayList<Point>();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Cursor cursor;
		if (calendar == null) {
			cursor = database.getReadableDatabase().query("Ponto", columns,
					null, null, null, null, "id DESC");
		} else {
			cursor = database.getReadableDatabase().query("Ponto", columns,
					"date(date) = ?",
					new String[] { df.format(calendar.getTime()) }, null, null,
					"id ASC", null);
			Log.i("Lendo ponto pela data", df.format(calendar.getTime()));
		}

		while (cursor.moveToNext()) {
			Point p = new Point(cursor.getLong(0), cursor.getString(1),
					cursor.getString(2));
			points.add(p);
		}
		cursor.close();
		return points;
	}

	// Utilizar somente esse método para pesquisar todos
	public List<Daily> findDaily() {

		// Pega todos os pontos
		ArrayList<Daily> daily = new ArrayList<Daily>();
		LinkedHashMap<String, ArrayList<Point>> groupPoints = new LinkedHashMap<String, ArrayList<Point>>();

		Cursor cursor = database.getReadableDatabase().query("Ponto", columns,
				null, null, null, null, "id ASC");

		while (cursor.moveToNext()) {
			Point p = new Point(cursor.getLong(0), cursor.getString(1),
					cursor.getString(2));

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate = df.format(p.getDate().getTime());
			if (!groupPoints.containsKey(stringDate))
				groupPoints.put(stringDate, new ArrayList<Point>());

			groupPoints.get(stringDate).add(p);
		}
		cursor.close();

		TreeSet<String> keys = new TreeSet<String>(groupPoints.keySet());
		Iterator<String> iterator = keys.descendingIterator();

		while (iterator.hasNext()) {
			String date = iterator.next();
			Daily p = new Daily(groupPoints.get(date));
			daily.add(p);
		}
		return daily;
	}

}