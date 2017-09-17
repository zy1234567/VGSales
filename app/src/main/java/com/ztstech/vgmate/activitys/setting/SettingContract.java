package com.ztstech.vgmate.activitys.setting;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/9/9.
 */

interface SettingContract {

    interface View extends BaseView {

        void onLogoutFinish(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {

        void logout();

        String getPhone();
    }
}
