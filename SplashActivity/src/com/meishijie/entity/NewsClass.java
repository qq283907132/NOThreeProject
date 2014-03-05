package com.meishijie.entity;

import java.io.Serializable;

public class NewsClass implements Serializable{
	private int id ;
	private String bclassname;
	private String name;
	private int type;
	private int newsnum;
	private int onclick;
	private String top_img;
	private String top_word;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBclassname() {
		return bclassname;
	}
	public void setBclassname(String bclassname) {
		this.bclassname = bclassname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNewsnum() {
		return newsnum;
	}
	public void setNewsnum(int newsnum) {
		this.newsnum = newsnum;
	}
	public int getOnclick() {
		return onclick;
	}
	public void setOnclick(int onclick) {
		this.onclick = onclick;
	}
	public String getTop_img() {
		return top_img;
	}
	public void setTop_img(String top_img) {
		this.top_img = top_img;
	}
	public String getTop_word() {
		return top_word;
	}
	public void setTop_word(String top_word) {
		this.top_word = top_word;
	}
}
