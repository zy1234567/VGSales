package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QuestionApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 *
 * @author smm
 * @date 2017/11/22
 * 删除问题
 */

public class DeleteQuestion implements UserCase<Observable<BaseRespBean>> {

    private QuestionApi api;

    private String quid;

    public DeleteQuestion(String quid){
        this.quid = quid;
        api = RetrofitUtils.createService(QuestionApi.class);
    }


    @Override
    public Observable<BaseRespBean> run() {
        return api.deleteQuestion(quid, UserRepository.getInstance().getAuthId());
    }
}
