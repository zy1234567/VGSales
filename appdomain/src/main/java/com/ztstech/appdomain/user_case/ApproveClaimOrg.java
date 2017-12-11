package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.ApproveOrgApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;

/**
 * 销售审批机构认领
 * @author smm
 * @date 2017/12/6
 */

public class ApproveClaimOrg implements UserCase<Observable<BaseRespBean>>{

    /** 审核通过 */
    public static final String STATUS_PASS = "00";
    /** 审核拒绝 */
    public static final String STATUS_REFUSE = "01";

    /** 定位认证 */
    public static final String IDENT_TYPE_LOCATION = "01";

    /** 加v认证 */
    public static final String IDENT_TYPE_ADDV = "02";

    private String rbiid;

    private String calid;

    /** 认证类型 */
    private String identType;

    private String status;

    private ApproveOrgApi api;

    public ApproveClaimOrg(String rbiid,String status,String calid,String identType){
        api = RetrofitUtils.createService(ApproveOrgApi.class);
        this.rbiid = rbiid;
        this.status = status;
        this.calid = calid;
        this.identType = identType;
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.approveClaimOrg(rbiid,calid,identType,status, UserRepository.getInstance().getAuthId());
    }
}
