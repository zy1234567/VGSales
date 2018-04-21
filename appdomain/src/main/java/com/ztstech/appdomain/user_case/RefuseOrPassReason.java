package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.RefuseOrPassReasonApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.RefuseOrPassData;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/4/21.
 */

public class RefuseOrPassReason implements UserCase<Observable<BaseRespBean>> {
    /**路人登记通过定位认证 或者  机构认领拒绝*/
    RefuseOrPassData refuseCalimData;
    RefuseOrPassReasonApi refuseReasonApi;
    final int type;
    public RefuseOrPassReason(RefuseOrPassData refuseOrPassData, int type) {
        refuseReasonApi = RetrofitUtils.createService(RefuseOrPassReasonApi.class);
        this.refuseCalimData = refuseOrPassData;
        this.type = type;
    }

    @Override
    public Observable<BaseRespBean> run() {
        return refuseReasonApi.approveClaimOrg(refuseCalimData.rbiid,
                refuseCalimData.calid,
                refuseCalimData.identificationtype,
                refuseCalimData.status,
                refuseCalimData.testorg
                , UserRepository.getInstance().getAuthId());
    }
}
