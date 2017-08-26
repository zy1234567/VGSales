package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.utils.ViewUtils;

/**
 * Created by zhiyuan on 2017/8/26.
 */

public class CircleGapView extends View {

    private Paint mPaint = new Paint();

    private int mColor;

    private int mFillRadius = 3;

    private float mStrokeWidth = 1;

    public CircleGapView(Context context) {
        this(context, null, 0);
    }

    public CircleGapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleGapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mColor = ContextCompat.getColor(context, R.color.color_006);

        mFillRadius = ViewUtils.dp2px(context, mFillRadius);
        mStrokeWidth = ViewUtils.dp2px(context, mStrokeWidth);

        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, mFillRadius, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width / 2, height / 2, width / 2 - mStrokeWidth / 2, mPaint);
    }
}
