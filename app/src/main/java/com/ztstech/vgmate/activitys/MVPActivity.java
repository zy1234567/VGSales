package com.ztstech.vgmate.activitys;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.ztstech.vgmate.base.BaseActivity;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public abstract class MVPActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    private ViewImpl<P> mvpView;

    @CallSuper
    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        mvpView = new ViewImpl<>(this);
        mvpView.setPresenter(mPresenter);
    }

    @Override
    protected void onDestroy() {
        mvpView.hideLoading(null);
        super.onDestroy();
    }

    public boolean isViewFinish() {
        return isFinishing();
    }

    protected abstract P initPresenter();

    public void showLoading(String message) {
        if (!isFinishing()) {
            mvpView.showLoading(message);
        }
    }

    public void hideLoading(@Nullable String errorMessage) {
        mvpView.hideLoading(errorMessage);
    }

}