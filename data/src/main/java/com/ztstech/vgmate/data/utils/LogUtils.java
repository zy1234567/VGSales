package com.ztstech.vgmate.data.utils;

import android.util.Log;

import com.ztstech.vgmate.data.BuildConfig;

/**
 * Created by zhiyuan on 2017/10/22.
 */

public class LogUtils {

    public static final void log(String text) {
        if (BuildConfig.DEBUG) {
            Log.d("VGMATE", "" + text);
        }
    }
}
