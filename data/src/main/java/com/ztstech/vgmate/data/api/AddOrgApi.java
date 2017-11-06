package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by smm on 2017/11/6.
 */

public interface AddOrgApi {

    @FormUrlEncoded
    @POST("exempt/AppSaleRegisterRBI")
    Observable<BaseRespBean> addOrg(@Field("rbioname") String rbioname,
                                         @Field("rbiotype") String rbiotype,
                                         @Field("rbidistrict") String rbidistrict,
                                         @Field("rbigps") String rbigps,
                                         @Field("authId") String authId,
                                         @Field("rbiaddress") String rbiaddress,
                                         @Field("rbiintroduction") String rbiintroduction,
                                         @Field("rbiphone") String rbiphone);

}
