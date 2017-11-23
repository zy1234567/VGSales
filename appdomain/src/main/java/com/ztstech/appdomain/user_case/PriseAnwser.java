package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QuestionApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/23.
 */

public class PriseAnwser implements UserCase<Observable<BaseRespBean>>{

    private String status;

    private String aid;

    private String uid;

    private QuestionApi api;

    public PriseAnwser(String status,String aid,String uid){
        this.status = status;
        this.aid = aid;
        this.uid = uid;
        api = RetrofitUtils.createService(QuestionApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.priseAnwser(status,aid,uid, UserRepository.getInstance().getAuthId());
    }
}
