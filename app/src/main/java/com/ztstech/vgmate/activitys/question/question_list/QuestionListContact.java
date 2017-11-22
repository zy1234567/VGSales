package com.ztstech.vgmate.activitys.question.question_list;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.QuestionListBean;

import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/16
 */

public interface QuestionListContact {

    interface View extends BaseView {

        void setData(List<QuestionListBean.ListBean> listData);

        void showError(String errorMessage);

        /**
         * 设置为没有更多数据
         * @param noreMoreData
         */
        void setNoreMoreData(boolean noreMoreData);

        void onDeleteSucceed();
    }

    interface Presenter extends BasePresenter<View> {
        void loadData(String keyword,boolean myflg);

        void appendData();

        void deleteQuestion(String qid);
    }

}
