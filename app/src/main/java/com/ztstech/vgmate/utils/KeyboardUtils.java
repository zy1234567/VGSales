package com.ztstech.vgmate.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ztstech.vgmate.activitys.article_detail.ArticleDetailActivity;

/**
 * Created by zhiyuan on 2017/10/22.
 */

public class KeyboardUtils {

    public static void showKeyBoard(Context context, EditText editText) {
        ((InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 0);
    }

    public static void hideKeyBoard(Context context, EditText editText) {
        ((InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
