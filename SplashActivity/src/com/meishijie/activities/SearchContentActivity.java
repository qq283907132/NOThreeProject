package com.meishijie.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.meishijie.adapter.SearchContentListViewAdapter;
import com.meishijie.dao.INewsContentDao;
import com.meishijie.dao.impl.NewsContentDaoImpl;
import com.meishijie.entity.NewsContent;
import com.meishijie.main.R;
import com.meishijie.search.NetSearchFragment;

public class SearchContentActivity extends Activity implements OnScrollListener {

	private ImageView back;
	private ImageView say;
	private EditText search_text;
	private ImageView clear;
	private FragmentManager fm;
	private FragmentTransaction trans;
	private NetSearchFragment nsf;
	private String search_title;

	private List<NewsContent> contentData = new ArrayList<NewsContent>();
	private int currentPage = 1;
	private int pageCount = 10;
	private int totalPage;
	// 放置记录的总条数
	private List<NewsContent> total = null;
	// 是否加载完成
	private boolean loadfinish = true;

	private String msg;
	private INewsContentDao contentDao;

	private SearchContentListViewAdapter adapter;
	private ImageView search;
	// private String title_name;
	private ListView lv_content;
	private Drawable mIconSearchDefault; // 搜索文本框默认图标
	private Drawable mIconSearchClear; // 搜索文本框清除文本内容图标

