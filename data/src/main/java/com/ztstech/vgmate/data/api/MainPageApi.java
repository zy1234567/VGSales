package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/8/24.
 */

public interface MainPageApi {

    @GET("exempt/AppSaleCountHomeMsg")
    Observable<MainPageBean> loadMainPageInfo(@Query("authId") String authId,
                                              @Query("district") String district);

}
