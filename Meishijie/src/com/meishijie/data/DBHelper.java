package com.meishijie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.meishijie.other.Contants;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, Contants.DB_NAME, null, Contants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
