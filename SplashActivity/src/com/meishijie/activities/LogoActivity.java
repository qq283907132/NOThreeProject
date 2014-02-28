package com.meishijie.activities;

import com.meishijie.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class LogoActivity extends Activity {
	private ImageView topImage;
	private ImageView bottomImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		initView();
		
	}

	private void initView() {
		setContentView(R.layout.logo);
		
		topImage = (ImageView) findViewById(R.id.img_top);
		bottomImage = (ImageView) findViewById(R.id.img_bottom);
		//topImage.setAnimation();
		
		
		new Handler().postDelayed(runnable, 5000);
	}
	
	private Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			Intent intent = new Intent(LogoActivity.this,IndexActivity.class);
			startActivity(intent);
			finish();
			//SplashActivity.this.overridePendingTransition(enterAnim, exitAnim);
		}
	};
}
