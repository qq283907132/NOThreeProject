package com.meishijie.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meishijie.main.R;

public class SearchGridViewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	//图片对象的集合
	private List<Picture> pictures;

	/**
	 * 
	 * @param context 上下文
	 * @param titles 传进来的图标的名称的集合
	 * @param images 传进来的食材图标的集合
	 */
	public SearchGridViewAdapter(Context context,int[] titles,int[] images) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.pictures = new ArrayList<SearchGridViewAdapter.Picture>();
		for(int i=0;i<images.length;i++){
			Picture picture = new Picture(titles[i], images[i]);
			pictures.add(picture);
		}
	}

	@Override
	public int getCount() {
		if(pictures != null){
			return pictures.size();
		}else{
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 加载布局
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.search_gridview,null);
			viewHolder = new ViewHolder();
			viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_shicai);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_search_title);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.image.setImageResource(pictures.get(position).getImageId());
		viewHolder.title.setText(pictures.get(position).getTitleId());

		return convertView;
	}

	/**
	 * 优化图片布局的加载
	 * @author Administrator
	 *
	 */
	class ViewHolder{
		public TextView title;
		public ImageView image;
	}
	
	/**
	 * GridView中图片的对象
	 * @author Administrator
	 *
	 */
	class Picture {
		//图标在R文件中的ID
		private int titleId;
		//图片在R文件中的ID
		private int imageId;

		public Picture() {
			super();
		}

		public Picture(int titleId, int imageId) {
			super();
			this.titleId = titleId;
			this.imageId = imageId;
		}

		public int getTitleId() {
			return titleId;
		}

		public void setTitleId(int titleId) {
			this.titleId = titleId;
		}

		public int getImageId() {
			return imageId;
		}

		public void setImageId(int imageId) {
			this.imageId = imageId;
		}

	}

}
