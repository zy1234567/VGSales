package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by smm on 2017/11/14.
 */

public interface GetUnApproveMateApi {

    /**
     * 查询待审批销售列表
     * @param uid 销售id
     * @param type 筛选类型 00默认全部,01直属
     * @return
     */
    @GET("exempt/AppSalesGetSubordinateSalesByUid")
    Observable<WaitApproveMateListBean> queryList(@Query("authId") String authId, @Query("uid") String uid,
                                                  @Query("type") String type,
                                                  @Query("pageNo") int pageNo);

}
