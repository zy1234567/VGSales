package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QuestionApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/22.
 * 创建问题回复
 */

public class CreateAnwser implements UserCase<Observable<BaseRespBean>>{

    private String qid;
    private String uid;
    private String content;

    private QuestionApi api;

    public CreateAnwser(String qid,String uid,String content){
        this.qid = qid;
        this.content = content;
        this.uid = uid;
        api = RetrofitUtils.createService(QuestionApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.createAnswer(qid,uid,content, UserRepository.getInstance().getAuthId());
    }
}
