package com.ztstech.vgmate.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

/**
 * Created by zhiyuan on 2017/8/8.
 */

public class ContextUtils {

    /**
     * 上下文是否可用
     * @param context 上下文
     * @return 返回false禁止使用
     */
    public static boolean isContextFinishing(@Nullable Context context) {
        if (context == null) {
            return true;
        }
        if (context instanceof Activity) {
            return ((Activity) context).isFinishing();
        }
        return false;
    }
}
