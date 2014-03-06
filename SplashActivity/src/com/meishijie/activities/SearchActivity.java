package com.meishijie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.meishijie.main.MainActivity;
import com.meishijie.main.R;
import com.meishijie.search.LocalSearchDateFragment;
import com.meishijie.search.LocalSearchFragment;
import com.meishijie.search.LocalSearchKouweiDateFragment;
import com.meishijie.search.NetSearchDateFragment;
import com.meishijie.search.NetSearchFragment;

/**
 * 搜索的主要的Activity
 * 
 * @author 羊婕
 * 
 */
public class SearchActivity extends FragmentActivity {

	private TextView net_search;
	
	
	private ImageView back;
	private TextView local_search;
	private ImageView say;
	private EditText search_text;
	private Button search;
	private ImageView clear;
	private FragmentManager fm;
	private FragmentTransaction trans;
	private NetSearchFragment nsf;
	private View local_nd;
	private View local_gy;
	private View local_kw;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_main);

		// 引用控件
		findByIds();
		// 控件事件监听
		setViewOnClickListener();

		nsf = new NetSearchFragment();
		fragment_replace(nsf);
		
	}

	/**
	 * 通过Id查找控件
	 */
	private void findByIds() {
		
		// 返回图标
		back = (ImageView) findViewById(R.id.back);
		// 网络搜索
		net_search = (TextView) findViewById(R.id.net_search);
		// 本地搜索
		local_search = (TextView) findViewById(R.id.local_search);
		// 语音搜索
		say = (ImageView) findViewById(R.id.say);
		// 搜索输入框
		search_text = (EditText) findViewById(R.id.search_text);
		// 搜索按钮
		search = (Button) findViewById(R.id.search);
		// 清除图标
		clear = (ImageView) findViewById(R.id.clear);

	}

	// 给每一个控件设置监听
	private void setViewOnClickListener() {
		ViewOnClickListener viewOnClickListener = new ViewOnClickListener();
		// 返回
		back.setOnClickListener(viewOnClickListener);
		// 网络搜索
		net_search.setOnClickListener(viewOnClickListener);
		// 本地搜索
		local_search.setOnClickListener(viewOnClickListener);
		search.setOnClickListener(viewOnClickListener);
		
	}

	// 每个控件的监听事件动作
	private class ViewOnClickListener implements OnClickListener {

		Intent intent = new Intent();
		private String keyName;
		
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			// 返回图标
			case R.id.back:
				finish();
				break;

			// 网络搜索
			case R.id.net_search:
				net_search.setBackgroundResource(R.drawable.search_topbar_btn);
				local_search.setBackgroundResource(R.drawable.search_topbar);
				fragment_replace(nsf);
				break;
			// 本地搜索
			case R.id.local_search:
				net_search.setBackgroundResource(R.drawable.search_topbar);
				local_search.setBackgroundResource(R.drawable.search_topbar_btn);
				LocalSearchFragment lsf = new LocalSearchFragment();
				fragment_replace(lsf);
				break;
			case R.id.search:
				keyName = search_text.getText().toString();
				intent.setClass(SearchActivity.this, SearchContentActivity.class);
				intent.putExtra("title_name", keyName);
				intent.putExtra("MSG", "vague");
				startActivity(intent);
				break;
			default: 
				break;
			}
		}

	}

	public void fragment_replace(Fragment fragment) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.search_fragments_replace, fragment);
		trans.addToBackStack(null);
		trans.commit();

	}
}
