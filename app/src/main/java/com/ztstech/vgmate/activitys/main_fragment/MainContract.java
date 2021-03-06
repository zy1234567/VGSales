package com.ztstech.vgmate.activitys.main_fragment;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface MainContract {

    interface View extends BaseView {


        void loadError(String errorMessage);

        void setData(MainPageBean mainPageBean);

        void setUserInfo(UserBean userBean);
    }

    interface Presenter extends BasePresenter<MainContract.View> {

        /**
         * 加载数据
         */
        void loadData();

        void loadUserInfo();


    }
}
