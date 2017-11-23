package com.ztstech.vgmate.activitys.question.question_detail;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.AnwserListBean;

import java.util.List;

/**
 * Created by smm on 2017/11/22.
 */

public class QuestionDetailContact {

    interface View extends BaseView{
        void setListData(List<AnwserListBean.ListBean> listData);

        void showError(String msg);

        String getqid();

        String getContent();

        String getQuid();

        void onReplySuccess();

        void onDeleteSuccess();
    }

    interface Presenter extends BasePresenter<View>{
        void loadListData();
        void appendData();
        void reply();
        void deleteAnwser(String ansid);
        void priseAnwser(AnwserListBean.ListBean data);
    }

}
