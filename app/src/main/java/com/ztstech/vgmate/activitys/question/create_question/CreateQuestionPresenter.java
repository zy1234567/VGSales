package com.ztstech.vgmate.activitys.question.create_question;

import com.ztstech.appdomain.user_case.CreateQuestion;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by smm on 2017/11/22.
 */

public class CreateQuestionPresenter extends PresenterImpl<CreateQuestionContact.View> implements CreateQuestionContact.Presenter{


    public CreateQuestionPresenter(CreateQuestionContact.View view) {
        super(view);
    }

    @Override
    public void submit(String descrption, String supplement) {
        new BasePresenterSubscriber<BaseRespBean>(mView){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.onSubmitSucceed();
                }else {
                    mView.onSubmitFailed(baseRespBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.onSubmitFailed(e.getMessage());
            }
        }.run(new CreateQuestion(descrption,supplement).run());
    }
}
