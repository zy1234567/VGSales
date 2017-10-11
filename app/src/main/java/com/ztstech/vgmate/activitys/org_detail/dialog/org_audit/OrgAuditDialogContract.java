package com.ztstech.vgmate.activitys.org_detail.dialog.org_audit;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.data.beans.RepeatOrgBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/9/25.
 * 认证对话框
 */

interface OrgAuditDialogContract {

    interface View extends BaseView {

        void loadRepeatDataFinish(List<RepeatOrgBean.ListBean> bean, String errmsg);

        void appendFinish(List<RepeatOrgBean.ListBean> bean, String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        void loadRepeatData(GetOrgListItemsBean.ListBean bean);

        void appendData(GetOrgListItemsBean.ListBean bean);
    }
}
