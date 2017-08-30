package com.ztstech.vgmate.activitys.login;

import android.os.Handler;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.lang.ref.WeakReference;

/**
 * Created by zhiyuan on 2017/8/22.
 */

public class LoginPresenter extends PresenterImpl<LoginContract.View> implements
        LoginContract.Presenter{

    private UserRepository userRepository;

    private static CountDown mCountDown;


    public LoginPresenter(LoginContract.View view) {
        super(view);
        userRepository = UserRepository.getInstance();
        if (mCountDown == null) {
            mCountDown = new CountDown();
        }
        mCountDown.setView(view);
    }

    @Override
    public void sendCode(String phone) {
        mView.showLoading(null);
        new PresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void onNext(BaseRespBean baseRespBean) {
                mView.hideLoading(null);
                if (baseRespBean.messageCode == 0) {
                    mView.sendCodeFinish(null);
                    if (baseRespBean.isSucceed()) {
                        mCountDown.start();
                    }
                } else if (baseRespBean.messageCode == 1) {
                    mView.sendCodeFinish("验证码一小时内发送超过5条，请稍后再试");
                } else if (baseRespBean.messageCode == 2) {
                    mView.sendCodeFinish("验证码一天内发送超过20条，请稍后再试");
                } else if (baseRespBean.messageCode == 3) {
                    mView.sendCodeFinish("无此号码");
                }else {
                    mView.sendCodeFinish("未知错误");
                }
            }
        }.run(userRepository.sendLoginCode(phone));
    }

    @Override
    public void login(String phone, String code) {
        mView.showLoading(null);
        new PresenterSubscriber<UserBean>() {

            @Override
            public void onNext(UserBean baseRespBean) {
                mView.hideLoading(null);
                if (baseRespBean.isSucceed()) {
                    mView.loginFinish(null);
                }else {
                    mView.loginFinish(baseRespBean.getErrmsg());
                }
            }
        }.run(userRepository.login(phone, code));
    }


    /**
     * 倒计时控制器
     */
    private static class CountDown implements Runnable {

        private Handler handler;

        private int seconds = 60;

        private WeakReference<LoginContract.View> viewRef;

        private boolean isCounting;

        public CountDown() {
            handler = new Handler();
        }

        public void start() {
            isCounting = true;
            handler.postDelayed(this, 1000);
        }

        public void setView(LoginContract.View view) {
            this.viewRef = new WeakReference<LoginContract.View>(view);
        }

        @Override
        public void run() {
            seconds--;
            if (seconds >= 0) {
                handler.postDelayed(this, 1000);
                if (this.viewRef.get() != null) {
                    this.viewRef.get().updateSeconds(seconds);
                }
            }else {
                seconds = 60;
            }

        }
    }
}
