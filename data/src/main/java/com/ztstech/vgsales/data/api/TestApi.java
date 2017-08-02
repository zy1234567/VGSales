package com.ztstech.vgsales.data.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface TestApi {


    @GET("top250")
    Observable<String> loadContentAllData();
}
