package com.ztstech.vgmate.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseApplication;
import com.ztstech.vgmate.base.BaseApplicationLike;

/**
 * Created by zhiyuan on 2017/7/28.
 * view相关工具
 */

public class ViewUtils {

    /**
     * 获得屏幕宽度
     */
    @SuppressWarnings("deprecation")
    public static int getPhoneWidth(Context ctx){
        if (ctx == null){
            return 0;
        }
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
    // 获得屏幕宽度
    public static int getScreenWidth(Context context) {
        if (context == null){
            return 0;
        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    @SuppressWarnings("deprecation")
    public static int getPhoneHeight(Context ctx){
        if (ctx == null){
            return 0;
        }
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 获得屏幕高宽比
     */
    public static double getPhoneScale(Context context){
        return getPhoneHeight(context) / getPhoneWidth(context);
    }

    /**
     * 计算四张图片时的宽度
     * @return
     */
    public static int messureFourImgWidth(Context ctx){
        int phoneWidth = getPhoneWidth(ctx);
        int oneImgWidth = (phoneWidth - dp2px(ctx,84))/3;
        int width = phoneWidth - dp2px(ctx,79) - oneImgWidth;
        return width;
    }

    /**
     * 计算不是四张图片时的宽度
     * @return
     */
    public static int messureNoFourImgWidth(Context ctx){
        int phoneWidth = getPhoneWidth(ctx);
        int width = phoneWidth - dp2px(ctx,84);
        return width;
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpValue) {
        return dp2px(BaseApplicationLike.getApplicationInstance(), dpValue);
    }

    /**
     * px转dp
     */
    public static int px2dp(float pxValue) {
        return px2dp(BaseApplicationLike.getApplicationInstance(), pxValue);
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
            if (strs[i] == null || strs[i].isEmpty()) {
                continue;
            }
            builder.setSpan(new ForegroundColorSpan(colors[i]), currentIndex, currentIndex + strs[i].length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            currentIndex += strs[i].length();
        }
        return builder;
    }

    /**
     * 获取不同样式span
     * @param builder 为空新建，不为空在现有基础上添加
     * @param strs 分段字符串
     * @param styles 与分段字符串对应的样式
     * @return
     */
    public static SpannableStringBuilder getDiffStyleSpan(@Nullable SpannableStringBuilder builder,
                                                          @NonNull String[] strs,@NonNull int[] styles) {
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

            builder.setSpan(new StyleSpan(styles[i]), currentIndex, currentIndex + strs[i].length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            currentIndex += strs[i].length();
        }
        return builder;
    }


    /**
     * 获取不同点击事件的span
     * @param builder 为空新建，不为空在现有基础上添加
     * @param strs 分段字符串
     * @param clicks 不同点击响应
     * @return
     */
    public static SpannableStringBuilder getDiffClickSpan(@Nullable SpannableStringBuilder builder,
                                                          @NonNull String[] strs,@NonNull ClickableSpan[] clicks) {
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
            builder.setSpan(clicks[i], currentIndex, currentIndex + strs[i].length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            currentIndex += strs[i].length();
        }
        return builder;
    }


    /**
     * 没有下划线的点击span
     */
    public static abstract class NoLineClickSpan extends ClickableSpan {


        public NoLineClickSpan() {
            super();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false); //去掉下划线
        }

    }

    /**
     * 自动匹配搜索的关键字并标红
     */
    public static void setKeyWordLight(String keyword, String allText, TextView tvContent){
        if (allText != null && allText.contains(keyword)) {
            int index = allText.indexOf(keyword);
            int len = keyword.length();
            Spanned temp = Html.fromHtml(allText.substring(0, index)
                    + "<font color=#FF4443>"
                    + allText.substring(index, index + len) + "</font>"
                    + allText.substring(index + len, allText.length()));

            tvContent.setText(temp);
        } else {
            tvContent.setText(allText);
        }
    }

    //设置单图尺寸
    public static String setSingleImageSize(Context ctx,String imgs,ImageView iv){
        String[] str = null;
        String[] wh  = null;
        String url = "",width = "",height = "";
        int w = 0,h = 0;
        try {
            str = imgs.split("!@");
            url = str[0];//截取图片地址
            wh = str[1].split(":;");
            width = wh[0];//截取图片宽
            height = wh[1];//截取图片高
            w = Integer.parseInt(width);
            h = Integer.parseInt(height);
        } catch (Exception e) {
        }
        //要设的高宽固定值:屏幕宽度的一半+50
        int length = (int) (getPhoneWidth(ctx) * 0.5 + 50);
        if(iv == null){
            return "";
        }
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) iv.getLayoutParams();
        if (w > h) {  //宽大于高，宽度设为固定值。
            float scale = (float)w/(float)h;
            if (scale<3) { //长图不做处理
                linearParams.width = length;
                linearParams.height = (int) (((float)length) / scale);
            }
        }else if(w < h) {  //高大于宽，高度设为固定值
            float scale = (float)h/(float)w;
            linearParams.height = length;
            linearParams.width = (int) ((float)length / scale);
        }else if(w!=0 && w == h){  //正方形图片高宽都设为最大值
            linearParams.width = length;
            linearParams.height = length;
        }
        if (w!=0 && h!=0 && (h/w)>5) { //扩大长图点击区域
            iv.setPadding(0, 0, ctx.getResources().getDimensionPixelSize(R.dimen.job_space_share_img_w), 0);
        }
        iv.setLayoutParams(linearParams);
        return url;
    }

}
