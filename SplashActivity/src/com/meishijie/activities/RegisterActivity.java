package com.meishijie.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.meishijie.main.R;

public class RegisterActivity extends SherlockActivity implements OnClickListener{
	
	private ImageView reg_actionbar_left_pic;
	private ImageView reg_actionbar_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		
		this.initActionBar();
	}
	
	private void initActionBar(){
		ActionBar actionBar = getSupportActionBar();
		
		actionBar.setCustomView(R.layout.register_actionbar);
		
		actionBar.setDisplayShowCustomEnabled(true);
		
		actionBar.setDisplayShowHomeEnabled(false);
		
		this.reg_actionbar_left_pic = (ImageView) findViewById(R.id.reg_actionbar_left_pic);
		this.reg_actionbar_right = (ImageView) findViewById(R.id.reg_actionbar_right);
		
		this.reg_actionbar_left_pic.setOnClickListener(this);
		this.reg_actionbar_right.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.reg_actionbar_left_pic:
			this.finish();
			break;
		case R.id.reg_actionbar_right:
			this.finish();
			break;
		default:
			break;
		}
	}
	
}
