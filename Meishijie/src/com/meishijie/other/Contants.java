package com.meishijie.other;

import android.net.Uri;

/**
 * @Title: Contants
 * @Description: 常量类
 * @Company: null
 * @author  DHA
 * @date    Feb 27, 2014
 */
public class Contants {
	
	//数据库名称
	public static final String DB_NAME = "31meishidb.db";
	//版本号
	public static final int DB_VERSION = 1;
	//表名
	public static final String TABLE_BUY_SHICAI = "buy_shicai";
	public static final String TABLE_NEWS_QINGDANG = "news_qingdang";
	public static final String TABLE_NEWS_SHICAI = "news_shicai";
	public static final String TABLE_NEWS_CLASS = "newsclass";
	public static final String TABLE_NEWS_CONTENT = "newscontent";
	public static final String TABLE_NEWS_FAV = "newsfav";
	public static final String TABLE_SHOPPING_CAIPU = "shopping_caipu";
	
	//访问的主机名(authority)
	public static final String AUTHORITY = "com.meishijie.data.provider";
	
	//访问类型
	public static final String CONTENT_TYPE = "vnd.android.cursor.type/vnd.meishijie.type";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.meishije.item";
	
	//匹配的URI地址
	public static final Uri URI_BUY_SHICAI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_BUY_SHICAI);
	public static final Uri URI_NEWS_QINGDANG = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NEWS_QINGDANG);
	public static final Uri URI_NEWS_SHICAI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NEWS_SHICAI);
	public static final Uri URI_NEWS_CLASS = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NEWS_CLASS);
	public static final Uri URI_NEWS_CONTENT = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NEWS_CONTENT);
	public static final Uri URI_NEWS_NEWS_FAV = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NEWS_FAV);
	public static final Uri URI_SHOPPING_CAIPU = Uri.parse("content://" + AUTHORITY + "/" + TABLE_SHOPPING_CAIPU);
	
	//是否匹配
	public static final int VISIT_ALL = 1;
	public static final int VISIT_ONE = 0 ;
}
