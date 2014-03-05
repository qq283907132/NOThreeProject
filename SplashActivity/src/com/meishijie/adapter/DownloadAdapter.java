package com.meishijie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.meishijie.main.R;

public class DownloadAdapter extends BaseAdapter{
	
	private String[] data;
	private LayoutInflater inflater ;
	
	public DownloadAdapter(Context context,String[] data) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return this.data.length;
	}

	@Override
	public Object getItem(int position) {
		return this.data[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder view ;
		if(convertView == null){
			view = new ViewHolder();
			convertView = inflater.inflate(R.layout.download_item, null);
			view.title = (TextView) convertView.findViewById(R.id.title);
			view.size = (TextView) convertView.findViewById(R.id.size);
			view.progerssbar = (ProgressBar) convertView.findViewById(R.id.progerssbar);
			view.import_text = (TextView) convertView.findViewById(R.id.import_text);
			convertView.setTag(view);
		}else {
			view = (ViewHolder) convertView.getTag();
		}
		
		view.title.setText(this.data[position]);
		view.size.setText("19M");
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView title;
		TextView size;
		ProgressBar progerssbar;
		TextView import_text;
	}

}
