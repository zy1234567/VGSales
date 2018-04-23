package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.RefuseOrPassReasonApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.OrgPassData;

import io.reactivex.Observable;

/**
 * Created by dongdong on 2018/4/21.
 */

public class OrgPass implements UserCase<Observable<BaseRespBean>>  {
    OrgPassData orgPassData;
    RefuseOrPassReasonApi refuseOrPassReasonApi;
    public OrgPass(OrgPassData orgPassData){
        this.orgPassData = orgPassData;
        refuseOrPassReasonApi = RetrofitUtils.createService(RefuseOrPassReasonApi.class);

    }
    @Override
    public Observable<BaseRespBean> run() {
        return refuseOrPassReasonApi.nomalorOrgRegister(
                UserRepository.getInstance().getAuthId(),
                orgPassData.rbiid,
                orgPassData.rbiostatus,
                orgPassData.identificationtype,
                orgPassData.terminal,
                orgPassData.type,
                orgPassData.communicationtype,
                orgPassData.contactsname,
                orgPassData.contactsphone,
                orgPassData.msg,
                orgPassData.description,
                orgPassData.spotgps,
                orgPassData.spotphotos,
                orgPassData.wechatid,
                orgPassData.videopicurl,
                orgPassData.positionpicurl);
    }
}
