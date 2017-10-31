package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.dto.UpdateUserInfoData;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/22.
 */

public interface LoginApi {

    /**
     * 发送验证码
     * @param phone
     * @param type  00：手机登录发送验证码或者修改手机号给旧手机发送验证码
     *              01: 修改手机号给新手机发送验证码
     *
     * @return
     */
    @Headers("user-agent: Android")
    @POST("code/sendLogincode.do")
    Observable<BaseRespBean> sendLoginCode(@Query("phone") String phone, @Query("type") String type);

    @Headers("user-agent: Android")
    @POST("exempt/saleCheckCodeByPhone")
    Observable<UserBean> login(@Query("phone") String phone, @Query("code") String code,
                               @Query("type") String type);


    @Headers("user-agent: Android")
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
                                            @Query("cardno") String cardNo,
                                            @Query("wdistrict") String wdistrict,
                                            @Query("birthday") String birthday,
                                            @Query("uid") String uid,
                                            @Query("uname") String uname);

    /**
     * 刷新登录
     * @return
     */
    @Headers("user-agent: Android")
    @POST("exempt/AppSaleUpdateLoginStatus")
    Observable<UserBean> refreshLogin(@Query("phone") String phone,
                                      @Query("authId") String authId);
    @Headers("user-agent: Android")
    @POST("exempt/AppSaleLoginout")
    Observable<BaseRespBean> logout(@Query("authId") String authId);


    /**
     * 更新手机号
     * @param phone 旧手机
     * @param code 验证码
     * @param type 00仅验证，01验证并根据结果修改手机号
     * @param newPhone 新手机号
     * @return
     */
    @Headers("user-agent: Android")
    @POST("exempt/AppSaleUpdateSauserPhone")
    Observable<UserBean> updatePhone(@Query("phone") String phone,
                                     @Query("code") String code,
                                     @Query("type") String type,
                                     @Query("newphone") String newPhone);
}
