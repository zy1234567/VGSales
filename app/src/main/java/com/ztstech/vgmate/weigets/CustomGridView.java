package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class CustomGridView extends ViewGroup {

    /**子控件之间距离*/
    private int mChildViewMarginHorizontal = 16;

    private int mChildViewMarginVertical = 16;

    /**列数*/
    private int countOfColumn = 4;

    public CustomGridView(Context context) {
        this(context, null, 0);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int childWidth = (width - (countOfColumn - 1) * mChildViewMarginHorizontal) / countOfColumn;
        int childSize = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
        for (int index = 0; index < getChildCount(); index++) {
            getChildAt(index).measure(childSize, childSize);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (MeasureSpec.EXACTLY != heightMode) {
            int totalRow = getChildCount() / countOfColumn + 1;     //总行数
            int height = (totalRow - 1) * mChildViewMarginVertical + totalRow * childWidth;
            setMeasuredDimension(width, height);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childWidth = (getWidth() - (countOfColumn - 1) * mChildViewMarginHorizontal) / countOfColumn;
        for (int i = 0; i < getChildCount(); i++) {
            //得到所在行、列
            int row = i / countOfColumn;
            int column = i % countOfColumn;

            int x = column * childWidth + column * mChildViewMarginHorizontal;
            int y = row * childWidth + row * mChildViewMarginVertical;

            getChildAt(i).layout(x, y, x + childWidth, y + childWidth);
        }
    }
}