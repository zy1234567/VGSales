package com.ztstech.vgmate.activitys.add_sell_mate;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.AddSellMateData;

/**
 * Created by zhiyuan on 2017/8/25.
 */

interface AddSellMateContract {

    interface View extends BaseView {

        /**
         * 提交数据
         * @param errorMessage
         */
        void onSubmitFinish(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 提交数据
         * @param addSellMateData
         */
        void submit(AddSellMateData addSellMateData);
    }

}
