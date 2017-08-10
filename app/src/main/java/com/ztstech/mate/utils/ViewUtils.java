package com.ztstech.mate.utils;


import com.ztstech.mate.base.BaseApplication;

/**
 * Created by zhiyuan on 2017/7/28.
 * view相关工具
 */

public class ViewUtils {

    /**
     * dp转px
     */
    public static int dp2px(float dpValue) {
        final float scale = BaseApplication.getApplicationInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     */
    public static int px2dp(float pxValue) {
        final float scale = BaseApplication.getApplicationInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
