package com.ztstech.vgmate.activitys.add_certification.add_phone_fragment;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.activitys.add_certification.add_home_fragment.HomeContract;

/**
 * Created by Administrator on 2018/4/20.
 */

public interface PhoneContract {
    interface View extends BaseView {

    }
    interface Presenter extends BasePresenter<PhoneContract.View> {
        void commite();
    }
}
