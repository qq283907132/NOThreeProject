package com.meishijie.activities;

import java.util.ArrayList;
import java.util.List;

import com.meishijie.adapter.FoodAdapter;
import com.meishijie.dao.INewsContentDao;
import com.meishijie.dao.impl.NewsContentDaoImpl;
import com.meishijie.entity.NewsClass;
import com.meishijie.entity.NewsContent;
import com.meishijie.main.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class NewsContentActivity extends Activity implements OnScrollListener {
	private ListView listView;
	private INewsContentDao contentDao;
	private List<NewsClass> classList;
	private String bclassname;
	private FoodAdapter adapter;
	// 放置记录的总条数
	private List<NewsContent> total = null;
	// 是否进行分页操作
	private int number = 10;//每次获取多少条数据
	//是否加载完成
    private boolean loadfinish = true;
    
	private View header;
	private View footer;
	private TextView title;
	private TextView desc;
	private List<NewsContent> contentList;
	private LinearLayout linearLayout;
	
	private String[] str = null;
	
	private ImageLoader imageLoader;
	
	private String[] data = new String[]{
			"http://images.meishij.net/p/20100719/bce3548403235bf27e56589db1d4cf73.jpg",
			"http://images.meishij.net/p/20100712/c719d7cac3df04dd3bc3103f7349e592.jpg",
			"http://images.meishij.net/p/20110127/bc28e90362678cf5f259b3491252bf43_150x150.jpg",
			"http://images.meishij.net/p/20110926/ddd57c81ac0c30740d7b4869742dc8b5_150x150.jpg",
			"http://images.meishij.net/p/20100925/7b0863da51b668d8f41fdd977883bc47.jpg",
			"http://images.meishij.net/p/20100915/7bf9281871985d19c3580cf955675204.jpg",
			"http://images.meishij.net/p/20100901/b223d8d24aa3f48de5e4ead1f035d3e9.jpg",
			"http://images.meishij.net/p/20101102/fc8ec9f0ba172e0b9097bd8b6592d57c.jpg",
			"http://images.meishij.net/p/20100712/14b2b006b7bccb16036d2f6be7a6caac.jpg",
			"http://images.meishij.net/p/20100329/d9c7d8723e322d069a24435a950e4fce.jpg",
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fooder_menu);
		
		this.imageLoader = ImageLoader.getInstance();
		
		str = new String[]{"菜系是在取材、刀工、调制等方面,有鲜明地方特色具备一定规模体系的菜肴流派。",
				"全球各地都有迥异的饮食文化,和不同的食风食俗,体现了人类文明的多样性化。",
				"厨房虽小,门道多多。掌握了一个技巧就能提高一份效率获得一些乐趣。",
				"烘焙又称烘烤,是在食材燃点之下通过干热方式使其脱水变干变硬变熟的工艺。",
				"营养物质的摄取是所有每个人生命所必要的物质基础，有共性也有特殊性。",
				"利用食物的性能稳定人体内环境,与自然环境相适应,才能保持健康,祛病延年。",
				"民以食为天,合理的营养能够促进健康,反之则引起疾病。",
				"五脏相互协调依赖制约,生克制化,保持动态平衡,才能保证生理活动顺利进行。",
				"家常菜,是中国菜的源头,也是地方风味菜系的组成基础。"};
		
		total = new ArrayList<NewsContent>();
		
		Intent intent = getIntent();
		bclassname = intent.getStringExtra("bclassname");
		classList = (List<NewsClass>) intent.getSerializableExtra("classList");
		
		linearLayout = (LinearLayout) findViewById(R.id.bottom_menu);
		
		title = (TextView) findViewById(R.id.title);
		title.setText(bclassname);
		
		
		
		listView = (ListView) findViewById(R.id.fooder_menu_list);
		header = LayoutInflater.from(this).inflate(R.layout.list_header,
				listView, false);
		imageView = (ImageView) header.findViewById(R.id.pic);
		desc = (TextView) header.findViewById(R.id.desc);
		listView.addHeaderView(header);
		footer = LayoutInflater.from(this).inflate(R.layout.list_more,
				listView, false);
		listView.addFooterView(footer);
		
		adapter = new FoodAdapter(this,this.imageLoader);
		
		contentDao = new NewsContentDaoImpl(this);
		initView(bclassname, classList);

		listView.removeFooterView(footer);
		listView.setOnScrollListener(this);
		
		
		for(int i=0;i<classList.size();i++){
			View layout = LayoutInflater.from(this).inflate(R.layout.fooder_bottom_menu, null);
			TextView content = (TextView) layout.findViewById(R.id.textview);
			content.setText(classList.get(i).getName());
			linearLayout.addView(layout);
		}
		
	}

	private void initView(String bclassname, List<NewsClass> classList) {
		if ("中华菜系".equals(bclassname)) {
			Log.i("jky",str[0] );
			desc.setText(str[0]);
			this.imageLoader.displayImage(this.data[0], imageView);
			setListViewData(bclassname,classList);
		} else if ("外国菜谱".equals(bclassname)) {
			desc.setText(str[1]);
			this.imageLoader.displayImage(this.data[1], imageView);
			setListViewData(bclassname,classList);
		} else if ("菜谱大全".equals(bclassname)) {
			desc.setText(str[8]);
			this.imageLoader.displayImage(this.data[8], imageView);
			setListViewData(bclassname,classList);
		} else if ("营养健康".equals(bclassname)) {
			desc.setText(str[4]);
			this.imageLoader.displayImage(this.data[4], imageView);
			setListViewData(bclassname,classList);
		} else if ("烘焙".equals(bclassname)) {
			desc.setText(str[3]);
			this.imageLoader.displayImage(this.data[3], imageView);
			setListViewData(bclassname,classList);
		}else if ("厨房百科".equals(bclassname)) {
			desc.setText(str[2]);
			this.imageLoader.displayImage(this.data[2], imageView);
			setListViewData(bclassname,classList);
		} else if ("人群膳食".equals(bclassname)) {
			desc.setText(str[4]);
			this.imageLoader.displayImage(this.data[4], imageView);
			setListViewData(bclassname,classList);
		}else if ("功能性调理".equals(bclassname)) {
			desc.setText(str[5]);
			this.imageLoader.displayImage(this.data[5], imageView);
			setListViewData(bclassname,classList);
		} else if ("疾病调理".equals(bclassname)) {
			desc.setText(str[6]);
			this.imageLoader.displayImage(this.data[6], imageView);
			setListViewData(bclassname,classList);
		} else if ("脏腑调理".equals(bclassname)) {
			desc.setText(str[7]);
			this.imageLoader.displayImage(this.data[7], imageView);
			setListViewData(bclassname,classList);
		}
	}
	
	public void setListViewData(String bclassname, List<NewsClass> classList){
		contentList = contentDao
				.getPartNewsContentBySuperName(bclassname,"0,10");
		total.addAll(contentList);
		adapter.setData(total);
		listView.setAdapter(adapter);
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			total.addAll((List<NewsContent>)msg.obj);
			adapter.notifyDataSetChanged();//告诉ListView数据已经发生改变，要求ListView更新界面显示
			if(listView.getFooterViewsCount() > 0) listView.removeFooterView(footer);
			loadfinish = true;
		}    	
    };
	private ImageView imageView;

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
		int count = contentDao.getSuperSum(bclassname);
		Log.i("newscontent" , "count:"+count);
		int maxpage = count%number == 0 ? count/number : count/number+1;
		Log.i("newscontent" , "maxpage:"+maxpage);
		
		final int loadtotal = totalItemCount;
		int lastItemid = listView.getLastVisiblePosition();//获取当前屏幕最后Item的ID
		if((lastItemid+1) == totalItemCount){//达到数据的最后一条记录
			if(totalItemCount > 0){
				//当前页
				int currentpage = totalItemCount%number == 0 ? totalItemCount/number : totalItemCount/number+1;
				int nextpage = currentpage + 1;//下一页
				if(nextpage <= maxpage && loadfinish){
					loadfinish = false;
					listView.addFooterView(footer);
					
					new Thread(new Runnable() {						
						public void run() {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							List<NewsContent> result = contentDao.getPartNewsContentBySuperName(bclassname,loadtotal+","+number);
							handler.sendMessage(handler.obtainMessage(100, result));
						}
					}).start();
				}		
			}
					
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
		
	}
	
	

}
