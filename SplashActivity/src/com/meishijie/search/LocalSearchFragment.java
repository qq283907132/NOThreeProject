package com.meishijie.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;

import com.meishijie.activities.SearchActivity;
import com.meishijie.main.R;

public class LocalSearchFragment extends Fragment {

	private View local_nd;
	private View local_gy;
	private View local_kw;
	private GridView search_net_date_gv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.search_local_fragment,
				container, false);
		// 通过ID查找控件
		findsId(layout);
		//控件点击事件监听
		setViewOnClickListener();
		return layout;
	}

	// 查找控件
	private void findsId(View v) {
		//按难度
		local_nd = v.findViewById(R.id.local_nd);
		//按工艺
		local_gy = v.findViewById(R.id.local_gy);
		//按口味
		local_kw = v.findViewById(R.id.local_kw);
		//数据的gridView
		search_net_date_gv = (GridView) v.findViewById(R.id.search_net_date_gv);
	}

	// 给每一个控件设置监听
	private void setViewOnClickListener() {
		ViewOnClickListener viewOnClickListener = new ViewOnClickListener();

		local_nd.setOnClickListener(viewOnClickListener);
		local_gy.setOnClickListener(viewOnClickListener);
		local_kw.setOnClickListener(viewOnClickListener);
	}

	// 每个控件的监听事件动作
	private class ViewOnClickListener implements OnClickListener {

		Intent intent = new Intent();

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			// 按难度
			case R.id.local_nd:
				NetSearchDateFragment nsdf = new NetSearchDateFragment();
				fragment_replace(nsdf);
				break;
			// 按工艺
			case R.id.local_gy:
				LocalSearchDateFragment lsdf = new LocalSearchDateFragment();
				fragment_replace(lsdf);
				break; 
			// 按口味
			case R.id.local_kw:
				LocalSearchKouweiDateFragment lskwdf = new LocalSearchKouweiDateFragment();
				fragment_replace(lskwdf);
				break;
			default:
				break;
			}
		}

	}

	public void fragment_replace(Fragment fragment){
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction trans = fm.beginTransaction();
		trans.replace(R.id.search_fragments_replace, fragment);
		trans.addToBackStack(null);
		trans.commit();
		/*if(getActivity() instanceof SearchActivity){
			SearchActivity search = (SearchActivity) getActivity();
			search.fragment_replace(fragment);
		}*/
		
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
