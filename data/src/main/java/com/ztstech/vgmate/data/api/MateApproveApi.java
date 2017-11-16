package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smm on 2017/11/14.
 */

public interface MateApproveApi {

    /**
     * 查询待审批销售列表
     * @param uid 销售id
     * @return
     */
    @GET("exempt/AppSalesGetSubordinateSalesByUid")
    Observable<WaitApproveMateListBean> queryList(@Query("authId") String authId, @Query("uid") String uid,
                                                  @Query("pageNo") int pageNo);

}
