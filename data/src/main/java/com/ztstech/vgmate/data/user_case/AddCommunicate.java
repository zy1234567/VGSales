package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.CommunicateHistoryApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/24.
 * 增加交流记录
 */

public class AddCommunicate implements UserCase<Observable<BaseRespBean>>{

    private CommunicateHistoryApi communicateHistoryApi;

    private String rbiid;
    private String msg;

    public AddCommunicate(String rbiid, String msg) {
        this.rbiid = rbiid;
        this.msg = msg;

        this.communicateHistoryApi = RetrofitUtils.createService(CommunicateHistoryApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return communicateHistoryApi.addCommunication(msg, rbiid,
                UserRepository.getInstance().getAuthId());
    }
}
