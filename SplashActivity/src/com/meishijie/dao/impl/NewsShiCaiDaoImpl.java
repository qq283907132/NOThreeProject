package com.meishijie.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;

import com.meishijie.dao.INewsShicaiDao;
import com.meishijie.entity.NewsShiCai;
import com.meishijie.other.Contants;

public class NewsShiCaiDaoImpl extends ContextWrapper implements INewsShicaiDao{
	
	private ContentResolver resolver;

	public NewsShiCaiDaoImpl(Context base) {
		super(base);
		this.resolver = getContentResolver();
	}

	@Override
	public List<NewsShiCai> getAllNewsShiCai() {
		List<NewsShiCai> lists = new ArrayList<NewsShiCai>();
		Cursor cursor = this.resolver.query(Contants.URI_NEWS_SHICAI, null, null, null, " id asc ");
		if(cursor.moveToFirst()){
			for(int i = 0;i < cursor.getCount();i++){
				cursor.moveToPosition(i);
				NewsShiCai shicai = new NewsShiCai();
				shicai.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
				shicai.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
				shicai.setClassname(cursor.getString(cursor.getColumnIndexOrThrow("classname")));
				lists.add(shicai);
			}
		}
		return lists;
	}

	@Override
	public NewsShiCai getOneNewsShiCai(int id) {
		NewsShiCai shicai = new NewsShiCai();
		Cursor cursor = this.resolver.query(ContentUris.withAppendedId(Contants.URI_NEWS_SHICAI, id), null, null, null, null);
		if(cursor.moveToFirst()){
			shicai.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
			shicai.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
			shicai.setClassname(cursor.getString(cursor.getColumnIndexOrThrow("classname")));
		}
		return shicai;
	}

	@Override
	public boolean insert(NewsShiCai shicai) {
		ContentValues values = new ContentValues();
		if(shicai != null){
			values.put("title", shicai.getTitle());
			values.put("classname", shicai.getClassname());
			
			this.resolver.insert(Contants.URI_NEWS_SHICAI, values);
			return true;
		}
		return false;
	}

}
