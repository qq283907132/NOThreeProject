package com.meishijie.activities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.meishijie.adapter.TiaoLiaoApapter;
import com.meishijie.adapter.YongLiaoAdapter;
import com.meishijie.dao.impl.NewsContentDaoImpl;
import com.meishijie.entity.NewsContent;
import com.meishijie.entity.YongLiao;
import com.meishijie.main.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ItemActivity extends Activity {

	private static final String TAG = "ItemActivity";
	private final int camera_requestCode = 0;
	private final int pick_requestCode = 1;
	private final int cancel = 2;
	private ImageView iv_newsphoto;
	private ImageView iv_pic;
	private ImageView iv_writerpic;
	private TextView tv_title;
	private TextView tv_writer;
	private TextView tv_makediff;
	private TextView tv_makepretime;
	private TextView tv_kouwei;
	private TextView tv_makeamount;
	private TextView tv_maketime;
	private TextView tv_heat;
	private TextView tv_lookbigmap;
	private TextView tv_gongyi;
	private ListView lv_prjq;
	public ImageLoader imageLoader;
	private ImageView iv_yongliao_picname;
	//private DisplayImageOptions options ;
	private DisplayImageOptions options ;
	private TextView tv_yongliao_name;
	private TextView tv_yongliao_num;
	private YongLiao yongliao = null;
	private String tiaoliao;
	//用料集合
	private List<YongLiao> yongliaolist = null;
	private ListView lv_yongliao;
	private NewsContent c;
	private GridView gv_tiaoliao;
	private ListView lv_make;
	
	/*private Handler handler = new Handler(){
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			//代表得到了调料数据
			case 0:
				Log.i("geek", tiaoliao);
				//解析调料数据
				String[] tiaoliaoitem = tiaoliao.split("、");
				
				List<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
				for (int i = 0; i < tiaoliaoitem.length; i++) {
					Log.d(TAG, "tiaoliaoitem[i]"+tiaoliaoitem[i]);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("id", tiaoliaoitem[i]);
					data.add(map);
				}
				adapter = new SimpleAdapter(ItemActivity.this, data, R.layout.item_tiaoliao, new String[]{"id"}, new int[]{R.id.tv_first});
				gv_tiaoliao.setAdapter(adapter);
				break;

			default:
				break;
			}
		};
	};*/
	
	
	@SuppressWarnings("deprecation")
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		this.imageLoader = ImageLoader.getInstance();
		
		  options = new DisplayImageOptions.Builder()
		  .showStubImage(R.drawable.content_nonet) //在ImageView加载过程中显示图片
		  .showImageForEmptyUri(R.drawable.ic_launcher) //image连接地址为空时
		  .showImageOnFail(R.drawable.ic_launcher) //image加载失败
		  .cacheInMemory(true) //加载图片时会在内存中加载缓存 
		  .cacheOnDisc(true)//加载图片时会在磁盘中加载缓存 
		  .displayer(new RoundedBitmapDisplayer(20))//设置用户加载图片task(这里是圆角图片显示)
		  .imageScaleType(ImageScaleType.EXACTLY_STRETCHED) .build();
		
		
		findViewById();
		NewsContentDaoImpl dao = new NewsContentDaoImpl(this);
		c = dao.getOneNewsContent(1582);
		// iv_pic.setim(Integer.valueOf(c.getNewsphoto()));
		this.imageLoader.displayImage(c.getNewsphoto(), iv_newsphoto, null, new AnimateFirstDisplayListener());
		tv_title.setText(c.getTitle());
		tv_writer.setText(c.getWriter());
		tv_gongyi.setText("做法：" + c.getGongyi());
		tv_makediff.setText("难度：" + c.getMake_diff());
		tv_makepretime.setText("准备：" + c.getMake_pretime());
		tv_kouwei.setText("口味：" + c.getKouwei());
		tv_makeamount.setText("人数：" + c.getMake_amount());
		tv_maketime.setText("烹饪：" + c.getMake_time());
		int heatnum = c.getHeat().indexOf(":");
		tv_heat.setText("热量：" + c.getHeat().substring(0, heatnum) + "大卡，属于"
				+ c.getHeat().substring(heatnum + 1) + "菜品");
		new MyThread().start();
		tiaoliao = c.getTiaoliao();
		//getTiaoliao();
		//解析调料数据
		String[] tiaoliaoitem = tiaoliao.split("、");
		
		List<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
		for (int i = 0; i < tiaoliaoitem.length; i++) {
			Log.d(TAG, "tiaoliaoitem[i]...."+tiaoliaoitem[i]);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", tiaoliaoitem[i]);
			data.add(map);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_tiaoliao, new String[]{"id"}, new int[]{R.id.tv_first});
		gv_tiaoliao.setAdapter(adapter);
		String tips = c.getTips();
		//String[] pengrenjiqiao = tips.split("word");
		String[] pengrenjiqiao =tips.split("@@##@@word@#@",2)[1].split("@@##@@word@#@");
		for (int i = 0; i < pengrenjiqiao.length; i++) {
			Log.d(TAG, "pengrenjiqiao:"+pengrenjiqiao[i].toString());
		}
		//Log.d(TAG, "pengrenjiqiao:"+pengrenjiqiao.toString());
		List<HashMap<String, String>> data1 = new ArrayList<HashMap<String,String>>();
		for (int j = 0; j < pengrenjiqiao.length; j++) {
			HashMap<String, String> map1 = new HashMap<String, String>();
			Log.d(TAG, "pengrenjiqiao11111:"+pengrenjiqiao[j].toString());
			/*if (j==0) {
				return;
			}else {*/
				if (j==pengrenjiqiao.length-1) {
					pengrenjiqiao[j] = pengrenjiqiao[j].substring(0, pengrenjiqiao[j].length()-6);
				}
				map1.put("id", pengrenjiqiao[j]);
				data1.add(map1);
			/*}*/
			
		}
		SimpleAdapter adapter1 = new SimpleAdapter(this, data1, R.layout.item_pengrenjiqiao, new String[]{"id"}, new int[]{R.id.tv_item_pengrenjiqiao});
		lv_prjq.setAdapter(adapter1);
		
		Log.d(TAG, "tiaoliao:"+tiaoliao);
		String content = c.getContent();
		
		Log.d(TAG, "content:"+content);
		String yyxx = c.getYyxx();
		Log.d(TAG, "yyxx:"+yyxx);
		String smalltext = c.getSmalltext();
		Log.d(TAG, "smalltext:"+smalltext);
	};

	/**
	 * 得到调料
	 */
