package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QuestionApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;

/**
 * Created by smm on 2017/11/22.
 * 发布问题
 */

public class CreateQuestion implements UserCase<Observable<BaseRespBean>>{

    private QuestionApi api;
    /** 问题描述 */
    private String descrption;
    /** 问题补充 */
    private String supplement;

    public CreateQuestion(String descrption,String supplement){
        this.descrption = descrption;
        this.supplement = supplement;
        api = RetrofitUtils.createService(QuestionApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.createQuestion(descrption,supplement, UserRepository.getInstance().getAuthId());
    }
}
