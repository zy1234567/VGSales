package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/22.
 */

public interface LoginApi {

    @POST("exempt/sendLogincode.do")
    Observable<BaseRespBean> sendLoginCode(@Query("phone") String phone);

    @POST("exempt/saleCheckCodeByPhone")
    Observable<BaseRespBean> login(@Query("phone") String phone, @Query("code") String code,
                                   @Query("type") String type);


    @POST("exempt/saleUpdateUserMsg")
    Observable<BaseRespBean> updateUserInfo(@Query("picurl") String picurl,
                                            @Query("didurl") String[] didurl,
                                            @Query("cardUrl") String cardUrl,
                                            @Query("sex") String sex,
                                            @Query("did") String did,
                                            @Query("bname") String bname,
                                            @Query("banks") String banks,
                                            @Query("status") String status,
                                            @Query("cardNo") String cardNo);
}
