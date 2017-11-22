package com.ztstech.vgmate.activitys.question.question_detail;

import com.ztstech.appdomain.user_case.CreateAnwser;
import com.ztstech.appdomain.user_case.GetAnwserList;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.AnwserListBean;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smm on 2017/11/22.
 */

public class QuestionDetailPresenter extends PresenterImpl<QuestionDetailContact.View> implements QuestionDetailContact.Presenter{

    private int currentpage;

    private List<AnwserListBean.ListBean> listBeen;

    public QuestionDetailPresenter(QuestionDetailContact.View view) {
        super(view);
        listBeen = new ArrayList<>();
    }

    @Override
    public void loadListData() {
        currentpage = 1;
        new BasePresenterSubscriber<AnwserListBean>(mView){

            @Override
            protected void childNext(AnwserListBean anwserListBean) {
                if (anwserListBean.isSucceed()){
                    if (currentpage == 1){
                        listBeen.clear();
                    }
                    listBeen.addAll(anwserListBean.list);
                    mView.setListData(listBeen);
                }else {
                    mView.showError(anwserListBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError(e.getMessage());
            }
        }.run(new GetAnwserList(mView.getqid(),currentpage).run());
    }

    @Override
    public void appendData() {

    }

    @Override
    public void reply() {
        new BasePresenterSubscriber<BaseRespBean>(mView){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.onReplySuccess();
                }else {
                    mView.showError("回复问题出错：".concat(baseRespBean.errmsg));
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("回复问题出错：".concat(e.getMessage()));
            }
        }.run(new CreateAnwser(mView.getqid(),mView.getQuid(),mView.getContent()).run());
    }

    @Override
    public void deleteAnwser() {

    }
}
