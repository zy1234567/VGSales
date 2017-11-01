package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.OrgListApi;
import com.ztstech.vgmate.data.beans.GetOrgListCountBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/23.
 * 获取机构数目
 */

public class GetOrgListCount implements UserCase<Observable<GetOrgListCountBean>>{

    private OrgListApi api;

    private String locationId;

    public GetOrgListCount(String locId) {
        api = RetrofitUtils.createService(OrgListApi.class);
        this.locationId = locId;
    }


    @Override
    public Observable<GetOrgListCountBean> run() {
        return api.loadCount(UserRepository.getInstance().getAuthId(),
                locationId);
    }
}
