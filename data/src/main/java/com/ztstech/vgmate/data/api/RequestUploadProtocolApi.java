package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.dto.CoopProgressData;
import com.ztstech.vgmate.data.dto.UploadProtocolData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dongdong on 2018/3/27.
 */

public interface RequestUploadProtocolApi {
    /**
     * 请求上传协议
     * @param orgid
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleListProtocol")
    Observable<UploadProtocolData> requestUploadprotocol(@Query("orgid") String orgid,
                                                         @Query("authId") String authId);

    @POST("exempt/AppSaleQueryProgressByOrgid")
    Observable<CoopProgressData> requestCoopprogress(@Query("rbiid") String rbiid,
                                                     @Query("orgid") String orgid,
                                                     @Query("authId") String authId);
}
