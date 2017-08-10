package com.ztstech.vgmate.activitys;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public interface BasePresenter<V extends BaseView> {

    void attach(V view);
}
