package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smm on 2017/11/17.
 */

public interface GetMateDetailApi {

    /**
     * 获取待审批销售的详情
     * @param saleid 销售id
     * @return
     */
    @GET("exempt/AppSaleQuerySaleByuid")
    Observable<UnApproveMateBean> queryList(@Query("authId") String authId, @Query("saleid") String saleid);

}
