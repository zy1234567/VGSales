package com.ztstech.vgmate.activitys.sell_chance;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/10/11.
 */

interface SellChanceContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 加载标题数字
         */
        void loadTitleCount();
    }
}
