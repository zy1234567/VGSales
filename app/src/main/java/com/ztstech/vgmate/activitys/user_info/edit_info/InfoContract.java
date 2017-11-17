package com.ztstech.vgmate.activitys.user_info.edit_info;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;

/**
 * Created by zhiyuan on 2017/9/8.
 */

interface InfoContract {

    interface View extends BaseView {

        void onSubmitSucceed();

        void onSubmitFailed(String message);

        /**
         * 设置用户数据
         */
        void setUserModule(FillInfoModel model);

        /**
         * 设置是否允许信息编辑
         * @param enabled
         */
        void setEditPrivateInfoEnabled(boolean enabled);

        void showError(String errmsg);

        void onApproveSucceed();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 加载用户本人数据
         */
        void loadUserModule();

        /**
         * 加载要审批的销售数据
         */
        void loadMateModule(UnApproveMateBean bean);

        /**
         * 保存信息
         * @param model
         */
        void saveInfo(FillInfoModel model);

        /**
         * 审批销售
         */
        void approveMate(String uid,String status);
    }
}
