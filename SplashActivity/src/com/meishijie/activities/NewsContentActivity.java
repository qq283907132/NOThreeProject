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
	private boolean is_divpage;
	// 页码
	private static int pageNo = 0;
	// 每一页要显示的数据
	private int number = 10;
	private MyListTask task;
	private View header;
	private View footer;
	private TextView title;
	private TextView desc;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fooder_menu);
		
		Intent intent = getIntent();
		bclassname = intent.getStringExtra("bclassname");
		classList = (List<NewsClass>) intent.getSerializableExtra("classList");
		
		
		total = new ArrayList<NewsContent>();
		pageNo = 1;
		task = new MyListTask();
		
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
			task.execute();
		} else if ("外国菜谱".equals(bclassname)) {
			task.execute();
		} else if ("菜谱大全".equals(bclassname)) {
			task.execute();
		} else if ("营养健康".equals(bclassname)) {
			task.execute();
		} else if ("烘焙".equals(bclassname)) {
			task.execute();
		}else if ("厨房百科".equals(bclassname)) {
			task.execute();
		} else if ("人群膳食".equals(bclassname)) {
			task.execute();
		}else if ("功能性调理".equals(bclassname)) {
			task.execute();
		} else if ("疾病调理".equals(bclassname)) {
			task.execute();
		} else if ("脏腑调理".equals(bclassname)) {
			task.execute();
		}
	}

	public class MyListTask extends AsyncTask<Void, Void, List<NewsContent>> {

		private int maxPage;
		private List<NewsContent> contentList;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected List<NewsContent> doInBackground(Void... params) {
			int count = contentDao.getSuperSum(bclassname);
			Log.i("newscontent" , "count:"+count);
			int maxPage = count%number == 0 ? count/number : count/number+1;
			Log.i("newscontent" , "maxPage:"+maxPage);
			if(count < number){
				number = count;
			}
			if(pageNo<=maxPage){
				if(pageNo==maxPage && count%number!=0){
					number = count%number;
				}
				contentList = contentDao
						.getPartNewsContentBySuperName(bclassname, (pageNo - 1)
								* number + "," + number);
			}
			return contentList;
		}

		@Override
		protected void onPostExecute(List<NewsContent> result) {
			super.onPostExecute(result);
			total.addAll(result);
			adapter.setData(total);
			if (pageNo == 1) {
				listView.setAdapter(adapter);
			}
			adapter.notifyDataSetChanged();
			pageNo++;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_BACK) {
			task.cancel(true);
			finish();
		}
		return true;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		is_divpage = (firstVisibleItem + visibleItemCount == totalItemCount);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (is_divpage && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			listView.addFooterView(footer);
			new MyListTask().execute();
		}
	}

}
