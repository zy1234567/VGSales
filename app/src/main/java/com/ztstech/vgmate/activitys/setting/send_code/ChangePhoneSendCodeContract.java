package com.ztstech.vgmate.activitys.setting.send_code;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/10/28.
 */

interface ChangePhoneSendCodeContract {

    interface View extends BaseView {

        void onSendCodeFinish(@Nullable String errmsg);

        void onCheckCodeFinish(@Nullable String errmsg, String code);
    }

    interface Presenter extends BasePresenter<View> {

        void sendCode(String phone);

        void checkCode(String phone, String code);
    }
}
