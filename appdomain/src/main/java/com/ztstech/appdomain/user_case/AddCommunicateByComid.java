package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.data.api.CommunicateHistoryApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.appdomain.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/24.
 * 根据comid添加
 */

public class AddCommunicateByComid implements UserCase<Observable<BaseRespBean>>{


    private String orgid;
    private String comid;
    private String status;
    private String msg;

    private CommunicateHistoryApi api;


    public AddCommunicateByComid(String msg, String orgid, String comid, String status) {
        this.msg = msg;
        this.orgid = orgid;
        this.comid = comid;
        this.status = status;
        this.api = RetrofitUtils.createService(CommunicateHistoryApi.class);
    }


    @Override
    public Observable<BaseRespBean> run() {
        return api.addCommunicationByComid(msg, orgid, comid, status,
                UserRepository.getInstance().getAuthId());
    }
}
