package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.RobChanceBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dongdong on 2018/4/18.
 */

public interface RobChanceApi {
    @POST("exempt/AppSaleListEnableChanceOrg")
    Observable<RobChanceBean> robChance(@Query("pageNo") int pageNo,
                                        @Query("authId") String authId);
}
