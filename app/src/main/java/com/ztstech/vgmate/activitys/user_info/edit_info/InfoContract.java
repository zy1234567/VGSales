package com.ztstech.vgmate.activitys.user_info.edit_info;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
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
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 加载用户数据
         */
        void loadUserModule();


        /**
         * 保存信息
         * @param model
         */
        void saveInfo(FillInfoModel model);
    }
}
