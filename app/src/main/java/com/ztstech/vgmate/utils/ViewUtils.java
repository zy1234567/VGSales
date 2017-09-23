package com.ztstech.vgmate.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

import com.ztstech.vgmate.base.BaseApplication;

/**
 * Created by zhiyuan on 2017/7/28.
 * view相关工具
 */

public class ViewUtils {

    /**
     * dp转px
     */
    public static int dp2px(float dpValue) {
        return dp2px(BaseApplication.getApplicationInstance(), dpValue);
    }

    /**
     * px转dp
     */
    public static int px2dp(float pxValue) {
        return px2dp(BaseApplication.getApplicationInstance(), pxValue);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }


    /**
     * 设置dialog全屏
     * @param dialog
     */
    public static void setDialogFullScreen(Dialog dialog) {
        Point point = new Point();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(point);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams mWindowAttributes = dialog.getWindow().getAttributes();
        mWindowAttributes.dimAmount = 0f;
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        mWindowAttributes.width = point.x;
        mWindowAttributes.height = WindowManager.LayoutParams.MATCH_PARENT;
    }


}
