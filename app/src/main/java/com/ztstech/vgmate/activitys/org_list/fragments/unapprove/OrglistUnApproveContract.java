package com.ztstech.vgmate.activitys.org_list.fragments.unapprove;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.OrglistUnApproveBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/14.
 */

interface OrglistUnApproveContract {

    interface View extends BaseView {

        /**
         * 下拉刷新结束
         * @param items
         * @param errmsg
         */
        void onRefreshFinish(List<OrglistUnApproveBean.ListBean> items, @Nullable String errmsg);

        /**
         * 上拉加载结束
         * @param items
         * @param errmsg
         */
        void onLoadFinsh(List<OrglistUnApproveBean.ListBean> items, @Nullable String errmsg);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 下拉刷新
         */
        void refresh();

        /**
         * 上拉加载
         */
        void loadMore();

    }
}
