package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smm on 2017/11/17.
 */

public interface ApproveMateApi {

    /**
     * 审批销售
     * @param saleid    销售uid
     * @param status 审核状态（00通过，01审批中，02拒绝）
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleApprovalSale")
    Observable<BaseRespBean> addSellMate(@Query("saleid") String saleid,
                                         @Query("status") String status,
                                         @Query("authId") String authId);

}
