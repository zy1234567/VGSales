package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/16.
 */

public interface OrgInfoApi {

    @GET("exempt/AppSaleBackOrgMsg")
    Observable<OrgInfoBean> getOrgInfo(@Query("rbiid") int rbiid,
                                       @Query(NetConstants.PARAM_AUTHID) String authId);
}
