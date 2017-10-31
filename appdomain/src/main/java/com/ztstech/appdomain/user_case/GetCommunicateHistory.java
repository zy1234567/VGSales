package com.ztstech.appdomain.user_case;

import android.text.TextUtils;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.data.api.CommunicateHistoryApi;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.appdomain.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/11.
 * 获取交流记录
 */

public class GetCommunicateHistory implements UserCase<Observable<CommunicationHistoryBean>> {

    private CommunicateHistoryApi api;
    private int page;
    private String comid;
    private String rbiid;

    public GetCommunicateHistory(int page, String comid, String rbiid) {
        api = RetrofitUtils.createService(CommunicateHistoryApi.class);

        this.page = page;
        this.comid = comid;
        this.rbiid = rbiid;
    }
    

    @Override
    public Observable<CommunicationHistoryBean> run() {
        if (!TextUtils.isEmpty(comid)) {
            return api.getCommunicationHistoryByComid(comid, page, UserRepository.getInstance().getAuthId());
        }else {
            return api.getCommunicationHistoryByRibid(rbiid, page, UserRepository.getInstance().getAuthId());
        }
    }
}
