package com.ztstech.vgmate.activitys.question.create_question;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by smm on 2017/11/22.
 */

public interface CreateQuestionContact {

    interface View extends BaseView{
        void onSubmitSucceed();
        void onSubmitFailed(String errmsg);
    }

    interface Presenter extends BasePresenter<View>{
        void submit(String descrption,String supplement);
    }

}
