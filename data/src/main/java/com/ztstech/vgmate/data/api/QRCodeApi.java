package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/11/2.
 */

public interface QRCodeApi {



//    @POST("/code/phonescan")
//    Observable<BaseRespBean> checkUUID(
//            @Query("info") String phone);

    @POST("/code/phonescan")
    Observable<BaseRespBean> checkUUID(
            @Query("uuid") String uuid,
            @Query("phone") String phone);

    @POST("code/phoneLogin")
    Observable<BaseRespBean> login(@Query("phone") String phone,
                                   @Query("uuid") String uuid,
                                   @Query(NetConstants.PARAM_AUTHID) String authId);
}
