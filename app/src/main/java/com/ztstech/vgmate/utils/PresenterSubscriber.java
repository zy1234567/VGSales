package com.ztstech.vgmate.utils;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.ztstech.vgmate.activitys.BaseView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhiyuan on 2017/8/21.
 */

public abstract class PresenterSubscriber<E> extends Subscriber<E> {

    private BaseView mView;

    private Subscription subscription;

    private Handler handler = new Handler();

    public PresenterSubscriber(@NonNull BaseView view) {
        this.mView = view;
        if (this.mView == null) {
            throw new NullPointerException("参数不能为空");
        }
    }


    @Override
    public final void onNext(E e) {
        if (mView != null && !mView.isViewFinish()) {
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
                    }
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            mView = null;
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
                    }
                }
            });
        }catch (Exception e1) {
            e1.printStackTrace();
        }finally {
            mView = null;
        }
    }

    public void run(Observable<E> observable) {
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

    protected void childError(Throwable e) {};

}