package com.meishijie.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meishijie.entity.NewsContent;
import com.meishijie.main.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SearchContentListViewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	//查询数据对象的集合
	private List<NewsContent> contentList;
	private ImageLoader imageLoader;
	public SearchContentListViewAdapter(Context context,List<NewsContent> contentList) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.imageLoader = ImageLoader.getInstance();
		setData(contentList);
	}

	public void setData(List<NewsContent> contentList){
		this.contentList = contentList;
	}
	@Override
	public int getCount() {
		if(contentList != null){
			return contentList.size();
		}else{
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return contentList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.search_content_item,null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.pic);
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.kouwei = (TextView) convertView.findViewById(R.id.tv_kouwei_content);
			viewHolder.gongyi = (TextView) convertView.findViewById(R.id.tv_gongyi_content);
			viewHolder.nandu = (TextView) convertView.findViewById(R.id.tv_nandu_content);
			viewHolder.maketime = (TextView) convertView.findViewById(R.id.tv_shijian_content);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		NewsContent conn = contentList.get(position);
		
		imageLoader.displayImage(conn.getNewsphoto(), viewHolder.image);
		viewHolder.title.setText(conn.getTitle());
		viewHolder.kouwei.setText(conn.getKouwei());
		viewHolder.gongyi.setText(conn.getGongyi());
		viewHolder.nandu.setText(conn.getMake_diff());
		viewHolder.maketime.setText(conn.getMake_time());
		
		return convertView;
	}

	/**
	 * 优化图片布局的加载
	 * @author 羊婕
	 *
	 */
	class ViewHolder{
		public TextView title;
		public ImageView image;
		public TextView kouwei;
		public TextView gongyi;
		public TextView nandu;
		public TextView maketime;
	}
}
