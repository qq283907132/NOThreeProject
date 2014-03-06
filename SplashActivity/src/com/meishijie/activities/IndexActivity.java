package com.meishijie.activities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.meishijie.dao.INewsClassDao;
import com.meishijie.dao.INewsContentDao;
import com.meishijie.dao.impl.NewsClassDaoImpl;
import com.meishijie.dao.impl.NewsContentDaoImpl;
import com.meishijie.entity.NewsClass;
import com.meishijie.entity.NewsContent;
import com.meishijie.main.R;
import com.meishijie.other.MyScrollView;
import com.meishijie.other.PullToRefreshView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class IndexActivity extends Activity {
	
	private List<ImageView> images;
	private ViewPager viewPager;
	private List<View> dots;
	private TextView big_title;
	private TextView small_title;
	
	//
	private LinearLayout linearLayout;
	
	private PullToRefreshView pullToRefreshView;
	private MyScrollView msv;
	//首页头部滑动图片
	private List<NewsContent> newsContents;
	//首页食品大类别列表数据
	private List<NewsClass> data;
	//首页食品小类别列表数据
	private List<NewsClass> subClasses;
	
	private StringBuffer buff;
	private ProgressBar topBar;
	
	private int oldPosition = 0;//上一次圆点位置
	private int currentItem = 0; //当前页面
	private ImageView imageView;
	
	//底部控件
	private TextView singleInfo;
	private TextView collection;
	private TextView shopList;
	private TextView latestScan;
	private TextView setting;
	private ImageView search;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		
		initViewPager();
		initView();
		initBottomView();
		
	}
	private void initViewPager() {
		
		dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));
        dots.add(findViewById(R.id.dot_5));
        
        big_title = (TextView) findViewById(R.id.big_title);
        small_title = (TextView)findViewById(R.id.small_title);
        topBar = (ProgressBar) findViewById(R.id.pb);
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		
		INewsContentDao contentDao = new NewsContentDaoImpl(this);
		
		newsContents = contentDao.getPartNewsContent("0,6");
		
		images = new ArrayList<ImageView>();
		for(int i=0;i<newsContents.size();i++){	
			imageView = new ImageView(getApplicationContext());
			Log.i("jky",newsContents.get(i).getNewsphoto());
			imageView.setImageResource(R.drawable.content_nopic);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			images.add(imageView);
		}
		//new LoadImageTask().execute(newsContents.get(0).getNewsphoto());	
		big_title.setText(newsContents.get(0).getTitle());
		small_title.setText(newsContents.get(0).getSmalltext());
		
		
		viewPager.setAdapter(new MyPagerAdapter());
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				big_title.setText(newsContents.get(position).getTitle());
				small_title.setText(newsContents.get(position).getSmalltext());
				
				dots.get(position).setBackgroundResource(R.drawable.dot_focused);
				dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
				
				oldPosition = position;
				currentItem = position;
			}
			
			@Override
			public void onPageScrolled(int firstVisibleItem, float visibleItemCount, int totalItemCount) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int scrollState) {
				
			}
		});
	}
	
	
	private void initView() {
		//获取大类类别名称
		INewsClassDao classDao = new NewsClassDaoImpl(this);
		data = classDao.getSuperClassName();
		
		//添加布局
		linearLayout = (LinearLayout) findViewById(R.id.main_list);
		
		for(int i=0;i<data.size();i++){
			View view = getLayoutInflater().inflate(R.layout.food_types, null);
			ImageView typeImage = (ImageView) view.findViewById(R.id.pic);
			
			TextView title = (TextView) view.findViewById(R.id.title);
			TextView unit = (TextView) view.findViewById(R.id.unit);
			
			title.setText(data.get(i).getBclassname());
			
			//获取小类类别名称
			subClasses = classDao.getSubClassByBigName(data.get(i).getBclassname());
			Log.i("jky", "count"+subClasses.size());
			buff = new StringBuffer();
			for(int j=0;j<subClasses.size();j++){
				buff.append(subClasses.get(j).getName()).append(", ");
			}
			buff = buff.deleteCharAt(buff.length()-1);
			unit.setText(buff.toString());
				
			view.setOnClickListener(new MyClick(data.get(i).getBclassname(),subClasses));
			
			linearLayout.addView(view);
		}
		
		pullToRefreshView = (PullToRefreshView) findViewById(R.id.refreshView);
		msv = (MyScrollView)findViewById(R.id.msv);
		pullToRefreshView.sv = msv;	
	}
	
	

	private class MyClick implements OnClickListener{
		private List<NewsClass> classList;
		private String bclassname;

		public MyClick(String bclassname, List<NewsClass> subClasses) {
			this.bclassname = bclassname;
			this.classList = subClasses;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(IndexActivity.this, NewsContentActivity.class);
			intent.putExtra("bclassname", bclassname);
			intent.putExtra("classList", (Serializable)classList);
			startActivity(intent);
			overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		}
		
	}
	
	private class MyPagerAdapter extends PagerAdapter{
		
		@Override
		public int getCount() {
			return images.size();
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(images.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(images.get(position));
			return images.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}
	
	private void initBottomView() {
		singleInfo = (TextView) findViewById(R.id.single_info);
		collection = (TextView) findViewById(R.id.single_collection);
		shopList = (TextView) findViewById(R.id.shop_list);
		latestScan = (TextView) findViewById(R.id.latest_scan);
		setting = (TextView) findViewById(R.id.setting);
		search = (ImageView) findViewById(R.id.search);
		
		singleInfo.setOnClickListener(new MyOnClickListener());
		collection.setOnClickListener(new MyOnClickListener());
		shopList.setOnClickListener(new MyOnClickListener());
		latestScan.setOnClickListener(new MyOnClickListener());
		setting.setOnClickListener(new MyOnClickListener());
		search.setOnClickListener(new MyOnClickListener());
	}
	
	/**
	 * 底部textview控件的监听事件
	 * @author Administrator
	 *
	 */
	private class MyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.single_info:
				
				break;
			case R.id.single_collection:
				
				break;

			case R.id.latest_scan:
	
				break;

			case R.id.shop_list:
	
				break;

			case R.id.setting:
	
				break;

			case R.id.search:
	
				break;
			}
		}
	}
}
