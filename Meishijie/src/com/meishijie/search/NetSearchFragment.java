package com.meishijie.search;

import com.meishijie.main.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NetSearchFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//加载网络搜索布局
		View viewLayout = inflater.inflate(R.layout.search_net_fragment, container, false);
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
