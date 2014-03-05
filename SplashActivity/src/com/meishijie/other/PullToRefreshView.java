package com.meishijie.other;

import java.util.Date;

import com.meishijie.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
@SuppressWarnings("unused")
public class PullToRefreshView extends LinearLayout {

	private static final int TAP_TO_REFRESH = 1; // 初始状态
	private static final int PULL_TO_REFRESH = 2; // 拉动刷新
	private static final int RELEASE_TO_REFRESH = 3; // 释放刷新
	private static final int REFRESHING = 4; // 正在刷新
	public int mRefreshState;// 记录当前状态
	
	public Scroller scroller;
	public ScrollView sv;
	
	private View refreshView;//刷新视图控件
	//private OnRefreshListener refreshListener;
	private int lastY;
	private int refreshTargetTop = -60;
	

	private boolean isRecord;
	private Context mContext;
	private ProgressBar refreshBar;
	private TextView refreshText;
	private TextView refreshUpdateTime;
	
	
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
		// 滑动对象，
		/*scroller = new Scroller(mContext);
		refreshView = LayoutInflater.from(mContext).inflate(R.layout.refresh_header, null);
		
		refreshBar = (ProgressBar) refreshView.findViewById(R.id.pull_to_refresh_progress); 
		refreshText = (TextView) refreshView.findViewById(R.id.pull_to_refresh_text);
		refreshUpdateTime = (TextView) refreshView.findViewById(R.id.pull_to_refresh_updated_at);
		
		LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, -refreshTargetTop);
		lp.topMargin = refreshTargetTop;
		lp.gravity = Gravity.CENTER;
		addView(refreshView, lp);
		
		mRefreshState = TAP_TO_REFRESH;
		isRecord = false;*/
	}
	

	/*public boolean onTouchEvent(MotionEvent event) {
		int y = (int) event.getRawY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 记录下y坐标
			if (isRecord == false) {
				lastY = y;
				isRecord = true;
			}
			break;

		case MotionEvent.ACTION_MOVE:
			// y移动坐标
			int m = y - lastY;

			doMovement(m);
			// 记录下此刻y坐标
			lastY = y;
			break;

		case MotionEvent.ACTION_UP:
			fling();
			isRecord = false;
			break;
		}
		return true;
	}

	private void fling() {
		// TODO Auto-generated method stub
		if (mRefreshState != REFRESHING) {
			LinearLayout.LayoutParams lp = (LayoutParams) refreshView
					.getLayoutParams();
			if (lp.topMargin > 0) {// 拉到了触发可刷新事件
				refresh();
			} else {
				returnInitState();
			}
		}
	}
	
	// 刷新
	public void onRefresh() {
		
		if (refreshListener != null) {

			refreshListener.onRefresh();
		}
	}

	private void returnInitState() {
		mRefreshState = TAP_TO_REFRESH;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.refreshView
				.getLayoutParams();
		int i = lp.topMargin;
		scroller.startScroll(0, i, 0, refreshTargetTop);
		invalidate();
	}

	private void refresh() {
		mRefreshState = REFRESHING;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.refreshView
				.getLayoutParams();
		int i = lp.topMargin;
		
		refreshBar.setVisibility(View.VISIBLE);
		refreshText.setText("正在刷新。。。");
		refreshUpdateTime.setText("最近更新 ： "+new Date().toLocaleString());
		
		scroller.startScroll(0, i, 0, 0 - i);
		invalidate();

		if (refreshListener != null) {

			refreshListener.onRefresh();
		}

	}
	
	
	@Override
	public void computeScroll() {
		if (scroller.computeScrollOffset()) {
			int i = this.scroller.getCurrY();
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.refreshView
					.getLayoutParams();
			int k = Math.max(i, refreshTargetTop);
			lp.topMargin = k;
			this.refreshView.setLayoutParams(lp);
			this.refreshView.invalidate();
			invalidate();
		}
	}

	private void doMovement(int moveY) {
		LinearLayout.LayoutParams lp = (LayoutParams) refreshView
				.getLayoutParams();

		if (mRefreshState != REFRESHING) {
			// 获取view的上边距
			float f1 = lp.topMargin;
			float f2 = f1 + moveY * 0.3F;
			int i = (int) f2;
			// 修改上边距
			lp.topMargin = i;
			// 修改后刷新
			refreshView.setLayoutParams(lp);
			refreshView.invalidate();
			invalidate();
			
			refreshText.setVisibility(View.VISIBLE);
			refreshBar.setVisibility(View.GONE);
			if (lp.topMargin > 0 && mRefreshState != RELEASE_TO_REFRESH) {
				
				refreshText.setText("松开可以刷新");
				
				mRefreshState = RELEASE_TO_REFRESH;
				
			} else if (lp.topMargin <= 0 && mRefreshState != PULL_TO_REFRESH) {
				refreshText.setText("向下拉动可以刷新");
				if (mRefreshState != TAP_TO_REFRESH) {
					
				}
				mRefreshState = PULL_TO_REFRESH;
			}
		}
	}
	
	*//**
	 * 结束刷新事件
	 *//*
	public void finishRefresh() {
		if (mRefreshState != TAP_TO_REFRESH) {
			mRefreshState = TAP_TO_REFRESH; // 初始刷新状态
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.refreshView
					.getLayoutParams();
			int i = lp.topMargin;
			
			refreshBar.setVisibility(View.GONE);
			
			scroller.startScroll(0, i, 0, refreshTargetTop);
			invalidate();
		}
		
	}
	
	*//**
	 * 该方法一般和ontouchEvent 一起用
	 *//*
	@Override
	public boolean onInterceptTouchEvent(MotionEvent e) {
		int action = e.getAction();
		int y = (int) e.getRawY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (isRecord == false) {
				Log.i("moveY", "lastY:" + y);
				lastY = y;
				isRecord = true;
			}
			break;

		case MotionEvent.ACTION_MOVE:
			// y移动坐标
			int m = y - lastY;

			if (canScroll(m)) {
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			isRecord = false;
			break;

		case MotionEvent.ACTION_CANCEL:

			break;
		}
		return false;
	}

	private boolean canScroll(int diff) {
		View childView;
		Log.i("other", "mRefreshState:" + mRefreshState);
		if (mRefreshState == REFRESHING) {
			return true;
		}
		if (getChildCount() > 1) {
			childView = this.getChildAt(1);
			if (childView instanceof ListView) {
				int top = ((ListView) childView).getChildAt(0).getTop();
				int pad = ((ListView) childView).getListPaddingTop();
				if ((Math.abs(top - pad)) < 3
						&& ((ListView) childView).getFirstVisiblePosition() == 0) {
					return true;
				} else {
					return false;
				}
			} else if (childView instanceof ScrollView) {
				// 头部下拉
				if (((ScrollView) childView).getScrollY() == 0 && diff > 0) {
					return true;
				} else if ((((ScrollView) childView).getChildAt(0)
						.getMeasuredHeight() <= ((ScrollView) childView)
						.getScrollY() + getHeight() && diff < 0)) {// 底部上拉
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}


	public interface OnRefreshListener{
		void onRefresh();
	}
	
	//对外提供一个刷新监听 回调方法
	public void setOnRefreshListener(OnRefreshListener listener){
		refreshListener = listener;
	}*/
}
