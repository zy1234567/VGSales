package com.ztstech.vgmate.activitys.complete_org_info_v2;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.OrgInfoBean;

/**
 * Created by zhiyuan on 2017/10/9.
 */

interface CompleteOrgInfoV2Contract {

    interface View extends BaseView {

        void showOrgInfo(OrgInfoBean.InfoBean bean);

        void onLoadOrgInfoError(String errmsg);

        void editOrgInfoFinish(@Nullable String errmsg);

    }

    interface Presenter extends BasePresenter<View> {

        void loadOrgInfo(int rbiid);

        /**
         * 编辑机构资料
         * @param bean
         */
        void editOrgInfo(OrgInfoBean.InfoBean bean);

    }
}
