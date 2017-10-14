package com.ztstech.vgmate.activitys.org_list.fragments.item;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/9/23.
 */

public interface OrglistItemContract {

    interface View extends BaseView {

        /**
         * 加载结束
         * @param items
         * @param errmsg
         */
        void onLoadMoreFinish(List<GetOrgListItemsBean.ListBean> items, String errmsg);


        /**
         * 刷新结束
         * @param items
         * @param errmsg
         */
        void onRefreshFinish(List<GetOrgListItemsBean.ListBean> items, String errmsg);


    }

    interface Presenter extends BasePresenter<View> {


        /**
         * 刷新界面
         */
        void refreshList(String locationId, String status);

        /**
         * 上拉加载更多
         */
        void appendList(String locationId, String status);

    }
}
