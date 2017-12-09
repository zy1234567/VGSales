package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QuestionApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.QuestionListBean;

import io.reactivex.Observable;

/**
 * Created by smm on 2017/11/22.
 */

public class GetQuestionList implements UserCase<Observable<QuestionListBean>>{

    private QuestionApi api;
    /** 问题描述 */
    private String keyword;
    /** 问题补充 */
    private int page;

    private String myquestion;

    public GetQuestionList(String keyword,int page,String myquestion){
        this.keyword = keyword;
        this.page = page;
        this.myquestion = myquestion;
        api = RetrofitUtils.createService(QuestionApi.class);
    }
    @Override
    public Observable<QuestionListBean> run() {
        return api.getQuestionList(keyword,myquestion,page,UserRepository.getInstance().getAuthId());
    }
}
