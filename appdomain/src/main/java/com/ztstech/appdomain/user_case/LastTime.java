package com.ztstech.appdomain.user_case;

import android.text.TextUtils;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.RobChanceApi;
import com.ztstech.vgmate.data.beans.LastTimeBean;

import io.reactivex.Observable;

/**
 * Created by dongdong on 2018/4/20.
 */

public class LastTime {
    private RobChanceApi api;
    private String rbiid;
    public LastTime(String rbiid){
        this.rbiid = rbiid;
        api = RetrofitUtils.createService(RobChanceApi.class);
    }

    public Observable<LastTimeBean> run() {
            return api.lasttime(rbiid, UserRepository.getInstance().getAuthId());

    }
}
