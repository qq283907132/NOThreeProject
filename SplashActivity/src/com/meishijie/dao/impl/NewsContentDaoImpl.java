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
import android.util.Log;

import com.meishijie.dao.INewsContentDao;
import com.meishijie.data.DBHelper;
import com.meishijie.entity.NewsContent;
import com.meishijie.other.Contants;
import com.meishijie.util.StringUtils;

public class NewsContentDaoImpl extends ContextWrapper implements INewsContentDao{
	
	private ContentResolver resolver;
	private DBHelper dbHelper;
	
	public NewsContentDaoImpl(Context base) {
		super(base);
		this.resolver = getContentResolver();
		this.dbHelper = new DBHelper(base);
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
	
	

	/**
	 * 查询前6个新闻内容
	 */
	public List<NewsContent> getPartNewsContent(String limit) {
		List<NewsContent> lists = new ArrayList<NewsContent>();
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query("newscontent", null, null, null, null, null, " id desc ", limit);
		if(cursor.moveToFirst()){
			for(int i = 0;i < cursor.getCount();i++){ 
				cursor.moveToPosition(i);
				System.out.println(cursor.getString(cursor.getColumnIndex("title")) + "*****************************8");
				NewsContent content = new NewsContent();
				this.setNewsContent(cursor, content);
				lists.add(content);
			}
		}
		db.close();
		cursor.close();
		return lists;
	}

	/**
	 * 
	 */
	@Override
	public List<NewsContent> getPartNewsContentBySuperName(String superName,String limit) {
		List<NewsContent> list = new ArrayList<NewsContent>();
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		/*String sql = "select id,title,titlepic,kouwei,gongyi,make_time,make_diff from newscontent where bclassname = ? limit 1,5";
		String[] selectionArgs = {superName};
		Cursor cursor = db.rawQuery(sql, selectionArgs);*/
		String[] columns = {"id","title","titlepic","kouwei","gongyi","make_time","make_diff"};
		
		Cursor cursor = db.query("newscontent", columns, " bclassname = ?", new String[]{superName}, null, null, null, limit);
		while(cursor.moveToNext()){
			NewsContent contents = new NewsContent();
			contents.setId(cursor.getInt(cursor.getColumnIndex("id")));
			contents.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			contents.setTitlepic(cursor.getString(cursor.getColumnIndex("titlepic")));
			contents.setKouwei(cursor.getString(cursor.getColumnIndex("kouwei")));
			contents.setGongyi(cursor.getString(cursor.getColumnIndex("gongyi")));
			contents.setMake_diff(cursor.getString(cursor.getColumnIndex("make_diff")));
			contents.setMake_time(cursor.getString(cursor.getColumnIndex("make_time")));
			list.add(contents);
		}
		db.close();
		cursor.close();
		return list;
	}

	@Override
	public int getSuperSum(String superName) {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		
		Cursor cursor = db.query("newscontent", null, " bclassname = ?", new String[]{superName}, null, null, null);
		int count = cursor.getCount();
		db.close();
		cursor.close();
		return count;
	}

	@Override
	public int getSubSum(String subName) {
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query("newscontent", null, " name = ?", new String[]{subName}, null, null, null);
		int count = cursor.getCount();
		db.close();
		cursor.close();
		return count;
	}
	

	/**
	 * 根据传进来的制作难度关键字查询数据
	 */
	@Override
	public List<NewsContent> getAllNewsByString(String ndKeys, String limit) {
		List<NewsContent> contentList = new ArrayList<NewsContent>();
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query("newscontent", null, " make_diff LIKE ? ",
				new String[] { "%" + ndKeys + "%" }, null, null, null, limit);
		if (cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				NewsContent content = new NewsContent();
				this.setNewsContent(cursor, content);
				contentList.add(content);
			}
		}
		cursor.close();
		db.close();
		return contentList;
	}

	/**
	 * 根据传进来的工艺关键字查询数据
	 */
	@Override
	public List<NewsContent> getAllNewsByGongYi(String gyKeys, String limit) {
		List<NewsContent> contentList = new ArrayList<NewsContent>();
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query("newscontent", null, " gongyi LIKE ? ",
				new String[] { "%" + gyKeys + "%" }, null, null, null, limit);
		if (cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				NewsContent content = new NewsContent();
				this.setNewsContent(cursor, content);
				contentList.add(content);
			}
		}
		cursor.close();
		db.close();
		return contentList;
	}

	/**
	 * 根据传进来的口味关键字查询数据
	 */
	@Override
	public List<NewsContent> getAllNewsByKouwei(String kwKeys, String limit) {
		List<NewsContent> contentList = new ArrayList<NewsContent>();
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query("newscontent", null, " kouwei LIKE ? ",
				new String[] { "%" + kwKeys + "%" }, null, null, null, limit);
		if (cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				NewsContent content = new NewsContent();
				this.setNewsContent(cursor, content);
				contentList.add(content);
			}
		}
		cursor.close();
		db.close();
		return contentList;
	}

	/**
	 * 模糊查询
	 */
	@Override
	public List<NewsContent> getAllNewsByTitle(String title, String limit) {
		List<NewsContent> contentList = new ArrayList<NewsContent>();
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = db.query("newscontent", null, " title LIKE ? ",
				new String[] { "%" + title + "%" }, null, null, null, limit);
		if (cursor != null) {
			Log.i("food", "++++++++++++++++++++++++++++++++");
			Log.i("food", "cursor count:" + cursor.getCount());
		} else {
			Log.i("food", "***************************");
		}
		if (cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				NewsContent content = new NewsContent();
				this.setNewsContent(cursor, content);
				contentList.add(content);
			}
		}
		cursor.close();
		db.close();
		return contentList;
	}
	
	@Override
	public int getCountByDataNd(String str) {
	
		// Cursor cursor =
		// resolver.query(Contants.URI_NEWS_CONTENT," title LIKE ? ", new
		// String[]{"%" + str + "%"}, null, " id asc");
		Cursor cursor = resolver.query(Contants.URI_NEWS_CONTENT, null,
				" make_diff LIKE ? ", new String[] { "%" + str + "%" }, null);
		cursor.close();
		return cursor.getCount();
		
	}

	@Override
	public int getCountByDataGy(String strgy) {
		Cursor cursor = resolver.query(Contants.URI_NEWS_CONTENT, null,
				" gongyi LIKE ? ", new String[] { "%" + strgy + "%" }, null);
		cursor.close();
		return cursor.getCount();
	}

	@Override
	public int getCountByDataKw(String strkw) {
		Cursor cursor = resolver.query(Contants.URI_NEWS_CONTENT, null,
				" kouwei LIKE ? ", new String[] { "%" + strkw + "%" }, null);
		cursor.close();
		return cursor.getCount();
	}

	@Override
	public int getCountByDataKey(String keys) {
		Cursor cursor = resolver.query(Contants.URI_NEWS_CONTENT, null,
				" title LIKE ? ", new String[] { "%" + keys + "%" }, null);
		cursor.close();
		return cursor.getCount();
	}

}
