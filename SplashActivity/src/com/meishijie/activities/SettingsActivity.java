package com.meishijie.activities;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.meishijie.main.R;

public class SettingsActivity extends SherlockActivity implements OnClickListener{
	
	private TableRow msj_account_login ;
	private TableRow binding_third_account;
	private ImageView local_network;
	private ImageView wifi_status;
	private TableRow push_info_everyday;
	private TableRow data_packet_download;
	private TableRow not_load_data_in_two_three;
	private TableRow clear_cache_all;
	private TableRow check_out_update;
	private TableRow advice_feed_back;
	private TableRow about_me_all_info;
	private TableRow soft_recommend_info;
	private TableRow invite_friends_download;
	
	private ImageView setting_actionbar_left_pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);
		this.initActionBar();
		
		this.initView();
		this.initClickListener();
	}
	
	private void initView(){
		this.msj_account_login = (TableRow) findViewById(R.id.msj_account_login);
		this.binding_third_account = (TableRow) findViewById(R.id.binding_third_account);
		this.local_network = (ImageView) findViewById(R.id.local_network);
		this.wifi_status = (ImageView) findViewById(R.id.wifi_status);
		this.push_info_everyday = (TableRow) findViewById(R.id.push_info_everyday);
		this.data_packet_download = (TableRow) findViewById(R.id.data_packet_download);
		this.not_load_data_in_two_three = (TableRow) findViewById(R.id.not_load_data_in_two_three);
		this.clear_cache_all = (TableRow) findViewById(R.id.clear_cache_all);
		this.check_out_update = (TableRow) findViewById(R.id.check_out_update);
		this.advice_feed_back = (TableRow) findViewById(R.id.advice_feed_back);
		this.about_me_all_info = (TableRow) findViewById(R.id.about_me_all_info);
		this.soft_recommend_info = (TableRow) findViewById(R.id.soft_recommend_info);
		this.invite_friends_download = (TableRow) findViewById(R.id.invite_friends_download);
	}
	
	private void initClickListener(){
		this.msj_account_login.setOnClickListener(this);
		this.binding_third_account.setOnClickListener(this);
		this.local_network.setOnClickListener(this);
		this.wifi_status.setOnClickListener(this);
		this.push_info_everyday.setOnClickListener(this);
		this.data_packet_download.setOnClickListener(this);
		this.not_load_data_in_two_three.setOnClickListener(this);
		this.clear_cache_all.setOnClickListener(this);
		this.check_out_update.setOnClickListener(this);
		this.advice_feed_back.setOnClickListener(this);
		this.about_me_all_info.setOnClickListener(this);
		this.soft_recommend_info.setOnClickListener(this);
		this.invite_friends_download.setOnClickListener(this);
	}
	
	private void initActionBar(){
		ActionBar actionBar = getSupportActionBar();
		
		actionBar.setCustomView(R.layout.setting_actionbar);
		
		actionBar.setDisplayShowCustomEnabled(true);
		
		actionBar.setDisplayShowHomeEnabled(false);
		
		this.setting_actionbar_left_pic = (ImageView) findViewById(R.id.setting_actionbar_left_pic);
		
		this.setting_actionbar_left_pic.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.msj_account_login:
			startActivity(new Intent(this,LoginActivity.class));
			break;
		case R.id.binding_third_account:
					
			break;
		case R.id.local_network:
			
			break;
		case R.id.wifi_status:
			
			break;
		case R.id.push_info_everyday:
			
			break;
		case R.id.data_packet_download:
			startActivity(new Intent(this,DownloadDataActivity.class));
			break;
		case R.id.not_load_data_in_two_three:
			
			break;
		case R.id.clear_cache_all:
			
			break;
		case R.id.check_out_update:
			
			break;
		case R.id.advice_feed_back:
			
			break;
		case R.id.about_me_all_info:
			
			break;
		case R.id.soft_recommend_info:
			
			break;
		case R.id.invite_friends_download:
			OnekeyShare oks = new OnekeyShare();
			// 分享时Notification的图标和文字
			oks.setNotification(R.drawable.icon, 
			getString(R.string.app_name));
			oks.setSilent(true);
			oks.show(this);
			break;
		case R.id.setting_actionbar_left_pic:
			break;
		default:
			break;
		}
	}
	
	
}
