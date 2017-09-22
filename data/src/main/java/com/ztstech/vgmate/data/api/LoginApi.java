package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UserBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/22.
 */

public interface LoginApi {


    @POST("exempt/sendLogincode.do")
    Observable<BaseRespBean> sendLoginCode(@Query("phone") String phone);

    @FormUrlEncoded
    @POST("exempt/saleCheckCodeByPhone")
    Observable<UserBean> login(@Field("phone") String phone, @Field("code") String code,
                               @Field("type") String type);


    @FormUrlEncoded
    @POST("exempt/saleUpdateUserMsg")
    Observable<BaseRespBean> updateUserInfo(@Field("picurl") String picurl,
                                            @Field("didurl") String[] didurl,
                                            @Field("cardUrl") String cardUrl,
                                            @Field("sex") String sex,
                                            @Field("did") String did,
                                            @Field("bname") String bname,
                                            @Field("banks") String banks,
                                            @Field("status") String status,
                                            @Field("cardNo") String cardNo,
                                            @Field("wdistrict") String wdistrict,
                                            @Field("birthday") String birthday,
                                            @Field("uid") String uid,
                                            @Field("uname") String uname);

    /**
     * 刷新登录
     * @return
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleUpdateLoginStatus")
    Observable<UserBean> refreshLogin(@Field("phone") String phone,
                                      @Field("authId") String authId);

    @FormUrlEncoded
    @POST("exempt/AppSaleLoginout")
    Observable<BaseRespBean> logout(@Field("authId") String authId);
}
