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
@SuppressWarnings("unused")
public class PullToRefreshView extends LinearLayout {

	private static final int TAP_TO_REFRESH = 1; 
	private static final int PULL_TO_REFRESH = 2; 
	private static final int RELEASE_TO_REFRESH = 3; 
	private static final int REFRESHING = 4; 
	public int mRefreshState;
	public int mfooterRefreshState;
	public Scroller scroller;
	public ScrollView sv;
	private View refreshView;
	public View mfooterView;
	public TextView mfooterViewText;
	private ImageView refreshIndicatorView;
	private int refreshTargetTop = -60;
	public int refreshFooter;
	private ProgressBar bar;
	private TextView downTextView;
	private TextView timeTextView;

	//private RefreshListener refreshListener;

	private int lastY;

	private RotateAnimation mFlipAnimation;

	private RotateAnimation mReverseFlipAnimation;
	public int nowpull = -1;

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
	
	//�����ṩһ��ˢ�¼��� �ص�����
	public void setOnRefreshListener(OnRefreshListener listener){
		
	}
	
	
}
