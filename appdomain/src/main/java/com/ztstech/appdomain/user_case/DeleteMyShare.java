package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/28.
 */

public class DeleteMyShare implements UserCase<Observable<BaseRespBean>> {

    private CreateShareApi api;

    private String sid;

    public DeleteMyShare(String sid){
        this.sid = sid;
        api = RetrofitUtils.createService(CreateShareApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.deleteMyShare(sid, UserRepository.getInstance().getAuthId());
    }
}
