package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.data.api.CommunicateHistoryApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/24.
 * 增加交流记录
 */

public class AddCommunicateByRbiid implements UserCase<Observable<BaseRespBean>>{

    private CommunicateHistoryApi communicateHistoryApi;

    private String rbiid;
    private String msg;

    public AddCommunicateByRbiid(String rbiid, String msg) {
        this.rbiid = rbiid;
        this.msg = msg;

        this.communicateHistoryApi = RetrofitUtils.createService(CommunicateHistoryApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return communicateHistoryApi.addCommunicationByRibid(msg, rbiid,
                UserRepository.getInstance().getAuthId());
    }
}
