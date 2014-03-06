package com.meishijie.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meishijie.entity.YongLiao;
import com.meishijie.main.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class YongLiaoAdapter extends BaseAdapter {
	private static final String TAG = "YongLiaoAdapter";
	private LayoutInflater infalte;
	private ImageLoader imageLoader;
	private List<YongLiao> yongliaolist = null;
	public YongLiaoAdapter(Context context,List<YongLiao> yongliaolist,ImageLoader imageLoader) {
		this.infalte = LayoutInflater.from(context);
		this.yongliaolist = yongliaolist;
		this.imageLoader = imageLoader;
	}
	@Override
	public int getCount() {
		return yongliaolist.size();
	}

	@Override
	public Object getItem(int position) {
		return yongliaolist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = infalte.inflate(R.layout.item_yongliao, null);
			holder.picname = (ImageView) convertView.findViewById(R.id.iv_yongliao_picname);
			holder.icon = (ImageView) convertView.findViewById(R.id.iv_yongliao_icon);
			holder.name = (TextView) convertView.findViewById(R.id.tv_yongliao_name);
			holder.num = (TextView) convertView.findViewById(R.id.tv_yongliao_num);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		YongLiao yong = yongliaolist.get(position);
		Log.i(TAG, "" + yong.getName() + "**************" + yong.getNum() + "*********" );
		holder.name.setText(yong.getName());
		holder.num.setText(yong.getNum());
		holder.icon.setImageDrawable(yong.getD());
		imageLoader.displayImage(yong.getIcon(), holder.picname);
		return convertView;
	}
	public class ViewHolder {
		ImageView picname ;
		TextView name ;
		TextView num;
		ImageView icon ;
	}
}
