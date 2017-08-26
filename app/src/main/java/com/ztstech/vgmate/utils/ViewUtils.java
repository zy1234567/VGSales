package com.ztstech.vgmate.utils;


import android.content.Context;

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
}
