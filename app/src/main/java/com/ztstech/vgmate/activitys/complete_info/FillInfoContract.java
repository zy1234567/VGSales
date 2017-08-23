package com.ztstech.vgmate.activitys.complete_info;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;

/**
 * Created by zhiyuan on 2017/8/17.
 */

interface FillInfoContract {

    interface View extends BaseView {

        void onSubmitSucceed();
    }

    interface Presenter extends BasePresenter<View> {


        void saveInfo(FillInfoModel model);

        /**
         * 资料是否已经完成
         */
        boolean isInfoFilled();
    }

}
