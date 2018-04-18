package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.RobChanceApi;
import com.ztstech.vgmate.data.beans.RobChanceBean;

import io.reactivex.Observable;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChance {
    private int pageNo;
    private RobChanceApi api;
    public RobChance(int pageNo){
        this.pageNo = pageNo;
        api = RetrofitUtils.createService(RobChanceApi.class);
    }

    public Observable<RobChanceBean> run() {
        return api.robChance(pageNo,UserRepository.getInstance().getAuthId());
    }
}
