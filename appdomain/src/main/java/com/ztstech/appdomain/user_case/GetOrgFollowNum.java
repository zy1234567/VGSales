package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.OrgFollowApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;

import io.reactivex.Observable;

/**
 * Created by smm on 2017/11/24.
 */

public class GetOrgFollowNum implements UserCase<Observable<OrgFollowNumBean>>{

    private OrgFollowApi api;

    private String uid;

    public GetOrgFollowNum(String uid){
        this.uid = uid;
        api = RetrofitUtils.createService(OrgFollowApi.class);
    }

    @Override
    public Observable<OrgFollowNumBean> run() {
        return api.queryFollowNum(UserRepository.getInstance().getAuthId(),uid);
    }
}
