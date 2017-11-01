package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by zhiyuan on 2017/10/31.
 */

public class BlurFrameLayout extends FrameLayout {

    private BlurCalculate mBlurCalculate;

    public BlurFrameLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public BlurFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBlurCalculate=new BlurCalculate(this);
    }

    public void setRadius(int radius){
        if(mBlurCalculate!=null) {
            mBlurCalculate.setRadius(radius);
        }
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mBlurCalculate.onAttachedToWindow();
    }
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBlurCalculate.BluronDetachedFromWindow();
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(mBlurCalculate.isCanvasChanged(canvas)) {
            mBlurCalculate.BlurCanvas();
        }else {
            mBlurCalculate.DrawCanvas(canvas);
            super.dispatchDraw(canvas);
        }
    }
}
