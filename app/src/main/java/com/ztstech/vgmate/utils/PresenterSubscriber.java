package com.ztstech.vgmate.utils;


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

    public PresenterSubscriber(BaseView view) {
        this.mView = view;
    }

    public PresenterSubscriber() {}

    @Override
    public final void onNext(E e) {
        if (mView != null && !mView.isViewFinish()) {
            next(e);
        }
    }

    @Override
    public void onCompleted() {
        if (mView == null) {
            return;
        }
        if (!mView.isViewFinish()) {
            mView.hideLoading(null);
        }
        mView = null;
    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (!mView.isViewFinish()) {
            mView.hideLoading(e.getLocalizedMessage());
        }
        mView = null;
    }

    public void run(Observable<E> observable) {
        if (mView != null) {
            mView.showLoading(null);
        }
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

    protected abstract void next(E e);

}