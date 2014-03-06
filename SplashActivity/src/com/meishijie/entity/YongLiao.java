package com.meishijie.entity;

import android.graphics.drawable.Drawable;

public class YongLiao {
	private String icon;
	private String name;
	private String num;
	private Drawable d ;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Drawable getD() {
		return d;
	}
	public void setD(Drawable d) {
		this.d = d;
	}
	@Override
	public String toString() {
		return "YongLiao [icon=" + icon + ", name=" + name + ", num=" + num
				+ ", d=" + d + "]";
	}
	
}
