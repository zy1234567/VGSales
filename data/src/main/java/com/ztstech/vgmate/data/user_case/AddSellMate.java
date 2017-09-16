package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.AddSellMateApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddSellMateData;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/16.
 * 增加销售伙伴
 */

public class AddSellMate implements UserCase<Observable<BaseRespBean>>{

    private AddSellMateData data;
    private AddSellMateApi api;
    private String sendCode;

    public AddSellMate(AddSellMateData data) {
        this.data = data;
        this.api = RetrofitUtils.createService(AddSellMateApi.class);
        this.sendCode = data.sendCode ? "00" : null;
    }


    @Override
    public Observable<BaseRespBean> run() {
        return api.addSellMate(data.phone,
                data.uname,
                data.did,
                data.wprovince,
                data.wcity,
                data.wdistrict,
                UserRepository.getInstance().getAuthId(),
                sendCode);
    }

}
