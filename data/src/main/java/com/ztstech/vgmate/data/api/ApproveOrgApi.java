package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by smm on 2017/12/6.
 */

public interface ApproveOrgApi {
    /**
     * 机构认领来的机构走的审核接口
     * @param rbiid
     * @param calid
     * @param identificationtype
     * @param status
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleAuditClaims")
    Observable<BaseRespBean> approveClaimOrg(@Query("rbiid") String rbiid,
                                             @Query("calid") String calid,
                                             @Query("identificationtype") String identificationtype,
                                             @Query("status") String status,
                                             @Query("testorg") String testorg,
                                             @Query("authId") String authId);

    /**
     * 登记来的机构审核通过的接口
     * @param rbiid
     * @param rbiostatus 00打点生效
     * @param flag 固定传00没有沟通记录
     * @param identificationtype
     * @param confirmOrUpdateType 传固定值00确认
     * @return
     */
    @POST("exempt/AppSaleAuditRBI")
    Observable<BaseRespBean> appregisterOrgyes(@Query("rbiid") String rbiid,
                                               @Query("rbiostatus") String rbiostatus,
                                               @Query("flag") String flag,
                                               @Query("identificationtype") String identificationtype,
                                               @Query("confirmOrUpdateType") String confirmOrUpdateType,
                                               @Query("testorg") String testorg,
                                               @Query("authId") String authId);

    /**
     * 登记来的机构审核拒绝接口
     * @param rbiid
     * @param authId
     * @return
     */
    @POST("exempt/appSaleDelRbiByRbiId")
    Observable<BaseRespBean> appregisterOrgno(@Query("rbiid") String rbiid,
                                              @Query("testorg") String testorg,
                                              @Query("authId") String authId);


}
