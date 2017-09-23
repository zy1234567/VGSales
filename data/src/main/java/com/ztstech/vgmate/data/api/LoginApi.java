package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.dto.UpdateUserInfoData;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/22.
 */

public interface LoginApi {


    @POST("exempt/sendLogincode.do")
    Observable<BaseRespBean> sendLoginCode(@Query("phone") String phone);

    @POST("exempt/saleCheckCodeByPhone")
    Observable<UserBean> login(@Query("phone") String phone, @Query("code") String code,
                               @Query("type") String type);


    @POST("exempt/saleUpdateUserMsg")
    Observable<BaseRespBean> updateUserInfo(@Query("authId") String authId,
                                            @Query("picurl") String picurl,
                                            @Query("didurl") String didurl,
                                            @Query("cardurl") String cardUrl,
                                            @Query("sex") String sex,
                                            @Query("did") String did,
                                            @Query("bname") String bname,
                                            @Query("banks") String banks,
                                            @Query("status") String status,
                                            @Query("cardNo") String cardNo,
                                            @Query("wdistrict") String wdistrict,
                                            @Query("birthday") String birthday,
                                            @Query("uid") String uid,
                                            @Query("uname") String uname);

//     data.put("banks", bean.banks);
//        data.put("picurl", bean.picurl);
//        data.put("didurl", bean.didurl[0] + "," + bean.didurl[1]);
//        data.put("cardurl", bean.cardUrl);
//        data.put("wdistrict", bean.wdistrict);
//        data.put("sex", bean.sex);
//        data.put("did", bean.did);
//        data.put("bname", bean.bname);
//        data.put("cardNo", bean.cardNo);
//        data.put("birthday", bean.birthday);
//        data.put("uname", bean.uname);

//    @POST("exempt/saleUpdateUserMsg")
//    Observable<BaseRespBean> updateUserInfo(@Body UpdateUserInfoData data);
//
//    @POST("exempt/saleUpdateUserMsg")
//    Observable<BaseRespBean> updateUserInfo(@QueryMap Map<String, Object> data);
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
