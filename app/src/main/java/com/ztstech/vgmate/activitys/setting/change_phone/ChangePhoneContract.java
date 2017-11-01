package com.ztstech.vgmate.activitys.setting.change_phone;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/10/28.
 */

interface ChangePhoneContract {

    interface View extends BaseView {

        void onSubmitFinish(@Nullable String errmsg, String phone);

    }

    interface Presenter extends BasePresenter<View> {

        void submit(String code, String phone, String newPhone);

    }
}
