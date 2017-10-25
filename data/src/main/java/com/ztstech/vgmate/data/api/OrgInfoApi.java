package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/16.
 */

public interface OrgInfoApi {

    @GET("exempt/AppSaleBackOrgMsg")
    Observable<OrgInfoBean> getOrgInfo(@Query("rbiid") int rbiid,
                                       @Query(NetConstants.PARAM_AUTHID) String authId);

    @POST("exempt/AppSaleDelRbiByRbiId")
    Observable<BaseRespBean> deleteOrgInfo(@Query("rbiid") String rbiid,
                                           @Query("delmsg") String delmsg,
                                           @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 审核通过
     * @return
     */
    @POST("exempt/AppSaleAuditRBI")
    Observable<BaseRespBean> confirmOrgInfo(@Query("repeatrbiid") String repeatrbiid,
                                            @Query("rbiid") String rbiid,
                                            @Query("rbibackmsg") String rbibackmsg,
                                            @Query("rbiostatus") String rbiostatus,
                                            @Query("oname") String oname,
                                            @Query("otype") String otype,
                                            @Query("district") String district,
                                            @Query("address") String address,
                                            @Query("gps") String gps,
                                            @Query("contphone") String contphone,
                                            @Query(NetConstants.PARAM_AUTHID) String authId);

}
