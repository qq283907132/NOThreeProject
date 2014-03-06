package com.meishijie.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.meishijie.adapter.DownloadAdapter;
import com.meishijie.main.R;

public class DownloadDataActivity extends SherlockActivity implements OnClickListener{
	
	private ListView data_download_list_view;
	private String[] data = new String[]{"热菜_图片包","凉菜_图片包","粥品_图片包","点心、主食、靓汤_图片包",
			"素菜、美容、瘦身_图片包","卤味、微波炉、海鲜_图片包","火锅、酱料蘸料、饮品、干…","孕妇、产妇、宝宝、老人_图…",
			"早餐、中餐、晚餐、下午茶…","川菜、粤菜_图片包","鲁菜、湘菜、东北菜、徽菜…","闽菜、京菜、沪菜、豫菜、…",
			"日本料理、韩国料理、意大…","法国菜、西餐面点、美国家…","烘焙_图片包","人群膳食_图片包",
			"功能性调理_图片包","疾病调理_图片包","脏腑调理_图片包","厨房百科_图片包"};
	
	private ImageView down_actionbar_left_pic;
	private ImageView down_actionbar_right;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_layout);
		
		this.initActionBar();
		
		this.data_download_list_view = (ListView) findViewById(R.id.data_download_list_view);
		
		this.data_download_list_view.setAdapter(new DownloadAdapter(this, data));
	}
	
	private void initActionBar(){
		ActionBar actionBar = getSupportActionBar();
		
		actionBar.setCustomView(R.layout.download_actionbar);
		
		actionBar.setDisplayShowCustomEnabled(true);
		
		actionBar.setDisplayShowHomeEnabled(false);
		
		this.down_actionbar_left_pic = (ImageView) findViewById(R.id.down_actionbar_left_pic);
		this.down_actionbar_right = (ImageView) findViewById(R.id.down_actionbar_right);
		
		this.down_actionbar_left_pic.setOnClickListener(this);
		this.down_actionbar_right.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.down_actionbar_left_pic:
			finish();
			break;

		default:
			break;
		}
	}
}
