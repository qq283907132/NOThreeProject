package com.meishijie.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

import com.meishijie.main.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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
			
		//初始化数据
		this.initData();
		
		JPushInterface.init(this);
		
		ShareSDK.initSDK(this);
		
		initImageLoader(getApplicationContext());
		
		startActivity(new Intent(this,IndexActivity.class));
	}
	
	 /**初始化图片加载类配置信息**/
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
            .threadPriority(Thread.NORM_PRIORITY - 2)//加载图片的线程数
            .denyCacheImageMultipleSizesInMemory() //解码图像的大尺寸将在内存中缓存先前解码图像的小尺寸。
            .discCacheFileNameGenerator(new Md5FileNameGenerator())//设置磁盘缓存文件名称
            .tasksProcessingOrder(QueueProcessingType.LIFO)//设置加载显示图片队列进程
            .writeDebugLogs() // Remove for release app
            .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
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
