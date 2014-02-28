package com.meishijie.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.meishijie.adapter.SearchGridViewAdapter;
import com.meishijie.main.R;

public class NetSearchFragment extends Fragment {

	private GridView search_gv;
	
	private int[] images = { R.drawable.tudou, R.drawable.doufu,
			R.drawable.zhuxiaopai, R.drawable.jidan, R.drawable.niurou,
			R.drawable.qiezi, R.drawable.nd_184, R.drawable.gy_184,
			R.drawable.kw_184 };

	private int[] texts = { R.string.tudou, R.string.doufu,
			R.string.zhuxiaopai, R.string.jidan, R.string.niurou,
			R.string.qiezi, R.string.nandu, R.string.gongyi, R.string.kouwei };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//加载网络搜索布局
		View viewLayout = inflater.inflate(R.layout.search_net_fragment, container, false);
		search_gv = (GridView) viewLayout.findViewById(R.id.search_gv);
		SearchGridViewAdapter adapter = new SearchGridViewAdapter(getActivity(),texts,images);
		search_gv.setAdapter(adapter);
		return viewLayout;
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
