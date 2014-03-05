package com.meishijie.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.meishijie.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

public class LogoActivity extends Activity {
	@SuppressWarnings("unused")
	private ImageView topImage;
	@SuppressWarnings("unused")
	private ImageView bottomImage;
	
	private static final String TAG = "SplashActivity"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.initData();
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
			overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			finish();
			
		}
	};
	
	
	/**
	 * @Title: initData
	 * @Description:初始化本地数据，将本地数据制拷贝到数据库路径下面
	 */
	public void initData(){
		try {
			//得到程序数据库路径下的数据文件
			File database_file = getDatabasePath("31meishidb.db");
			//得到程序数据库路径
			File file_dir = database_file.getParentFile();
			//如果路径不存在，则生成路径
			if(!file_dir.exists()){
				file_dir.mkdir();
				
				Log.i(TAG, "数据库路径：" + file_dir);
			}
			
			//如果数据库文件不存在，则进行拷贝
			if(!database_file.exists()){ 
				//读取输入流
				InputStream is = getAssets().open("31meishidb.db");
				//得到输出流
				FileOutputStream fos = new FileOutputStream(database_file);
				//准备写入文件
				byte[] buffer = new byte[1024 * 5];
				int byteCount = 0;
				while((byteCount = is.read(buffer)) > 0){
					fos.write(buffer, 0, byteCount);
				}
				//逆序关闭流
				fos.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
