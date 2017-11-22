package com.ztstech.vgmate.activitys.question.question_list;

import com.ztstech.appdomain.user_case.DeleteQuestion;
import com.ztstech.appdomain.user_case.GetQuestionList;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.QuestionListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/22
 */

public class QuestionListPresenter extends PresenterImpl<QuestionListContact.View> implements QuestionListContact.Presenter{

    private int currentpage = 1;

    private String keyword;

    /** 是否是查询我的问题 */
    private boolean myflg;

    private List<QuestionListBean.ListBean> listBeen;


    public QuestionListPresenter(QuestionListContact.View view) {
        super(view);
        listBeen = new ArrayList<>();
    }

    /**
     * 查询问题列表(所有问题，我的问题，搜索问题都是用的这个)
     * @param keyword 是查询搜索列表需要传关键词 不是搜索列表keyword传空即可
     * @param myflg 是否是查需那我的列表
     */
    @Override
    public void loadData(String keyword,boolean myflg) {
        currentpage = 1;
        this.keyword = keyword;
        this.myflg = myflg;
        new BasePresenterSubscriber<QuestionListBean>(mView,false){

            @Override
            protected void childNext(QuestionListBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    if (currentpage == 1){
                        listBeen.clear();
                    }
                    listBeen.addAll(baseRespBean.list);
                    mView.setData(listBeen);
                    if (baseRespBean.pager.totalPages == currentpage){
                        mView.setNoreMoreData(true);
                    }
                }else {
                    mView.showError(baseRespBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                 mView.showError(e.getMessage());
            }
        }.run(new GetQuestionList(keyword,currentpage,myflg ? "01" :"").run());
    }

    @Override
    public void appendData() {
        currentpage ++;
        loadData(keyword,myflg);
    }

    @Override
    public void deleteQuestion(String qid) {
        new BasePresenterSubscriber<BaseRespBean>(mView){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.onDeleteSucceed();
                }else {
                    mView.showError(baseRespBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError(e.getMessage());
            }
        }.run(new DeleteQuestion(qid).run());
    }
}
