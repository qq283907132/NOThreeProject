package com.meishijie.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.meishijie.dao.INewsClassDao;
import com.meishijie.data.DBHelper;
import com.meishijie.entity.NewsClass;
import com.meishijie.entity.NewsContent;
import com.meishijie.other.Contants;

public class NewsClassDaoImpl extends ContextWrapper implements INewsClassDao {
	
	private ContentResolver resolver;
	private DBHelper dbHelper;
	public NewsClassDaoImpl(Context base) {
		super(base);
		this.resolver = getContentResolver();
		this.dbHelper = new DBHelper(base);
	}

	@Override
	public List<NewsClass> getAllNewsClass() {
		List<NewsClass> lists = new ArrayList<NewsClass>();
		Cursor cursor = this.resolver.query(Contants.URI_NEWS_CLASS, null, null, null, " id asc ");
		if(cursor.moveToFirst()){
			for(int i = 0;i < cursor.getCount();i++){
				cursor.moveToPosition(i);
				NewsClass news = new NewsClass();
				this.setData(cursor, news);
				lists.add(news);
			}
		}
		cursor.close();
		return lists;
	}

	@Override
	public NewsClass getOneNewsClass(int id) {
		NewsClass news = new NewsClass();
		Cursor cursor = this.resolver.query(ContentUris.withAppendedId(Contants.URI_NEWS_CLASS, id), null, null, null, null);
		if(cursor.moveToFirst()){
			this.setData(cursor, news);
		}
		cursor.close();
		return news;
	}
	
	private void setData(Cursor cursor,NewsClass news){
		news.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
		news.setBclassname(cursor.getString(cursor.getColumnIndexOrThrow("bclassname")));
		news.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
		news.setType(cursor.getInt(cursor.getColumnIndexOrThrow("type")));
		news.setNewsnum(cursor.getInt(cursor.getColumnIndexOrThrow("newsnum")));
		news.setOnclick(cursor.getInt(cursor.getColumnIndexOrThrow("onclick")));
		news.setTop_img(cursor.getString(cursor.getColumnIndexOrThrow("top_img")));
		news.setTop_word(cursor.getString(cursor.getColumnIndexOrThrow("top_word")));
	}

	@Override
	public boolean insert(NewsClass newsClass) {
		ContentValues values = new ContentValues();
		if(newsClass != null){
			values.put("bclassname", newsClass.getBclassname());
			values.put("name", newsClass.getName());
			values.put("type", newsClass.getType());
			values.put("newsnum", newsClass.getNewsnum());
			values.put("onclick", newsClass.getOnclick());
			values.put("top_img", newsClass.getTop_img());
			values.put("top_word", newsClass.getTop_word());
			
			this.resolver.insert(Contants.URI_NEWS_CLASS, values);
			return true;
		}
		return false;
	}

	@Override
	public List<NewsClass> getSuperClassName() {
		
		List<NewsClass> lists = new ArrayList<NewsClass>();
		String sql = "select distinct bclassname from newsclass";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			NewsClass newsClass= new NewsClass();
			String bclassname = cursor.getString(cursor.getColumnIndex("bclassname"));
			if(bclassname!= null&&!bclassname.equals("")){
				newsClass.setBclassname(cursor.getString(cursor.getColumnIndex("bclassname")));
				lists.add(newsClass);
			}
		}
		cursor.close();
		return lists;
	}

	@Override
	public List<NewsClass> getSubClassByBigName(String superName) {
		List<NewsClass> lists = new ArrayList<NewsClass>();
		String sql = "select distinct name from newsclass where bclassname = ?";
		String[] selectionArgs = { superName };
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		while (cursor.moveToNext()) {
			NewsClass newsClass = new NewsClass();
			newsClass.setName(cursor.getString(cursor.getColumnIndex("name")));
			lists.add(newsClass);
		}
		cursor.close();
		return lists;
	}
	
}
