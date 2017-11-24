package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.OrgFollowApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/24.
 */

public class GetOrgFollowNum implements UserCase<Observable<OrgFollowNumBean>>{

    OrgFollowApi api = RetrofitUtils.createService(OrgFollowApi.class);

    @Override
    public Observable<OrgFollowNumBean> run() {
        return api.queryFollowNum(UserRepository.getInstance().getAuthId());
    }
}
