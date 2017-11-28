package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/28.
 */

public class PriseShare implements UserCase<Observable<BaseRespBean>>{

    private String sid;

    private String status;

    private String likedid;

    private String likeuid;

    private CreateShareApi api;

    public PriseShare(String sid,String status,String likedid,String likeuid){
        this.sid = sid;
        this.status = status;
        this.likedid = likedid;
        this.likeuid = likeuid;
        api = RetrofitUtils.createService(CreateShareApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.priseShare(status,likedid,likeuid, UserRepository.getInstance().getAuthId());
    }
}
