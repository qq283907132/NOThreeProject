package com.meishijie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.meishijie.main.R;

public class LoginActivity extends SherlockActivity implements OnClickListener{
	
	private ImageView login_actionbar_left_pic;
	private ImageView login_actionbar_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login_msj_layout);
		
		this.initActionBar();
		
	}
	
	private void initActionBar(){
		ActionBar actionBar = getSupportActionBar();
		
		actionBar.setCustomView(R.layout.login_actionbar);
		
		actionBar.setDisplayShowCustomEnabled(true);
		
		actionBar.setDisplayShowHomeEnabled(false);
		
		this.login_actionbar_left_pic = (ImageView) findViewById(R.id.login_actionbar_left_pic);
		this.login_actionbar_right = (ImageView) findViewById(R.id.login_actionbar_right);
		
		this.login_actionbar_left_pic.setOnClickListener(this);
		this.login_actionbar_right.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id  = v.getId();
		switch (id) {
		case R.id.login_actionbar_left_pic:
			this.finish();
			break;
		case R.id.login_actionbar_right:
			startActivity(new Intent(this,RegisterActivity.class));
			break;
		default:
			break;
		}
	}
}
