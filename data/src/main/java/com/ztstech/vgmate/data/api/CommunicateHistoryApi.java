package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/11.
 * 沟通历史api
 */

public interface CommunicateHistoryApi {


    /**
     * 根据comid查询沟通记录
     * @param comid
     * @param currentPage
     * @param authId
     * @return
     */
    @GET("exempt/AppSaleQueryOrgLogByComid")
    Observable<CommunicationHistoryBean> getCommunicationHistoryByComid(@Query("comid") String comid,
                                                                        @Query("currentPage") int currentPage,
                                                                        @Query(NetConstants.PARAM_AUTHID) String authId);


    /**
     * 根据ribid查询沟通记录
     * @return
     */
    @GET("exempt/AppSaleQueryOrglogByRbiid")
    Observable<CommunicationHistoryBean> getCommunicationHistoryByRibid(@Query("rbiid") String rbiid,
                                                                        @Query("pageNo") int pageNo,
                                                                        @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 根据ribid添加沟通记录
     * @param msg
     * @param rbiid
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleAddOrglog")
    Observable<BaseRespBean> addCommunicationByRibid(@Query("msg") String msg,
                                                     @Query("rbiid") String rbiid,
                                                     @Query(NetConstants.PARAM_AUTHID) String authId);


    /**
     * 根据comid添加沟通记录
     * @param msg 沟通信息
     * @param orgid 机构id
     * @param comid 新推荐id
     * @param status 00添加记录，01关闭并添加记录
     * @return
     */
    @POST("exempt/saleInsertApprovalLog")
    Observable<BaseRespBean> addCommunicationByComid(@Query("msg") String msg,
                                                     @Query("orgid") String orgid,
                                                     @Query("comid") String comid,
                                                     @Query("status") String status,
                                                     @Query(NetConstants.PARAM_AUTHID) String authId);
}
