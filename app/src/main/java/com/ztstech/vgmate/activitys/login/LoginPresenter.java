package com.ztstech.vgmate.activitys.login;

import android.os.Handler;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.manager.CountDown;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

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
            mCountDown = new CountDown(60);
        }
        mCountDown.setListener(new CountDown.CountdownListener() {
            @Override
            public void updateSeconds(int seconds) {
                if (!mView.isViewFinish()) {
                    mView.updateSeconds(seconds);
                }
            }
        });
    }

    @Override
    public void sendCode(String phone) {
        mView.showLoading(null);
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
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

            @Override
            protected void childError(Throwable e) {
                super.childError(e);
                mView.sendCodeFinish("网络链接错误，请稍后重试");
            }

        }.run(userRepository.sendLoginCode(phone));
    }

    @Override
    public void login(String phone, String code) {
        mView.showLoading(null);
        new BasePresenterSubscriber<UserBean>(mView) {

            @Override
            public void childNext(UserBean baseRespBean) {
                mView.hideLoading(null);
                if (baseRespBean.isSucceed()) {
                    mView.loginFinish(null);
                }else {
                    mView.loginFinish(baseRespBean.getErrmsg());
                }
            }

            @Override
            protected void childError(Throwable e) {
                super.childError(e);
                mView.sendCodeFinish("网络链接错误，请稍后重试");
            }

        }.run(userRepository.login(phone, code));
    }

    @Override
    public boolean isUserinfoCompleted() {
        return userRepository.isUserInfoCompleted();
    }

}
