package com.example.love_hzau;

import java.text.SimpleDateFormat;

import com.example.love_hzau.itterface.OnRefreshListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView; 
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyListView extends ListView implements OnScrollListener {

	private int firstVisibleItemPosition; // ��Ļ��ʾ�ڵ�һ����item������
	private int downY; // ����ʱy���ƫ����
	private int headerViewHeight; // ͷ���ֵĸ߶�
	private View headerView; // ͷ���ֵĶ���

	private final int DOWN_PULL_REFRESH = 0; // ����ˢ��״̬
	private final int RELEASE_REFRESH = 1; // �ɿ�ˢ��
	private final int REFRESHING = 2; // ����ˢ����
	private int currentState = DOWN_PULL_REFRESH; // ͷ���ֵ�״̬: Ĭ��Ϊ����ˢ��״̬

	private Animation upAnimation; // ������ת�Ķ���
	private Animation downAnimation; // ������ת�Ķ���

	private ImageView ivArrow; // ͷ���ֵļ�ͷ
	private ProgressBar mProgressBar; // ͷ���ֵĽ�����
	private TextView tvState; // ͷ���ֵ�״̬
	private TextView tvLastUpdateTime; // ͷ���ֵ�������ʱ��

	private OnRefreshListener mOnRefershListener;
	private boolean isScrollToBottom; // �Ƿ񻬶����ײ�
	private View footerView; // �Ų��ֵĶ���
	private int footerViewHeight; // �Ų��ֵĸ߶�
	private boolean isLoadingMore = false; // �Ƿ����ڼ��ظ�����

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
		initFooterView();
		this.setOnScrollListener(this);
	}

	/**
	 * ��ʼ���Ų���
	 */
	private void initFooterView() {
		footerView = View.inflate(getContext(), R.layout.loader_footer, null);
		footerView.measure(0, 0);
		footerViewHeight = footerView.getMeasuredHeight();
		footerView.setPadding(0, -footerViewHeight, 0, 0);
		this.addFooterView(footerView);
	}

	/**
	 * ��ʼ��ͷ����
	 */
	private void initHeaderView() {
		headerView = View.inflate(getContext(), R.layout.refresh_header, null);
		ivArrow = (ImageView) headerView.findViewById(R.id.ivArrow);
		mProgressBar = (ProgressBar) headerView.findViewById(R.id.pbWaiting);
		tvState = (TextView) headerView.findViewById(R.id.refresh_tips);
		tvLastUpdateTime = (TextView) headerView.findViewById(R.id.refresh_last_time);

		// �������ˢ��ʱ��
		tvLastUpdateTime.setText("���ˢ��ʱ��: " + getLastUpdateTime());

		headerView.measure(0, 0); // ϵͳ������ǲ�����headerView�ĸ߶�
		headerViewHeight = headerView.getMeasuredHeight();
		headerView.setPadding(0, -headerViewHeight, 0, 0);
		this.addHeaderView(headerView); // ��ListView�Ķ������һ��view����
		initAnimation();
	}

	/**
	 * ���ϵͳ������ʱ��
	 * 
	 * @return
	 */
	private String getLastUpdateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(System.currentTimeMillis());
	}

	/**
	 * ��ʼ������
	 */
	private void initAnimation() {
		upAnimation = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		upAnimation.setDuration(500);
		upAnimation.setFillAfter(true); // ����������, ͣ���ڽ�����λ����

		downAnimation = new RotateAnimation(-180f, -360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		downAnimation.setDuration(500);
		downAnimation.setFillAfter(true); // ����������, ͣ���ڽ�����λ����
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveY = (int) ev.getY();
			// �ƶ��е�y - ���µ�y = ���.
			int diff = (moveY - downY) / 2;
			// -ͷ���ֵĸ߶� + ��� = paddingTop
			int paddingTop = -headerViewHeight + diff;
			// ���: -ͷ���ֵĸ߶� > paddingTop��ֵ ִ��super.onTouchEvent(ev);
			if (firstVisibleItemPosition == 0 && -headerViewHeight < paddingTop) {
				if (paddingTop > 0 && currentState == DOWN_PULL_REFRESH) { // ��ȫ��ʾ��.
					currentState = RELEASE_REFRESH;
					refreshHeaderView();
				} else if (paddingTop < 0 && currentState == RELEASE_REFRESH) { // û����ʾ��ȫ
					currentState = DOWN_PULL_REFRESH;
					refreshHeaderView();
				}
				// ����ͷ����
				headerView.setPadding(0, paddingTop, 0, 0);
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			// �жϵ�ǰ��״̬���ɿ�ˢ�»�������ˢ��
			if (currentState == RELEASE_REFRESH) {
				// ��ͷ��������Ϊ��ȫ��ʾ״̬
				headerView.setPadding(0, 0, 0, 0);
				// ���뵽����ˢ����״̬
				currentState = REFRESHING;
				refreshHeaderView();

				if (mOnRefershListener != null) {
					mOnRefershListener.onDownPullRefresh(); // ����ʹ���ߵļ�������
				}
			} else if (currentState == DOWN_PULL_REFRESH) {
				// ����ͷ����
				headerView.setPadding(0, -headerViewHeight, 0, 0);
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * ����currentStateˢ��ͷ���ֵ�״̬
	 */
	private void refreshHeaderView() {
		switch (currentState) {
		case DOWN_PULL_REFRESH: // ����ˢ��״̬
			tvState.setText("����ˢ��");
			ivArrow.startAnimation(downAnimation); // ִ��������ת
			break;
		case RELEASE_REFRESH: // �ɿ�ˢ��״̬
			tvState.setText("�ɿ�ˢ��");
			ivArrow.startAnimation(upAnimation); // ִ��������ת
			break;
		case REFRESHING: // ����ˢ����״̬
			ivArrow.clearAnimation();
			ivArrow.setVisibility(View.GONE);
			mProgressBar.setVisibility(View.VISIBLE);
			tvState.setText("����ˢ����...");
			break;
		default:
			break;
		}
	}

	/**
	 * ������״̬�ı�ʱ�ص�
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
			// �жϵ�ǰ�Ƿ��Ѿ����˵ײ�
			if (isScrollToBottom && !isLoadingMore) {
				isLoadingMore = true;
				// ��ǰ���ײ�
				footerView.setPadding(0, 0, 0, 0);
				this.setSelection(this.getCount());

				if (mOnRefershListener != null) {
					mOnRefershListener.onLoadingMore();
				}
			}
		}
	}

	/**
	 * ������ʱ����
	 * 
	 * @param firstVisibleItem
	 *            ��ǰ��Ļ��ʾ�ڶ�����item��position
	 * @param visibleItemCount
	 *            ��ǰ��Ļ��ʾ�˶��ٸ���Ŀ������
	 * @param totalItemCount
	 *            ListView������Ŀ������
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		firstVisibleItemPosition = firstVisibleItem;

		if (getLastVisiblePosition() == (totalItemCount - 1)) {
			isScrollToBottom = true;
		} else {
			isScrollToBottom = false;
		}
	}

	/**
	 * ����ˢ�¼����¼�
	 * 
	 * @param listener
	 */
	public void setOnRefreshListener(OnRefreshListener listener) {
		mOnRefershListener = listener;
	}

	/**
	 * ����ͷ����
	 */
	public void hideHeaderView() {
		headerView.setPadding(0, -headerViewHeight, 0, 0);
		ivArrow.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.GONE);
		tvState.setText("����ˢ��");
		tvLastUpdateTime.setText("���ˢ��ʱ��: " + getLastUpdateTime());
		currentState = DOWN_PULL_REFRESH;
	}

	public void hideFooterView() {
		footerView.setPadding(0, -footerViewHeight, 0, 0);
		isLoadingMore = false;
	}
}
