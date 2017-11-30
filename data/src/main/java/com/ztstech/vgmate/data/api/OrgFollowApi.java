package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * @author smm
 * @date 2017/11/14
 */

public interface OrgFollowApi {

    /**
     * 查询客户跟进列表
     * @param status 00默认没有筛选 01以确认，02有待认领，03以认领，04管理端
     * @return
     */
    @GET("exempt/AppSaleQueryMyResponsibilityOrg")
    Observable<OrgFollowlistBean> queryList(@Query("authId") String authId, @Query("status") String status,
                                            @Query("uid") String uid,
                                            @Query("pageNo") int currentPage);

    /**
     * 查询客户跟进三个状态的数量
     * @param authId
     * @return
     */
    @POST("exempt/saleCountRBINum")
    Observable<OrgFollowNumBean> queryFollowNum(@Query("authId") String authId);

}
