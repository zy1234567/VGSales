package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/16.
 */

public interface AddSellMateApi {

    @POST("exempt/saleInsertNewSale")
    Observable<BaseRespBean> addSellMate(@Query("phone") String phone,
                                         @Query("uname") String uname,
                                         @Query("did") String did,
                                         @Query("wprovince") String wprovince,
                                         @Query("wcity") String wcity,
                                         @Query("wdistrict") String wdistrict,
                                         @Query("authId") String authId,
                                         @Query("noteswitch") String noteswitch);
}
