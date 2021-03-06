package com.ztstech.appdomain.user_case;

import android.text.TextUtils;

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
    private String rbiid;
    public RobChance(int pageNo,String rbiid){
        this.pageNo = pageNo;
        this.rbiid = rbiid;
        api = RetrofitUtils.createService(RobChanceApi.class);
    }

    public Observable<RobChanceBean> run() {
        if (!TextUtils.isEmpty(rbiid)) {
            return api.lockOrg(rbiid,UserRepository.getInstance().getAuthId());
        }else{
            return api.robChance(pageNo, UserRepository.getInstance().getAuthId());
        }
    }
}
