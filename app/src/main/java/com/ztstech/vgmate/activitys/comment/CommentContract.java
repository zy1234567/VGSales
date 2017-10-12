package com.ztstech.vgmate.activitys.comment;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.CommentBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/12.
 */

interface CommentContract {

    interface View extends BaseView {

        /**
         * 刷新结束
         * @param listBeanList
         * @param error
         */
        void onLoadFinish(List<CommentBean.ListBean> listBeanList, @Nullable String error);

        /**
         * 加载结束
         * @param listBeanList
         * @param error
         */
        void onAppendFinish(List<CommentBean.ListBean> listBeanList, @Nullable String error);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 查询评论列表
         * @param newsid
         */
        void getCommentList(String newsid);

        /**
         * 上拉加载
         * @param newsid
         */
        void appendCommentList(String newsid);
    }
}
