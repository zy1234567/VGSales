package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.GetOrgListCountBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/23.
 *
 */

public interface OrgListApi {

    @GET("exempt/AppSaleQueryRBInum")
    Observable<GetOrgListCountBean> loadCount(@Query(NetConstants.PARAM_AUTHID) String authId,
                                              @Query("rbidistrict") String rbidistrict);
}
