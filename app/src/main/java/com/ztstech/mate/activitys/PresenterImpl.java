package com.ztstech.mate.activitys;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public class PresenterImpl<V extends BaseView>  {

    protected V mView;

    public PresenterImpl(V view) {
        attach(view);
    }

    public void attach(V view) {
        this.mView = view;
    }

}
