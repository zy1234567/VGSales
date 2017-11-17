package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * PhotoView位于viewpager中必须使用该viewpager
 * @author smm
 *
 */
public class PhotoViewViewPager extends ViewPager {

	public PhotoViewViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PhotoViewViewPager(Context context) {
		super(context);
	}

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
