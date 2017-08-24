package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.MainPageBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/24.
 */

public interface MainPageApi {

    @GET("AppSaleCountHomeMsg")
    Observable<MainPageBean> loadMainPageInfo();
}
