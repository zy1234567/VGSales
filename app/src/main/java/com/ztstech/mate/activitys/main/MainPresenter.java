package com.ztstech.mate.activitys.main;

import com.ztstech.mate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class MainPresenter extends PresenterImpl<MainContract.View>
        implements MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        super(view);
    }
}
