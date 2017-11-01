package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.OrgInfoApi;
import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/16.
 * 获取机构资料
 */

public class GetOrgInfo implements UserCase<Observable<OrgInfoBean>> {

    private int rbiid;

    public OrgInfoApi api;

    public GetOrgInfo(int rbiid) {
        this.rbiid = rbiid;
        api = RetrofitUtils.createService(OrgInfoApi.class);
    }

    @Override
    public Observable<OrgInfoBean> run() {
        return api.getOrgInfo(rbiid, UserRepository.getInstance().getAuthId());
    }
}
