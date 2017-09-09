package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.model.information.InformationModel;

import java.util.List;

/**
 * Created by zhiyuan on 2017/8/11.
 */

interface InformationContract {

    interface View extends BaseView {

        /**
         * 设置列表数据
         */
        void setListData(List<MainListBean.ListBean> listData);

        /**
         * 显示错误
         * @param errorMessage
         */
        void showError(String errorMessage);

        /**
         * 显示没有更多数据
         */
        void showNomoreData(boolean nomore);
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
