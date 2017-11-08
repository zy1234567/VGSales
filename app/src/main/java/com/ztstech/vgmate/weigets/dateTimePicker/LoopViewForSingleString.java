package com.ztstech.vgmate.weigets.dateTimePicker;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by MingWang on 2017/5/27.
 *
 * 原LoopView为了适应多列数据滑动，触摸事件响应区域较窄，这里重写了e（），修改2.0f为14.0f
 */

public class LoopViewForSingleString extends LoopView {
    public LoopViewForSingleString(Context context) {
        super(context);
    }

    public LoopViewForSingleString(Context context, AttributeSet attributeset) {
        super(context, attributeset);
    }

    public LoopViewForSingleString(Context context, AttributeSet attributeset, int i1) {
        super(context, attributeset, i1);
    }

    @Override
    public void e() {
        Rect rect = new Rect();
        for (int i1 = 0; i1 < arrayList.size(); i1++) {
            // String s1 = (String) arrayList.get(i1);
            // wangpeng:调整可触摸区域的宽度为4个字符
            paintB.getTextBounds("0000", 0, "0000".length(), rect);
            int j1 = rect.width();
            // wangpeng:调整区域增大2倍，提升体验。
            j1 = (int) (j1 * 14.0f);
            if (j1 > g) {
                g = j1;
            }
            paintB.getTextBounds("\u661F\u671F", 0, 2, rect);
            j1 = rect.height();
            if (j1 > h) {
                h = j1;
            }
        }
    }
}
