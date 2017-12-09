package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.ApproveMateApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;

/**
 * Created by smm on 2017/11/17.
 */

public class ApproveMate implements UserCase<Observable<BaseRespBean>>{

    public static final String STATUS_PASS = "00";
    public static final String STATUS_APPROVING = "01";
    public static final String STATUS_REFUSE = "02";

    private String uid;

    private String status;

    private ApproveMateApi api;

    public ApproveMate(String uid,String status){
        this.uid = uid;
        this.status = status;
        api = RetrofitUtils.createService(ApproveMateApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.addSellMate(uid,status, UserRepository.getInstance().getAuthId());
    }
}
