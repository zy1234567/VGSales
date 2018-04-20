package com.ztstech.vgmate.activitys.add_certification.add_home_fragment;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by Administrator on 2018/4/20.
 */

public interface HomeContract {
    interface View extends BaseView {

    }
    interface Presenter extends BasePresenter<HomeContract.View> {
        void commite();
    }
}
