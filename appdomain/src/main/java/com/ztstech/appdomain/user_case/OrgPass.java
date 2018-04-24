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
    int type;
    public OrgPass(OrgPassData orgPassData){
        this.orgPassData = orgPassData;
        this.type = type;
        refuseOrPassReasonApi = RetrofitUtils.createService(RefuseOrPassReasonApi.class);

    }
    @Override
    public Observable<BaseRespBean> run() {
        if (orgPassData.approvetype == 1) {
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
                    orgPassData.roletype,
                    orgPassData.positionpicurl);
        }else{
            return refuseOrPassReasonApi.orgClaim(
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
                    orgPassData.positionpicurl,
                    orgPassData.callon,
                    orgPassData.calid,
                    orgPassData.status,
                    orgPassData.roletype,
                    "01");
        }
    }
}
