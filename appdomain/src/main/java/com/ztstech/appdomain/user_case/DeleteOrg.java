package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.OrgInfoApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/23.
 */

public class DeleteOrg implements UserCase<Observable<BaseRespBean>> {

    private OrgInfoApi orgInfoApi;

    private String rbiid;
    private String delMsg;

    public DeleteOrg(String rbiid, String delmessage) {
        orgInfoApi = RetrofitUtils.createService(OrgInfoApi.class);
        this.delMsg = delmessage;
        this.rbiid = rbiid;
    }

    @Override
    public Observable<BaseRespBean> run() {
        return orgInfoApi.deleteOrgInfo(rbiid, delMsg, UserRepository.getInstance().getAuthId());
    }
}
