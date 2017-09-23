package com.ztstech.vgmate.activitys.org_list;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.GetOrgListCountBean;

/**
 * Created by zhiyuan on 2017/9/8.
 * 机构名录
 */

interface OrgListContract {

    interface View extends BaseView {

        /**
         * 加载结束
         * @param bean
         * @param errmsg
         */
        void onLoadCountFinish(GetOrgListCountBean bean, String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 加载数量信息
         */
        void loadCount(String locId);
    }
}
