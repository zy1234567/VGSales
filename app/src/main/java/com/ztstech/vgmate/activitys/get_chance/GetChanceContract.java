package com.ztstech.vgmate.activitys.get_chance;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/25.
 */

interface GetChanceContract {

    interface View extends BaseView {

        void onAddCommunicateFinish(@Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        void refreshData(String comid);

        void loadData(String comid);

        /**
         * 增加沟通记录
         */
        void addCommunicate(String rbiid, String msg);
    }
}
