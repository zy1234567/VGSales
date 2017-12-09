package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by smm on 2017/12/6.
 */

public interface ApproveOrgApi {

    @POST("exempt/AppSaleAuditClaims")
    Observable<BaseRespBean> approveClaimOrg(@Query("rbiid") String rbiid,
                                             @Query("calid") String calid,
                                         @Query("status") String status,
                                         @Query("authId") String authId);

}
