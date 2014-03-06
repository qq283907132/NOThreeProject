package com.meishijie.activities;

import java.util.ArrayList;
import java.util.List;

import com.meishijie.adapter.FoodAdapter;
import com.meishijie.dao.INewsContentDao;
import com.meishijie.dao.impl.NewsContentDaoImpl;
import com.meishijie.entity.NewsClass;
import com.meishijie.entity.NewsContent;
import com.meishijie.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

public class NewsContentActivity extends Activity implements OnScrollListener {
	private ListView listView;
	private INewsContentDao contentDao;
	private List<NewsClass> classList;
	private String bclassname;
	private FoodAdapter adapter;
	// 放置记录的总条数
	private List<NewsContent> total = null;
	// 是否进行分页操作
	private int number = 10;//每次获取多少条数据
	//是否加载完成
    private boolean loadfinish = true;
    
	private View header;
	private View footer;
	private TextView title;
	private TextView desc;
	private List<NewsContent> contentList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fooder_menu);
		
		total = new ArrayList<NewsContent>();
		
		Intent intent = getIntent();
		bclassname = intent.getStringExtra("bclassname");
		classList = (List<NewsClass>) intent.getSerializableExtra("classList");
		
		title = (TextView) findViewById(R.id.title);
		title.setText(bclassname);
		desc = (TextView) findViewById(R.id.desc);
		
		listView = (ListView) findViewById(R.id.fooder_menu_list);
		header = LayoutInflater.from(this).inflate(R.layout.list_header,
				listView, false);
		listView.addHeaderView(header);
		footer = LayoutInflater.from(this).inflate(R.layout.list_more,
				listView, false);
		listView.addFooterView(footer);
		
		adapter = new FoodAdapter(this);
		
		contentDao = new NewsContentDaoImpl(this);
		initView(bclassname, classList);

		listView.removeFooterView(footer);
		listView.setOnScrollListener(this);
	}

	private void initView(String bclassname, List<NewsClass> classList) {
		if ("中华菜系".equals(bclassname)) {
			setListViewData(bclassname,classList);
		} else if ("外国菜谱".equals(bclassname)) {
			setListViewData(bclassname,classList);
		} else if ("菜谱大全".equals(bclassname)) {
			setListViewData(bclassname,classList);
		} else if ("营养健康".equals(bclassname)) {
			setListViewData(bclassname,classList);
		} else if ("烘焙".equals(bclassname)) {
			setListViewData(bclassname,classList);
		}else if ("厨房百科".equals(bclassname)) {
			setListViewData(bclassname,classList);
		} else if ("人群膳食".equals(bclassname)) {
			setListViewData(bclassname,classList);
		}else if ("功能性调理".equals(bclassname)) {
			setListViewData(bclassname,classList);
		} else if ("疾病调理".equals(bclassname)) {
			setListViewData(bclassname,classList);
		} else if ("脏腑调理".equals(bclassname)) {
			setListViewData(bclassname,classList);
		}
	}
	
	public void setListViewData(String bclassname, List<NewsClass> classList){
		contentList = contentDao
				.getPartNewsContentBySuperName(bclassname,"0,10");
		total.addAll(contentList);
		adapter.setData(total);
		listView.setAdapter(adapter);
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			total.addAll((List<NewsContent>)msg.obj);
			adapter.notifyDataSetChanged();//告诉ListView数据已经发生改变，要求ListView更新界面显示
			if(listView.getFooterViewsCount() > 0) listView.removeFooterView(footer);
			loadfinish = true;
		}    	
    };

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
		int count = contentDao.getSuperSum(bclassname);
		Log.i("newscontent" , "count:"+count);
		int maxpage = count%number == 0 ? count/number : count/number+1;
		Log.i("newscontent" , "maxpage:"+maxpage);
		
		final int loadtotal = totalItemCount;
		int lastItemid = listView.getLastVisiblePosition();//获取当前屏幕最后Item的ID
		if((lastItemid+1) == totalItemCount){//达到数据的最后一条记录
			if(totalItemCount > 0){
				//当前页
				int currentpage = totalItemCount%number == 0 ? totalItemCount/number : totalItemCount/number+1;
				int nextpage = currentpage + 1;//下一页
				if(nextpage <= maxpage && loadfinish){
					loadfinish = false;
					listView.addFooterView(footer);
					
					new Thread(new Runnable() {						
						public void run() {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							List<NewsContent> result = contentDao.getPartNewsContentBySuperName(bclassname,loadtotal+","+number);
							handler.sendMessage(handler.obtainMessage(100, result));
						}
					}).start();
				}		
			}
					
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	
	}
	
	

}
