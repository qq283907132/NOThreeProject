package com.meishijie.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.meishijie.adapter.SearchGridViewAdapter;
import com.meishijie.main.R;

/***
 * 网络搜索的fragment
 * @author 羊婕
 *
 */
public class NetSearchFragment extends Fragment {

	private GridView search_gv;
	
	//食材图标
	private int[] images = { R.drawable.tudou, R.drawable.doufu,
			R.drawable.zhuxiaopai, R.drawable.jidan, R.drawable.niurou,
			R.drawable.qiezi, R.drawable.nd_184, R.drawable.gy_184,
			R.drawable.kw_184 };

	//食材名称
	private int[] texts = { R.string.tudou, R.string.doufu,
			R.string.zhuxiaopai, R.string.jidan, R.string.niurou,
			R.string.qiezi, R.string.nandu, R.string.gongyi, R.string.kouwei };

	private SearchGridViewAdapter adapter;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//加载网络搜索布局
		View viewLayout = inflater.inflate(R.layout.search_net_fragment, container, false);
		//食材的GridView
		search_gv = (GridView) viewLayout.findViewById(R.id.search_gv);
		//给GridView的每个Item设置事件监听
		search_gv.setOnItemClickListener(new ViewOnItemClickListener());
		//适配器
		adapter = new SearchGridViewAdapter(getActivity(),texts,images);
		//设置绑定适配器
		search_gv.setAdapter(adapter);
		return viewLayout;
	}
	
	//GridView的点击事件的处理
	class ViewOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(getActivity(), "images"+(position+1), Toast.LENGTH_SHORT).show();
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