/*	private void getTiaoliao() {
		new Thread(){
			public void run() {
				tiaoliao = c.getTiaoliao();
				handler.sendEmptyMessage(0);
			};
		}.start();
	}
*/

	private void findViewById() {
		iv_newsphoto = (ImageView) findViewById(R.id.iv_newsphoto);
		iv_pic = (ImageView) findViewById(R.id.iv_pic);
		iv_writerpic = (ImageView) findViewById(R.id.iv_writerpic);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_writer = (TextView) findViewById(R.id.tv_writer);
		tv_gongyi = (TextView) findViewById(R.id.tv_gongyi);
		tv_makediff = (TextView) findViewById(R.id.tv_makediff);
		tv_makepretime = (TextView) findViewById(R.id.tv_makepretime);
		tv_kouwei = (TextView) findViewById(R.id.tv_kouwei);
		tv_makeamount = (TextView) findViewById(R.id.tv_makeamount);
		tv_maketime = (TextView) findViewById(R.id.tv_maketime);
		tv_heat = (TextView) findViewById(R.id.tv_heat);
		tv_lookbigmap = (TextView) findViewById(R.id.tv_lookbigmap);
		lv_prjq = (ListView) findViewById(R.id.lv_prjq);
		iv_yongliao_picname = (ImageView)findViewById(R.id.iv_yongliao_picname);
		tv_yongliao_name = (TextView)findViewById(R.id.tv_yongliao_name);
		tv_yongliao_num = (TextView)findViewById(R.id.tv_yongliao_num);
		lv_yongliao = (ListView) findViewById(R.id.lv_yongliao);
		gv_tiaoliao = (GridView) findViewById(R.id.gv_tiaoliao);
		lv_make = (ListView)findViewById(R.id.lv_make);
	}
	
	/**
	 * 
	 * @author AdministratoranimateFirstListener是当图片第一次加载的监听事件，
	 * 目的在于显示一个淡入的显示效果动画，可以添加其他事件
	 *
	 */
	@SuppressWarnings("unused")
	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {
		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage!=null) {
				ImageView imageView = (ImageView) view;
				 boolean firstDisplay = !displayedImages.contains(imageUri);
	                if (firstDisplay) {
	                    FadeInBitmapDisplayer.animate(imageView, 3000); //设置image隐藏动画500ms
	                    displayedImages.add(imageUri); //将图片uri添加到集合中
	                }
			}
		}
		
	}
	class MyThread extends Thread{
		@Override
		public void run() {
			super.run();
			//获得用料食材
			String zhuliao = c.getZhuliao();
			//Log.d(TAG, "用料："+zhuliao.toString());
			
			String[] str = zhuliao.split(";");
			//Log.d(TAG, "str.length："+str.length);
			yongliaolist = new ArrayList<YongLiao>();
			for(int i=0;i<str.length;i++){
				String zhuliaoitem = str[i];
				String[] zhuliaoitemitem = zhuliaoitem.split("@");
				//Log.d(TAG, "zhuliaoitemitem.length："+zhuliaoitemitem.length);
				yongliao = new YongLiao();
				for (int j = 0; j <= zhuliaoitemitem.length; j++) {
					if (j==0) {
						yongliao.setIcon(zhuliaoitemitem[j].toString());
					}else if (j==1) {
						yongliao.setName(zhuliaoitemitem[j].toString());
					}else if (j==2) {
						yongliao.setNum(zhuliaoitemitem[j].toString());
					}else{
						yongliao.setD(getResources().getDrawable(R.drawable.index_arrow));
					}
				}
				yongliaolist.add(yongliao);
			}
			YongLiaoAdapter adapter = new YongLiaoAdapter(ItemActivity.this, yongliaolist,imageLoader);
			lv_yongliao.setAdapter(adapter);
		}
	}
	/**
	 * @param requestCode请求码
	 * @param resultCode结束码
	 * @param data 数据
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		// 拍照回来了
		case camera_requestCode:
			// 拿到拍到的照片
			Bitmap bitMap = data.getParcelableExtra("data");
			iv_pic.setImageBitmap(bitMap);
			break;
		// 从相册回来了
		case pick_requestCode:
			// 拿到在相册中选的照片
			// uri->BitMap
			// 这个方法的实质，就是根据Uri查询系统数据库，得到文件
			try {
				Bitmap bm = Media.getBitmap(getContentResolver(),
						data.getData());
				iv_pic.setImageBitmap(bm);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		default:
			break;
		}
	}

	public void camera(View btn) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("操作选项");
		String[] itemsId = { "拍照", "用户相册", "取消" };
		builder.setItems(itemsId, new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case camera_requestCode:
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, camera_requestCode);
					break;
				case pick_requestCode:
					Intent intent1 = new Intent(Intent.ACTION_PICK);
					intent1.setType("vnd.android.cursor.dir/image");
					startActivityForResult(intent1, pick_requestCode);
					break;
				case cancel:

					break;

				default:
					break;
				}
			}
		});
		builder.show();
	}
}
