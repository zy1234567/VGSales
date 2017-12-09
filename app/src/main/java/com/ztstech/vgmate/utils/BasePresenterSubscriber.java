package com.ztstech.vgmate.utils;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author smm
 * @author zhiyuan
 * @date 2017/8/21
 * presenter执行userCase类
 */

public abstract class BasePresenterSubscriber<E> implements Observer<E> {

    /**
     * 获取view引用
     */
    private BaseView mView;


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
    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
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
    public void onComplete() {
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
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this);
    }


    protected abstract void childNext(E e);

    protected void childError(Throwable e) {}

}