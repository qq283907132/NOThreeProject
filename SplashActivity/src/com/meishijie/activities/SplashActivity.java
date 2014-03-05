package com.meishijie.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

import com.meishijie.dao.INewsContentDao;
import com.meishijie.dao.impl.NewsContentDaoImpl;
import com.meishijie.entity.NewsContent;
import com.meishijie.main.R;

/**
 * @Title: SplashActivity
 * @Description: 欢迎界面
 * @Company: null
 * @author  DHA
 * @date    Feb 27, 2014
 */
public class SplashActivity extends Activity{

	private static final String TAG = "SplashActivity"; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		JPushInterface.init(getApplicationContext());
		
		this.setStyleBasic();
		
		ShareSDK.initSDK(this);
		
		//初始化数据
		this.initData();
		
		startActivity(new Intent(this,SettingsActivity.class));
	}
	
	
	private void setStyleBasic(){
		BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(getApplicationContext());
		builder.statusBarDrawable = R.drawable.icon;
		builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
		builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）  
		JPushInterface.setPushNotificationBuilder(1, builder);
	}
	
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
