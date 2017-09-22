package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/16.
 */

public interface AddSellMateApi {

    @FormUrlEncoded
    @POST("exempt/saleInsertNewSale")
    Observable<BaseRespBean> addSellMate(@Field("phone") String phone,
                                         @Field("uname") String uname,
                                         @Field("did") String did,
                                         @Field("wprovince") String wprovince,
                                         @Field("wcity") String wcity,
                                         @Field("wdistrict") String wdistrict,
                                         @Field("authId") String authId,
                                         @Field("noteswitch") String noteswitch);
}
