package com.ztstech.vgmate.data.api;

import android.provider.SyncStateContract;

import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/11.
 * 沟通历史api
 */

public interface CommunicateHistoryApi {


    @GET("exempt/AppSaleQueryOrgLogByComid")
    Observable<CommunicationHistoryBean> getCommunicationHistory(@Query("comid") String comid,
                                                                 @Query("currentPage") int currentPage,
                                                                 @Query(NetConstants.PARAM_AUTHID) String authId);
}
