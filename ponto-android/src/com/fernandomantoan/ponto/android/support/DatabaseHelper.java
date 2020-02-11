package com.fernandomantoan.ponto.android.support;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	public DatabaseHelper(Context context) {
		super(context, "Ponto", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("", "Criando o banco...");
		String ddl = new StringBuffer()
			.append("CREATE TABLE Ponto ( ")
			.append("id INTEGER PRIMARY KEY, ")
			.append("date DATE, ")
			.append("obs TEXT ")
			.append(" );")
			.toString();
		//TODO alterar date para tipo text
		db.execSQL(ddl);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}