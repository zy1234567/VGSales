package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QuestionApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/23.
 * 删除答案
 */

public class DeleteAnwser implements UserCase<Observable<BaseRespBean>>{

    private QuestionApi api;

    private String ansid;

    public DeleteAnwser(String ansid){
        this.ansid = ansid;
        api = RetrofitUtils.createService(QuestionApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.deleteAnswer(ansid, UserRepository.getInstance().getAuthId());
    }
}
