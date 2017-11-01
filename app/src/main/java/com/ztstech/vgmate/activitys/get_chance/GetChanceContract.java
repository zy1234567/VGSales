package com.ztstech.vgmate.activitys.get_chance;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/8/25.
 */

interface GetChanceContract {

    interface View extends BaseView {

        void onAddCommunicateFinish(@Nullable String errmsg);

        void onRefreshFinish(List<CommunicationHistoryBean.ListBean> items, @Nullable String errmsg);

        void onLoadFinish(List<CommunicationHistoryBean.ListBean> items, @Nullable String errmsg);

    }

    interface Presenter extends BasePresenter<View> {

        void refreshData(String comid, String rbiid);

        void loadData(String comid, String rbiid);

        /**
         * 增加沟通记录
         */
        void addCommunicateByRbiid(String rbiid, String msg);

        /**
         * 根据comid增加沟通记录
         * @param status
         * @param orgin
         * @param comid
         * @param msg
         */
        void addCommunicateByComid(String status, String orgin, String comid, String msg);
    }
}
