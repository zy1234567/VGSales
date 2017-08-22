package com.ztstech.vgmate.activitys.login;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/22.
 */

interface LoginContract {

    interface View extends BaseView {

        /**
         * 发送验证码完成
         * @param errorMessage 错误信息，为null表示没有错误
         */
        void sendCodeFinish(@Nullable String errorMessage);

        /**
         * 登录完成
         * @param errorMessage 错误信息，为null表示登录成功
         */
        void loginFinish(@Nullable String errorMessage);

        /**
         * 更新验证码获取时间
         * @param second
         */
        void updateSeconds(int second);
    }

    interface Presenter extends BasePresenter<View> {

        void sendCode(String phone);

        void login(String phone, String code);
    }
}
