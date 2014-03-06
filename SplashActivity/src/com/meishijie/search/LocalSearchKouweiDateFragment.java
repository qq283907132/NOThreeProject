package com.meishijie.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.meishijie.activities.SearchContentActivity;
import com.meishijie.main.R;
import com.meishijie.search.NetSearchDateFragment.MyAdapter;

public class LocalSearchKouweiDateFragment extends Fragment {

	private String[] kw_titles = { "家常味", "香辣味", "咸鲜味", "甜味", "酸甜味", "酸辣味",
			"麻辣味", "酱香味", "奶香味", "蒜香味", "鱼香味", "葱香味", "果味", "五香味", "咖哩味",
			"椒麻味", "茄汁味", "酸味", "苦香味", "姜汁味", "怪味", "芥末味", "红油味", "豆瓣味", "麻酱味", "黑椒味",
			"糊辣味", "其他"};
	private GridView net_data_gv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.search_net_date_fragment,
				container, false);
		net_data_gv = (GridView) layout.findViewById(R.id.search_net_date_gv);

		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.search_net_date_fragment_item, nd_titles);*/
	
		MyAdapter adapter = new MyAdapter();
		net_data_gv.setAdapter(adapter);
		net_data_gv.setOnItemClickListener(new ViewSetOnItemClickListener());
		return layout;
	}

	/**
	 * GridView 的条目点击事件
	 * @author 羊婕
	 *
	 */
	class ViewSetOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//获取到每一个点击的当前的Item的TextView的值
			String name = kw_titles[position];
			Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.putExtra("title_name", name);
			intent.putExtra("MSG", "kouwei_content");
			intent.setClass(getActivity(), SearchContentActivity.class);
			startActivity(intent);
		}
		
	}
	/**
	 * 适配器
	 * @author 羊婕
	 *
	 */
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return kw_titles.length;
		}

		@Override
		public Object getItem(int position) {
			return kw_titles[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(getActivity()).inflate(R.layout.search_net_date_fragment_item, null, false);
			TextView tv_data_fenlei = (TextView) convertView.findViewById(R.id.tv_data_fenlei);
			tv_data_fenlei.setText(kw_titles[position]);
			return convertView;
		}
		
		
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
