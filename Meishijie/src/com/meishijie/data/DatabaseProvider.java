package com.meishijie.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.meishijie.other.Contants;

/**
 * @Title: DatabaseProvider
 * @Description: contentprovider类 
 * @Company: null
 * @author  DHA
 * @date    Feb 27, 2014
 */
public class DatabaseProvider extends ContentProvider{
	
	private static final String TAG = "DatabaseProvider";
	//uri匹配类
	private static UriMatcher uriMatcher ;
	//匹配结果
	private int match ;
	//数据库操作的支持类
	private DBHelper dbHelper ;
	//数据库类
	private SQLiteDatabase db ;
	
	static {
		
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_SHICAI, 1);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_SHICAI + "/#", 0);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_QINGDANG, 1);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_QINGDANG + "/#", 0);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_SHICAI, 1);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_SHICAI + "/#", 0);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_CLASS, 1);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_CLASS + "/#", 0);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_CONTENT, 1);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_CONTENT + "/#", 0);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_FAV, 1);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_NEWS_FAV + "/#", 0);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_SHOPPING_CAIPU, 1);
		
		uriMatcher.addURI(Contants.AUTHORITY, Contants.TABLE_SHOPPING_CAIPU + "/#", 0);
	}
	
	@Override
	public boolean onCreate() {
		this.dbHelper = new DBHelper(getContext());
		return true;
	}
	
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		this.match = uriMatcher.match(uri);
		if(this.match != Contants.VISIT_ALL){
			throw new IllegalArgumentException("插入的数据不能指定ID！");
		}
		String table_name = getTableName(uri.toString());
		this.db = this.dbHelper.getWritableDatabase();
		if(values == null){
			throw new NullPointerException("你插入的为空值 !");
		}
		Log.i(TAG, "准备插入" + table_name + "中的数据！");
		Long id = this.db.insert(table_name, null, values);
		if(id > 0){
			this.notifyDataChanged(uri);
			Log.i(TAG, "数据插入成功！,已通知数据更改");
			return ContentUris.withAppendedId(uri, id);
		}
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		this.db = this.dbHelper.getReadableDatabase();
		this.match = uriMatcher.match(uri);
		String table_name = getTableName(uri.toString());
		switch (this.match) {
		case Contants.VISIT_ALL:
			Log.i(TAG, "准备查询" + table_name + "中的所有数据！");
			break;
		case Contants.VISIT_ONE:
			long id = ContentUris.parseId(uri);
			selection = " id = ?";
			selectionArgs = new String[]{String.valueOf(id)};
			
			Log.i(TAG, "准备查询" + table_name + "中ID为 " + id + "的数据");
			break;
		default:
			throw new IllegalArgumentException("错误的URI ：" + uri + "，数据查询失败！");
		}
		Log.i(TAG, "数据查询成功！");
		return this.db.query(table_name, projection, selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		this.db = this.dbHelper.getWritableDatabase();
		this.match = uriMatcher.match(uri);
		String table_name = getTableName(uri.toString());
		switch (this.match) {
		case Contants.VISIT_ALL:
			Log.i(TAG, "准备更新" + table_name + "中的所有数据！");
			break;
		case Contants.VISIT_ONE:
			long id = ContentUris.parseId(uri);
			selection = " id = ? " ;
			selectionArgs = new String[]{String.valueOf(id)};
			
			Log.i(TAG, "准备更新" + table_name + "中ID为 " + id + "的数据");
			break;
		default:
			throw new IllegalArgumentException("错误的URI ：" + uri + "，数据更新失败！");
		}
		int count = this.db.update(table_name, values, selection, selectionArgs);
		if(count > 0){
			notifyDataChanged(uri);
			Log.i(TAG, "数据更新成功！,已通知数据更改");
		}
		return count;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		this.db = this.dbHelper.getWritableDatabase();
		this.match = uriMatcher.match(uri);
		String table_name = getTableName(uri.toString());
		switch (this.match) {
		case Contants.VISIT_ALL:
			Log.i(TAG, "准备删除" + table_name + "中的所有数据！");
			break;
		case Contants.VISIT_ONE:
			long id = ContentUris.parseId(uri);
			selection = " id = ? " ;
			selectionArgs = new String[]{String.valueOf(id)};
			
			Log.i(TAG, "准备删除" + table_name + "中ID为 " + id + "的数据");
			break;
		default:
			throw new IllegalArgumentException("错误的URI ：" + uri + "，数据删除失败！");
		}
		int count = this.db.delete(table_name, selection, selectionArgs);
		if(count > 0){
			this.notifyDataChanged(uri);
			Log.i(TAG, "数据删除成功！,已通知数据更改");
		}
		return count;
	}
	
	@Override
	public String getType(Uri uri) {
		this.match = uriMatcher.match(uri);
		switch (match) {
		case Contants.VISIT_ALL:
			return Contants.CONTENT_TYPE;
		case Contants.VISIT_ONE:
			return Contants.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("错误的URI地址：" + uri.toString());
		}
	}
	
	/**
	 * @Title: getTableName
	 * @Description: 获取uri当中path的表名部分
	 * @param uri uri路径
	 * @return
	 */
	private String getTableName(String uri){
		String table_name = "";
		if(getCharacterCount(uri, "/") >=4){
			table_name = uri.substring(getCharacterPosition(uri, 3) + 1, getCharacterPosition(uri, 4));
		}else{
			table_name = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		}
		return table_name;
	}
	
	/**
	 * @Title: getCharacterPosition
	 * @Description: 获得URI当中特定符号出现的位置
	 * @param string  uri路径
	 * @param position 第几次
	 * @return
	 */
	private int getCharacterPosition(String string,int position){
	    //这里是获取"/"符号的位置
	    Matcher slashMatcher = Pattern.compile("/").matcher(string);
	    int mIdx = 0;
	    while(slashMatcher.find()) {
	       mIdx++;
	       //当"/"符号第position次出现的位置
	       if(mIdx == position){
	          break;
	       }
	    }
	    return slashMatcher.start();
	}
	
	/**
	 * @Title: getCharacterCount
	 * @Description: 获得URI中特定符号出现的次数
	 * @param string  uri路径
	 * @param sym  出现的符号
	 * @return
	 */
	private int getCharacterCount(String string,String sym){
		 //这里是获取"/"符号的位置
	    Matcher slashMatcher = Pattern.compile("/").matcher(string);
	    int mIdx = 0;
	    while(slashMatcher.find()) {
	       mIdx++;
	    }
	    return mIdx;
	}
	
	/**
	 * @Title: notifyDataChanged
	 * @Description: 通知数据改变
	 * @param uri
	 */
	private void notifyDataChanged(Uri uri){
		getContext().getContentResolver().notifyChange(uri, null);
	}
}
