package com.ztstech.vgmate.activitys.article_detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.MainListBean;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public interface ArticleDetailContract {

    interface View extends BaseView {

        /**
         * 评论结束
         * @param errmsg
         */
        void onCommentFinish(@Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 评论
         * @param comment
         * @param data
         */
        void comment(String flid, String newid, String touid, String comment,String flg);
    }
}
