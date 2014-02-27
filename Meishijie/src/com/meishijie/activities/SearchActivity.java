package com.meishijie.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.meishijie.main.R;

public class SearchActivity extends FragmentActivity {

	private TextView net_search;
	private ImageView back;
	private TextView local_search;
	private ImageView say;
	private EditText search_text;
	private Button search;
	private ImageView clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_main);

		// 引用控件
		findByIds();
		// 控件事件监听
		setViewOnClickListener();
	}

	// 通过Id查找控件
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
		back.setOnClickListener(viewOnClickListener);
	}

	// 每个控件的监听事件动作
	private class ViewOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			//返回图标
			case R.id.back:

				break;

			//网络搜索
			case R.id.net_search:

				break;
			default:
				break;
			}
		}

	}
}
