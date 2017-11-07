package com.ztstech.vgmate.activitys.main_fragment.subview.notice;

import android.support.annotation.Nullable;

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

        void resendFinish(String errorMessage);

        /**
         * 设置为没有更多数据
         * @param noreMoreData
         */
        void setNoreMoreData(boolean noreMoreData);

        /**
         * 删除文章结束
         * @param errmsg
         */
        void deleteArticleFinish(@Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        void loadData();

        void appendData();

        /**
         * 删除文章
         * @param nid
         */
        void deleteNotice(String nid);

        /**
         * 重新发送
         */
        void resendArticle(MainListBean.ListBean bean);
    }
}
