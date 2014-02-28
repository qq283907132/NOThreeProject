package com.meishijie.dao;

import java.util.List;

import com.meishijie.entity.NewsContent;

/**
 * @Title: INewsContentDao
 * @Description: newscontent表的操作dao接口
 * @Company: null
 * @author  DHA
 * @date    Feb 28, 2014
 */
public interface INewsContentDao {
	public List<NewsContent> getAllNewsContent();
	public NewsContent getOneNewsContent(int id);
	public boolean insertNewsContent(NewsContent newsContent);
}
