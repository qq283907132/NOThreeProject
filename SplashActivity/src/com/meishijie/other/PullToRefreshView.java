package com.meishijie.other;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

public class PullToRefreshView extends LinearLayout {

	private static final int TAP_TO_REFRESH = 1; // 初始状态
	private static final int PULL_TO_REFRESH = 2; // 拉动刷新
	private static final int RELEASE_TO_REFRESH = 3; // 释放刷新
	private static final int REFRESHING = 4; // 正在刷新
	public int mRefreshState;// 记录头当前状态
	public int mfooterRefreshState;//记录尾当前状态
	public Scroller scroller;
	public ScrollView sv;
	private View refreshView;//头部视图
	public View mfooterView;// 尾部视图
	public TextView mfooterViewText;
	private ImageView refreshIndicatorView;
	private int refreshTargetTop = -60;
	public int refreshFooter;
	private ProgressBar bar;
	private TextView downTextView;
	private TextView timeTextView;

	//private RefreshListener refreshListener;

	private int lastY;
	// 动画效果
	// 变为向下的箭头
	private RotateAnimation mFlipAnimation;
	// 变为逆向的箭头
	private RotateAnimation mReverseFlipAnimation;
	public int nowpull = -1;// 0为头部下拉，1为尾部上拉

	private boolean isRecord;
	private Context mContext;
	
	public PullToRefreshView(Context context) {
		super(context);
		mContext = context;
	}
	
	public PullToRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}
	
	
	private void init() {
		
	}
	
	
	public interface OnRefreshListener{
		void onRefresh();
	}
	
	//对外提供一个刷新监听 回调方法
	public void setOnRefreshListener(OnRefreshListener listener){
		
	}
	
	
}
