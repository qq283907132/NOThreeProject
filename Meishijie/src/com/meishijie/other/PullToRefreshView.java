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

	private static final int TAP_TO_REFRESH = 1; // ��ʼ״̬
	private static final int PULL_TO_REFRESH = 2; // ����ˢ��
	private static final int RELEASE_TO_REFRESH = 3; // �ͷ�ˢ��
	private static final int REFRESHING = 4; // ����ˢ��
	public int mRefreshState;// ��¼ͷ��ǰ״̬
	public int mfooterRefreshState;//��¼β��ǰ״̬
	public Scroller scroller;
	public ScrollView sv;
	private View refreshView;//ͷ����ͼ
	public View mfooterView;// β����ͼ
	public TextView mfooterViewText;
	private ImageView refreshIndicatorView;
	private int refreshTargetTop = -60;
	public int refreshFooter;
	private ProgressBar bar;
	private TextView downTextView;
	private TextView timeTextView;

	//private RefreshListener refreshListener;

	private int lastY;
	// ����Ч��
	// ��Ϊ���µļ�ͷ
	private RotateAnimation mFlipAnimation;
	// ��Ϊ����ļ�ͷ
	private RotateAnimation mReverseFlipAnimation;
	public int nowpull = -1;// 0Ϊͷ��������1Ϊβ������

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
