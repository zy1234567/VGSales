package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.AddOrgApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddOrgData;

import io.reactivex.Observable;

/**
 * Created by smm on 2017/11/6.
 *
 */

public class AddOrg implements UserCase<Observable<BaseRespBean>>{

    private AddOrgData data;
    private AddOrgApi api;

    public AddOrg(AddOrgData data){
        this.data = data;
        api = RetrofitUtils.createService(AddOrgApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.addOrg(data.rbioname,
                data.rbiotype,
                data.rbidistrict,
                data.rbigps,
                UserRepository.getInstance().getAuthId(),
                data.rbiaddress,
                data.rbiintroduction,
                data.rbiphone);
    }
}
