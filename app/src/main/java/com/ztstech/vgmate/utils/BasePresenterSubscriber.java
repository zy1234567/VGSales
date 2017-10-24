package com.ztstech.vgmate.utils;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.utils.LogUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhiyuan on 2017/8/21.
 */

public abstract class BasePresenterSubscriber<E> extends Subscriber<E> {

    private BaseView mView;

    private Subscription subscription;

    private Handler handler = new Handler();

    public BasePresenterSubscriber(@NonNull BaseView view) {
        this.mView = view;
        if (this.mView == null) {
            throw new NullPointerException("参数不能为空");
        }
    }


    @Override
    public final void onNext(E e) {
        if (mView != null && !mView.isViewFinish()) {
            mView.hideLoading(null);
            childNext(e);
        }
    }

    @Override
    public void onCompleted() {
        try {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (mView != null && !mView.isViewFinish()) {
                        mView.hideLoading(null);
                    }else {
                        if (mView == null) {
                            LogUtils.log("onCompleted mView为空");
                        }
                    }
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public final void onError(final Throwable e) {
        try {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (mView != null && !mView.isViewFinish()) {
                        mView.hideLoading(e.getLocalizedMessage());
                        childError(e);
                    }else {
                        if (mView == null) {
                            LogUtils.log("onError mView为空");
                        }
                    }
                }
            });
        }catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void run(Observable<E> observable) {
        if (mView.isViewFinish()) {
            return;
        }
        mView.showLoading(null);
        subscription = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this);
    }

    public void cancel() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        mView = null;
    }

    protected abstract void childNext(E e);

    protected void childError(Throwable e) {}

}