package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.model.information.InformationModel;

import java.util.List;

/**
 * Created by zhiyuan on 2017/8/11.
 */

interface InformationContract {

    interface View extends BaseView {

        void setListData(List<InformationModel> informationModels);
    }

    interface Presenter extends BasePresenter<InformationContract.View> {

        /**
         * 加载列表数据，即下拉刷新
         */
        void loadListData();

        /**
         * 添加数据
         */
        void appendData();
    }

}
