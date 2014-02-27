package com.meishijie.main;

import com.meishijie.activities.SearchActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void jump(View btn){
		Intent intent = new Intent(MainActivity.this, SearchActivity.class);
		startActivity(intent);
	}

}
