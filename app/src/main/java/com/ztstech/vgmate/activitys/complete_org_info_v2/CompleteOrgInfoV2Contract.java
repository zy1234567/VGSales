package com.ztstech.vgmate.activitys.complete_org_info_v2;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.OrgInfoBean;

/**
 * Created by zhiyuan on 2017/10/9.
 */

interface CompleteOrgInfoV2Contract {

    interface View extends BaseView {

        void showOrgInfo(OrgInfoBean bean);

        void onLoadOrgInfoError(String errmsg);

    }

    interface Presenter extends BasePresenter<View> {

        void loadOrgInfo(int rbiid);

    }
}
