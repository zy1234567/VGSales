package com.ztstech.vgmate.activitys.complete_info;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/17.
 */

interface FillInfoContract {

    interface View extends BaseView {

        void onSubmitSucceed();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 上传图片
         * @param path
         */
        void uploadImage(String path);

    }

}