	private View footer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_listview);
		final Resources res = getResources();
		// ListView
		lv_content = (ListView) findViewById(R.id.lv_content);
		// ListView 底部的布局
		footer = LayoutInflater.from(this).inflate(R.layout.pull_to_refresh,
				lv_content, false);

		lv_content.addFooterView(footer);

		// 查找控件
		findByIds();
		// 调用传过来的数据
		getData();
		// 设置控件
		setViewOnClickListener();
		search_text.addTextChangedListener(tbxSearch_TextChanged);
		search_text.setOnTouchListener(txtSearch_OnTouch);

		// 设置默认图标
		search_text.setCompoundDrawablesWithIntrinsicBounds(null, null,
				mIconSearchDefault, null);

		contentDao = new NewsContentDaoImpl(SearchContentActivity.this);
		// 判断是从哪个类传过来的数据
		if (msg.equals("nandu_content")) {
			contentData = contentDao.getAllNewsByString(search_title,
					(currentPage - 1) * pageCount + "," + pageCount);
		} else if (msg.equals("gongyi_content")) {
			contentData = contentDao.getAllNewsByGongYi(search_title,
					(currentPage - 1) * pageCount + "," + pageCount);
		} else if (msg.equals("kouwei_content")) {
			contentData = contentDao.getAllNewsByKouwei(search_title,
					(currentPage - 1) * pageCount + "," + pageCount);
		}else if(msg.equals("vague")){
			contentData = contentDao.getAllNewsByTitle(search_title,
					(currentPage - 1) * pageCount + "," + pageCount);
		}

		for (NewsContent con : contentData) {
			Log.i("geek", con.getTitle()
					+ "************************************");
		}

		// 总的数据的集合,初始化
		total = new ArrayList<NewsContent>();
		total.addAll(contentData);

		adapter = new SearchContentListViewAdapter(SearchContentActivity.this,
				contentData);
		lv_content.setAdapter(adapter);
		// 初始化搜索的字段
		search_text.setText(search_title);

		lv_content.removeFooterView(footer);
		lv_content.setOnScrollListener(this);

	}

	/**
	 * 通过Id查找控件
	 */
	private void findByIds() {

		// 返回图标
		back = (ImageView) findViewById(R.id.back);

		// 语音搜索
		say = (ImageView) findViewById(R.id.say);
		// 搜索输入框
		search_text = (EditText) findViewById(R.id.search_text);
		search = (ImageView) findViewById(R.id.search);
		// 清除图标
		clear = (ImageView) findViewById(R.id.clear);
		

	}

	// 给每一个控件设置监听
	private void setViewOnClickListener() {
		ViewOnClickListener viewOnClickListener = new ViewOnClickListener();
		// 返回
		back.setOnClickListener(viewOnClickListener);
		search.setOnClickListener(viewOnClickListener);
	}

	// 每个控件的监听事件动作
	private class ViewOnClickListener implements OnClickListener {

		Intent intent = new Intent();

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			// 返回图标
			case R.id.back:
				finish();
				break;

			case R.id.search:
				search_title = search_text.getText().toString();
				Log.i("geek", search_title + "********************");
				contentData = contentDao.getAllNewsByTitle(search_title,
						(currentPage - 1) * pageCount + "," + pageCount);
				for (NewsContent con : contentData) {
					Log.i("geek", con.getTitle()
							+ "+++++++++++++++++++++++++++++");
				}
				adapter.setData(contentData);
				adapter.notifyDataSetChanged();

				break;
			default:
				break;
			}
		}

	}

	/**
	 * 动态搜索
	 */
	private TextWatcher tbxSearch_TextChanged = new TextWatcher() {

		// 缓存上一次文本框内是否为空
		private boolean isnull = true;

		@Override
		public void afterTextChanged(Editable s) {
			if (TextUtils.isEmpty(s)) {
				if (!isnull) {
					// search_text.setCompoundDrawablesWithIntrinsicBounds(null,
					// null, mIconSearchDefault, null);
					isnull = true;
				}
			} else {
				if (isnull) {
					// search_text.setCompoundDrawablesWithIntrinsicBounds(null,
					// null, mIconSearchClear, null);
					isnull = false;
				}
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		/**
		 * 随着文本框内容改变动态改变列表内容
		 */
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// 查询搜索

		}
	};

	/** 点击清除图标，清空文本框 */
	private OnTouchListener txtSearch_OnTouch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				int curX = (int) event.getX();
				// 判断触摸位置在右端，并且文本框内有内容
				if (curX > v.getWidth() - 88
						&& !TextUtils.isEmpty(search_text.getText())) {
					search_text.setText("");
					int cacheInputType = search_text.getInputType();// backuptheinputtype
					search_text.setInputType(InputType.TYPE_NULL);// disablesoftinput
					search_text.onTouchEvent(event);// callnativehandler
					search_text.setInputType(cacheInputType);// restoreinputtype
					return true;// consumetoucheven
				}
				break;
			}
			return false;
		}
	};

	// 获得传过来的数据
	private void getData() {
		Intent intentDate = getIntent();
		search_title = intentDate.getStringExtra("title_name");
		msg = intentDate.getStringExtra("MSG");
		Log.i("geek", search_title);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			total.addAll((List<NewsContent>) msg.obj);
			adapter.setData(total);
			adapter.notifyDataSetChanged();// 告诉ListView数据已经发生改变，要求ListView更新界面显示
			if (lv_content.getFooterViewsCount() > 0)
				lv_content.removeFooterView(footer);
			loadfinish = true;
		}
	};
	
	private int count;
	private ProgressBar pull_to_load_progress;

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// 判断是从哪个类传过来的数据
		if (msg.equals("nandu_content")) {
			count = contentDao.getCountByDataNd(search_title);
		} else if (msg.equals("gongyi_content")) {
			count = contentDao.getCountByDataGy(search_title);
		} else if (msg.equals("kouwei_content")) {
			count = contentDao.getCountByDataKw(search_title);
		}else if(msg.equals("vague")){
			count = contentDao.getCountByDataKey(search_title);
		}
		
		Log.i("newscontent", "count:" + count);
		int maxpage = count % pageCount == 0 ? count / pageCount : count
				/ pageCount + 1;
		Log.i("newscontent", "maxpage:" + maxpage);

		final int loadtotal = totalItemCount;
		int lastItemid = lv_content.getLastVisiblePosition();// 获取当前屏幕最后Item的ID
		if ((lastItemid + 1) == totalItemCount) {// 达到数据的最后一条记录
			if (totalItemCount > 0) {
				// 当前页
				int currentpage = totalItemCount % pageCount == 0 ? totalItemCount
						/ pageCount
						: totalItemCount / pageCount + 1;
				int nextpage = currentpage + 1;// 下一页
				if (nextpage <= maxpage && loadfinish) {
					loadfinish = false;
					lv_content.addFooterView(footer);
					
					new Thread(new Runnable() {
						private List<NewsContent> result;

						public void run() {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							//判断数据属于哪一个类型
							if (msg.equals("nandu_content")) {
								result = contentDao.getAllNewsByString(search_title,loadtotal
										+ "," + pageCount);
							} else if (msg.equals("gongyi_content")) {
								result = contentDao
										.getAllNewsByGongYi(search_title, loadtotal
												+ "," + pageCount);
							} else if (msg.equals("kouwei_content")) {
								result = contentDao
										.getAllNewsByKouwei(search_title, loadtotal
												+ "," + pageCount);
							}else if(msg.equals("vague")){
								result = contentDao
										.getAllNewsByTitle(search_title, loadtotal
												+ "," + pageCount);
							}
							
							handler.sendMessage(handler.obtainMessage(100,
									result));
						}
					}).start();
				}
			}

		}
	}
}
