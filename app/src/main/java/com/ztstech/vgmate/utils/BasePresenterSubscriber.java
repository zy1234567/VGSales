package com.ztstech.vgmate.utils;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.base.BaseApplication;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.events.LogoutEvent;
import com.ztstech.vgmate.data.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhiyuan on 2017/8/21.
 * presenter执行userCase类
 */

public abstract class BasePresenterSubscriber<E> extends Subscriber<E> {

    /**
     * 获取view引用
     */
    private BaseView mView;

    private Subscription subscription;

    private Handler handler = new Handler();

    /**
     * 显示进度条
     */
    private boolean showLoading = true;

    /**
     * 默认构造器，显示loading
     * @param view
     */
    public BasePresenterSubscriber(@NonNull BaseView view) {
        this(view, true);
    }

    /**
     * 自定义是否显示loading
     * @param view
     * @param showLoading
     */
    public BasePresenterSubscriber(@NonNull BaseView view, boolean showLoading) {
        this.showLoading = showLoading;
        this.mView = view;
        if (this.mView == null) {
            throw new NullPointerException("参数不能为空");
        }
    }


    @Override
    public final void onNext(E e) {
        if (mView != null && !mView.isViewFinish()) {
            if (showLoading) {
                mView.hideLoading(null);
            }
            int status = ((BaseRespBean) e).status;
            if (status == NetConstants.STATUS_IDENTIFY_OUT_DATE){
                // 如果是身份过期强行登出
                Intent it = new Intent(BaseApplicationLike.getApplicationInstance(),
                        LoginActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                it.putExtra(LoginActivity.ARG_MESSAGE, ((BaseRespBean) e).errmsg);
                BaseApplicationLike.getApplicationInstance().startActivity(it);
                UserRepository.getInstance().clearUserInfo();
            }else {
                childNext(e);
            }
        }
    }

    @Override
    public void onCompleted() {
        try {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (mView != null && !mView.isViewFinish()) {
                        if (showLoading) {
                            mView.hideLoading(null);
                        }
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
        if (showLoading) {
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

    protected abstract void childNext(E e);

    protected void childError(Throwable e) {}

}