package com.ztstech.vgmate.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/20.
 */

public class MaxTextLengthFilter implements InputFilter {

    private int mMaxLength;
    Toast toast;
    @SuppressLint("WrongConstant")
    public MaxTextLengthFilter(Context context, int max){
        mMaxLength = max;
        toast = Toast.makeText(context, "字符不能超过"+max+"个", 1000);
        toast.setGravity(Gravity.TOP, 0, 235);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart , int dend){
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if(keep < (end - start)){
            toast.show();
        }
        if(keep <= 0){
            return "";
        }else if(keep >= end - start){
            return null;
        }else{
            return source.subSequence(start,start + keep);
        }
    }
}