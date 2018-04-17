package com.ztstech.appdomain.user_case;

import android.text.TextUtils;

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

    /** 来源于认领 */
    public static final String TYPE_CLAIM = "00";
    /** 来源于登记 */
    public static final String TYPE_ADD = "01";

    /** 定位认证 */
    public static final String IDENT_TYPE_LOCATION = "01";

    /** 加v认证 */
    public static final String IDENT_TYPE_ADDV = "02";

    private String rbiid;

    private String calid;

    /** 认证类型 */
    private String identType;

    private String status;

    /** 是否勾选了测试 */
    private String testOrg;

    private ApproveOrgApi api;
    /**机构类型 00认领来的机构 01登记来的机构*/
    String type;
    /**登记来的机构是否通过审核*/
    String yesorno;
    String oname;
    public ApproveClaimOrg(String rbiid,String status,String calid,String identType,String testOrg,String type,String yesorno,String oname){
        api = RetrofitUtils.createService(ApproveOrgApi.class);
        this.rbiid = rbiid;
        this.status = status;
        this.calid = calid;
        this.identType = identType;
        this.type = type;
        this.testOrg = testOrg;
        this.yesorno = yesorno;
        this.oname = oname;
    }

    @Override
    public Observable<BaseRespBean> run() {
        if (TextUtils.equals(type,TYPE_CLAIM)) {
            return api.approveClaimOrg(rbiid, calid, identType, status,testOrg,UserRepository.getInstance().getAuthId());
        }else{
            if (TextUtils.equals(yesorno,STATUS_PASS)) {
                return api.appregisterOrgyes(rbiid, STATUS_PASS, STATUS_PASS, identType, STATUS_PASS, testOrg,UserRepository.getInstance().getAuthId());
            }else{
                return api.appregisterOrgno(rbiid,UserRepository.getInstance().getAuthId(),"01","04","00",oname);
            }
        }
    }
}
