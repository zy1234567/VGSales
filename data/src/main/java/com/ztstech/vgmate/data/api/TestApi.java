package com.ztstech.vgmate.data.api;

import retrofit2.http.GET;
import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface TestApi {


    @GET("top250")
    Observable<String> loadContentAllData();
}
