package com.ztstech.vgsales.activitys;

import android.support.annotation.Nullable;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public interface BaseView {

    void showLoading(String message);

    void hideLoading(@Nullable String errorMessage);

    boolean isViewFinish();

}
