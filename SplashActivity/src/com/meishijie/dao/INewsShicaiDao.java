package com.meishijie.dao;

import java.util.List;

import com.meishijie.entity.NewsShiCai;

public interface INewsShicaiDao {
	public List<NewsShiCai> getAllNewsShiCai();
	public NewsShiCai getOneNewsShiCai(int id);
	public boolean insert(NewsShiCai shicai);
}
