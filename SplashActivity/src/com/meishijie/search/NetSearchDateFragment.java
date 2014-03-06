package com.meishijie.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.meishijie.activities.SearchContentActivity;
import com.meishijie.main.R;

public class NetSearchDateFragment extends Fragment {

	private String[] nd_titles = { "新手尝试", "初级入门", "初中水平", "中级掌勺", "高级厨师",
			"厨神级" };
	private GridView net_data_gv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.search_net_date_fragment,
				container, false);
		net_data_gv = (GridView) layout.findViewById(R.id.search_net_date_gv);

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
			String name = nd_titles[position];
			Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.putExtra("title_name", name);
			intent.putExtra("MSG", "nandu_content");
			intent.setClass(getActivity(), SearchContentActivity.class);
			startActivity(intent);
		}
		
	}
	/**
	 * 适配器
	 * @author 羊婕
	 *
	 */
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return nd_titles.length;
		}

		@Override
		public Object getItem(int position) {
			return nd_titles[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(getActivity()).inflate(
					R.layout.search_net_date_fragment_item, null, false);
			TextView tv_data_fenlei = (TextView) convertView
					.findViewById(R.id.tv_data_fenlei);
			tv_data_fenlei.setText(nd_titles[position]);
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
