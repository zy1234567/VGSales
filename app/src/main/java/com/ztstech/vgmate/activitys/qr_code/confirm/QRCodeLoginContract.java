package com.ztstech.vgmate.activitys.qr_code.confirm;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/11/2.
 */

interface QRCodeLoginContract {

    interface View extends BaseView {

        /**
         * 登录结束
         * @param errmsg
         */
        void loginFinish(@Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 登录网页
         * @param uuid
         */
        void login(String uuid);
    }
}
