package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.dto.UploadProtocolData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dongdong on 2018/3/27.
 */

public interface RequestUploadProtocolApi {
    @POST("exempt/AppSaleListProtocol")
    Observable<UploadProtocolData> requestUploadprotocol(@Query("orgid") String orgid,
                                                @Query("authId") String authId);
}
