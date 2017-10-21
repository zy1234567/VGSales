package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.CommunicateHistoryApi;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/11.
 * 获取交流记录
 */

public class GetCommunicateHistory implements UserCase<Observable<CommunicationHistoryBean>> {

    private CommunicateHistoryApi api;
    private int page;
    private String comid;

    public GetCommunicateHistory(int page, String comid) {
        api = RetrofitUtils.createService(CommunicateHistoryApi.class);

        this.page = page;
        this.comid = comid;

    }
    

    @Override
    public Observable<CommunicationHistoryBean> run() {
        return api.getCommunicationHistory(comid, page, UserRepository.getInstance().getAuthId());
    }
}
