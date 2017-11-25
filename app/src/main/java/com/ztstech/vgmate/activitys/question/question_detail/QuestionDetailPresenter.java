package com.ztstech.vgmate.activitys.question.question_detail;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ztstech.appdomain.user_case.CreateAnwser;
import com.ztstech.appdomain.user_case.DeleteAnwser;
import com.ztstech.appdomain.user_case.GetAnwserList;
import com.ztstech.appdomain.user_case.PriseAnwser;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.activitys.question.adapter.AnwserViewHolder;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.AnwserListBean;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/22
 */

public class QuestionDetailPresenter extends PresenterImpl<QuestionDetailContact.View> implements QuestionDetailContact.Presenter{

    private int currentpage;

    private List<AnwserListBean.ListBean> listBeen;

    private boolean isRefresh;

    public QuestionDetailPresenter(QuestionDetailContact.View view) {
        super(view);
        listBeen = new ArrayList<>();
    }

    @Override
    public void loadListData() {
        if (isRefresh){
            return;
        }
        currentpage = 1;
        isRefresh = true;
        new BasePresenterSubscriber<AnwserListBean>(mView){

            @Override
            protected void childNext(AnwserListBean anwserListBean) {
                isRefresh = false;
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
                isRefresh = false;
                mView.showError(e.getMessage());
            }
        }.run(new GetAnwserList(mView.getqid(),currentpage).run());
    }

    @Override
    public void appendData() {
        if (isRefresh){
            return;
        }
        currentpage ++;
        isRefresh = true;
        new BasePresenterSubscriber<AnwserListBean>(mView,false){

            @Override
            protected void childNext(AnwserListBean anwserListBean) {
                isRefresh = false;
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
                isRefresh = false;
                mView.showError(e.getMessage());
            }
        }.run(new GetAnwserList(mView.getqid(),currentpage).run());
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
    public void deleteAnwser(String ansid) {
        new BasePresenterSubscriber<BaseRespBean>(mView){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.onDeleteSuccess();
                }else {
                    mView.showError("删除回复出错：".concat(baseRespBean.errmsg));
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("删除回复出错：".concat(e.getMessage()));
            }
        }.run(new DeleteAnwser(ansid).run());
    }

    @Override
    public void priseAnwser(AnwserListBean.ListBean data) {
        String status = AnwserViewHolder.STATUS_PRISE.equals(data.likeStatus) ? "00" : "01";
        new BasePresenterSubscriber<BaseRespBean>(mView,false){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){

                }else {
                    mView.showError(baseRespBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("点赞出错：".concat(e.getMessage()));
            }
        }.run(new PriseAnwser(status,data.ansid,data.ansPublishUid).run());
    }


}
