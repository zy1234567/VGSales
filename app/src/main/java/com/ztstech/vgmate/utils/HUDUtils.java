package com.ztstech.vgmate.utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by wangzhiyuan on 2017/4/21.
 */
public class HUDUtils {

    /**
     * 实例化
     * @param context Application 上下文
     */
    public static KProgressHUD create(Context context) {
        return create(context,"正在加载");
    }

    /**
     *自定义提示信息加载框
     * @param context
     * @param hint
     * @return
     */
    public static KProgressHUD create(Context context, String hint) {
        return KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(hint)
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f);
    }

}
