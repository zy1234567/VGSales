package com.ztstech.vgmate.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
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


    /**
     * 获取不同字号spannableStringBuilder
     * @param builder 为空新建，不为空在现有基础上添加
     * @param strs 分段字符串
     * @param sizes 分段字符串大小，与分段字符串段数相同
     * @return
     */
    public static SpannableStringBuilder getDiffSizeSpan(@Nullable SpannableStringBuilder builder,
                                                         @NonNull String[] strs, @NonNull int[] sizes) {
        String text = strs[0];
        for (int i = 1; i < strs.length; i++) {
            text = text + strs[i];
        }
        if (builder == null) {
            builder = new SpannableStringBuilder(text);
        }

        int currentIndex = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].isEmpty()) {
                continue;
            }
            builder.setSpan(new AbsoluteSizeSpan(sizes[i]), currentIndex, currentIndex + strs[i].length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            currentIndex += strs[i].length();
        }
        return builder;
    }


    /**
     * 获取不同颜色span
     * @param builder 为空新建，不为空在现有基础上添加
     * @param strs 分段字符串
     * @param colors 与分段字符串对应的颜色值
     * @return
     */
    public static SpannableStringBuilder getDiffColorSpan(@Nullable SpannableStringBuilder builder,
                                                          @NonNull String[] strs,@NonNull int[] colors) {
        String text = strs[0];
        for (int i = 1; i < strs.length; i++) {
            text = text + strs[i];
        }
        if (builder == null) {
            builder = new SpannableStringBuilder(text);
        }

        int currentIndex = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].isEmpty()) {
                continue;
            }
            builder.setSpan(new ForegroundColorSpan(colors[i]), currentIndex, currentIndex + strs[i].length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            currentIndex += strs[i].length();
        }
        return builder;
    }




}
