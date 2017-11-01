package com.ztstech.vgmate.activitys.main_fragment.subview.notice;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.model.notice.NoticeModel;

import java.util.List;

/**
 * Created by zhiyuan on 2017/8/15.
 */

interface NoticeContract {

    interface View extends BaseView {

        void setData(List<MainListBean.ListBean> listData);

        void showError(String errorMessage);

        /**
         * 设置为没有更多数据
         * @param noreMoreData
         */
        void setNoreMoreData(boolean noreMoreData);
    }

    interface Presenter extends BasePresenter<View> {

        void loadData();

        void appendData();
    }
}
