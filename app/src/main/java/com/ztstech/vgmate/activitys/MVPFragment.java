package com.ztstech.vgmate.activitys;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.ztstech.vgmate.base.BaseFragment;


/**
 * Created by zhiyuan on 2017/7/27.
 */

public abstract class MVPFragment<P extends BasePresenter> extends BaseFragment implements BaseView{

    protected P mPresenter;
    private ViewImpl<P> mvpView;

    @CallSuper
    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = initPresenter();
        }else {
            mPresenter.attach(this);
        }
        mvpView = new ViewImpl<>(getActivity());
        mvpView.setPresenter(mPresenter);
    }

    public void showLoading(String message) {
        mvpView.showLoading(message);
    }

    public void hideLoading(@Nullable String errorMessage) {
        mvpView.hideLoading(errorMessage);
    }

    public boolean isViewFinish() {
        return getActivity() == null || getActivity().isFinishing();
    }

    protected abstract P initPresenter();
}