package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QuestionApi;
import com.ztstech.vgmate.data.beans.AnwserListBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/22.
 */

public class GetAnwserList implements UserCase<Observable<AnwserListBean>> {

    private String qid;
    private int page;

    private QuestionApi api;

    public GetAnwserList(String qid,int page){
        api = RetrofitUtils.createService(QuestionApi.class);
        this.qid = qid;
        this.page = page;
    }

    @Override
    public Observable<AnwserListBean> run() {
        return api.getQuestionAnwserList(qid,page, UserRepository.getInstance().getAuthId());
    }
}
