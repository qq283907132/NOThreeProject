package com.meishijie.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;

import com.meishijie.dao.INewsContentDao;
import com.meishijie.entity.NewsContent;
import com.meishijie.other.Contants;
import com.meishijie.util.StringUtils;

public class NewsContentDaoImpl extends ContextWrapper implements INewsContentDao{
	
	private ContentResolver resolver;
	
	public NewsContentDaoImpl(Context base) {
		super(base);
		this.resolver = getContentResolver();
	}

	@Override
	public List<NewsContent> getAllNewsContent() {
		List<NewsContent> lists = new ArrayList<NewsContent>();
		Cursor cursor = this.resolver.query(Contants.URI_NEWS_CONTENT, null, null, null, " id asc");
		if(cursor.moveToFirst()){
			for(int i = 0;i < cursor.getCount();i++){
				cursor.moveToPosition(i);
				NewsContent content = new NewsContent();
				this.setNewsContent(cursor, content);
				lists.add(content);
			}
		}
		cursor.close();
		return lists;
	}

	@Override
	public NewsContent getOneNewsContent(int id) {
		NewsContent content = new NewsContent();
		Cursor cursor = this.resolver.query(ContentUris.withAppendedId(Contants.URI_NEWS_CONTENT, id), null, null, null, " id asc ");
		if(cursor.moveToFirst()){
			this.setNewsContent(cursor, content);
		}
		cursor.close();
		return content;
	}
	
	private void setNewsContent(Cursor cursor , NewsContent content){
		int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
		content.setId(id);
		content.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
		content.setWriter(cursor.getString(cursor.getColumnIndexOrThrow("writer")));
		content.setWriter_p(cursor.getString(cursor.getColumnIndexOrThrow("writer_p")));
		content.setSmalltext(StringUtils.decodeMeishiString(id, cursor.getString(cursor.getColumnIndexOrThrow("smalltext"))));
		content.setFclassname(cursor.getString(cursor.getColumnIndexOrThrow("fclassname")));
		content.setBclassname(cursor.getString(cursor.getColumnIndexOrThrow("bclassname")));
		content.setClassname(cursor.getString(cursor.getColumnIndexOrThrow("classname")));
		content.setZhuliao(cursor.getString(cursor.getColumnIndexOrThrow("zhuliao")));
		content.setFuliao(StringUtils.decodeMeishiString(id, cursor.getString(cursor.getColumnIndexOrThrow("fuliao"))));
		content.setTiaoliao(StringUtils.decodeMeishiString(id, cursor.getString(cursor.getColumnIndexOrThrow("tiaoliao"))));
		content.setContent(StringUtils.decodeMeishiString(id, cursor.getString(cursor.getColumnIndexOrThrow("content"))));
		content.setKouwei(cursor.getString(cursor.getColumnIndexOrThrow("kouwei")));
		content.setGongyi(cursor.getString(cursor.getColumnIndexOrThrow("gongyi")));
		content.setTitlepic(cursor.getString(cursor.getColumnIndexOrThrow("titlepic")));
		content.setNewsphoto(cursor.getString(cursor.getColumnIndexOrThrow("newsphoto")));
		content.setOnclick(cursor.getInt(cursor.getColumnIndexOrThrow("onclick")));
		content.setMake_time(cursor.getString(cursor.getColumnIndexOrThrow("make_time")));
		content.setMake_pretime(cursor.getString(cursor.getColumnIndexOrThrow("make_pretime")));
		content.setMake_diff(cursor.getString(cursor.getColumnIndexOrThrow("make_diff")));
		content.setMake_amount(cursor.getString(cursor.getColumnIndexOrThrow("make_amount")));
		content.setHref(cursor.getString(cursor.getColumnIndexOrThrow("href")));
		content.setTips(StringUtils.decodeMeishiString(id, cursor.getString(cursor.getColumnIndexOrThrow("tips"))));
		content.setYyxx(StringUtils.decodeMeishiString(id, cursor.getString(cursor.getColumnIndexOrThrow("yyxx"))));
		content.setHeat(cursor.getString(cursor.getColumnIndexOrThrow("heat")));
	}

	@Override
	public boolean insertNewsContent(NewsContent newsContent) {
		ContentValues values = new ContentValues();
		if(newsContent != null){
			values.put("title", newsContent.getTitle());
			values.put("writer", newsContent.getWriter());
			values.put("writer_p", newsContent.getWriter_p());
			values.put("smalltext", newsContent.getSmalltext());
			values.put("fclassname", newsContent.getFclassname());
			values.put("bclassname", newsContent.getBclassname());
			values.put("classname", newsContent.getClassname());
			values.put("zhuliao", newsContent.getZhuliao());
			values.put("fuliao", newsContent.getFuliao());
			values.put("tiaoliao", newsContent.getTiaoliao());
			values.put("content", newsContent.getContent());
			values.put("kouwei", newsContent.getKouwei());
			values.put("gongyi", newsContent.getGongyi());
			values.put("titlepic", newsContent.getTitlepic());
			values.put("newsphoto", newsContent.getNewsphoto());
			values.put("onclick", newsContent.getOnclick());
			values.put("make_time", newsContent.getMake_time());
			values.put("make_pretime", newsContent.getMake_pretime());
			values.put("make_diff", newsContent.getMake_diff());
			values.put("make_amount", newsContent.getMake_amount());
			values.put("href", newsContent.getHref());
			values.put("tips", newsContent.getTips());
			values.put("yyxx", newsContent.getYyxx());
			values.put("heat", newsContent.getHeat());
			
			this.resolver.insert(Contants.URI_NEWS_CONTENT, values);
			return true;
		}
		return false;
	}

}
