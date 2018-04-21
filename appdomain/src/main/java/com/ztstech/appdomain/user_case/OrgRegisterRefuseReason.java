package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.RefuseOrPassReasonApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.OrgRegisterRefuseData;
import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/4/21.
 */

public class OrgRegisterRefuseReason implements UserCase<Observable<BaseRespBean>> {
    OrgRegisterRefuseData orgRegisterRefuseData;
    RefuseOrPassReasonApi refuseOrPassReasonApi;
    public OrgRegisterRefuseReason(OrgRegisterRefuseData orgRegisterRefuseData){
        this.orgRegisterRefuseData=orgRegisterRefuseData;
        refuseOrPassReasonApi= RetrofitUtils.createService(RefuseOrPassReasonApi.class);

    }

    @Override
    public Observable<BaseRespBean> run() {
        return refuseOrPassReasonApi.appregisterOrgno(orgRegisterRefuseData.rbiid,
                UserRepository.getInstance().getAuthId(),orgRegisterRefuseData.type,
                orgRegisterRefuseData.rubbishtype,orgRegisterRefuseData.refuse,orgRegisterRefuseData.oname);
    }
}
