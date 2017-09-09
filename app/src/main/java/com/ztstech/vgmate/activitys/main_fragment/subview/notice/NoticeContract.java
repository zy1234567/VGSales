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

        void setData(MainListBean data);

        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {

        void loadData();

        void appendData();
    }
}
