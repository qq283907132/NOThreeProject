package com.meishijie.dao;

import java.util.List;

import com.meishijie.entity.NewsClass;

public interface INewsClassDao {
	public List<NewsClass> getAllNewsClass();
	public NewsClass getOneNewsClass(int id);
	public boolean insert(NewsClass newsClass);
	//获取大类的名字
	public List<NewsClass> getSuperClassName();
	//小类的名字
	public List<NewsClass> getSubClassByBigName(String superName);
	
	
}
