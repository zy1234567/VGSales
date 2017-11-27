package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * 分享列表显示图片所用的listview
 */
public class MyGridView extends GridView {

	private float mTouchX;
	private float mTouchY;
	
	float lastX;
	float lastY;
	long downLongTime;
	long downTime;
	
	private OnTouchBlankPositionListener mTouchBlankPosListener;
	private OnLongTouchBlankPositionListener mLongTouchBlankPosListener;
	
	public MyGridView(Context context) {
		super(context);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	int flag = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mTouchBlankPosListener != null) {
			if (!isEnabled()) {
				return isClickable() || isLongClickable();
			}
			float x = event.getX();
			float y = event.getY();
			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){

				downTime = System.currentTimeMillis();
			}
			if (event.getActionMasked() == MotionEvent.ACTION_UP) {
				final int motionPosition = pointToPosition((int) x, (int) y);
				
				long upTime = System.currentTimeMillis();
				long intervalTime = upTime - downTime;
				if (motionPosition == INVALID_POSITION && intervalTime < 700) {
		
					mTouchBlankPosListener.onTouchBlank(event);
				}
			}
		}
		
		if(mLongTouchBlankPosListener != null){

			if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
				

				downLongTime = System.currentTimeMillis();
			}
			if(event.getActionMasked() == MotionEvent.ACTION_MOVE){

				long upTime = System.currentTimeMillis();
				long intervalTime = upTime - downLongTime;  
				
			        if (intervalTime >= 450 && flag == 0 ) {  
			        	flag= 1;
			        	mLongTouchBlankPosListener.onLongTouchBlank(event);
			        } 
			}
			if(event.getActionMasked() == MotionEvent.ACTION_UP){
				flag = 0;
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 设置GridView的空白区域的触摸事件
	 * 
	 * @param listener
	 */
	public void setOnTouchBlankPositionListener(
			OnTouchBlankPositionListener listener) {
		mTouchBlankPosListener = listener;
	}

	public interface OnTouchBlankPositionListener {
		void onTouchBlank(MotionEvent event);
	}
	/**
	 * 设置GridView的空白区域的长按触摸事件
	 *
	 * @param listener
	 */
	public void setOnLongTouchBlankPositionListener(
			OnLongTouchBlankPositionListener listener) {
		mLongTouchBlankPosListener = listener;
	}
	public interface OnLongTouchBlankPositionListener {
		void onLongTouchBlank(MotionEvent event);
	}
}
