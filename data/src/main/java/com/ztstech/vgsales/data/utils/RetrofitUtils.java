package com.ztstech.vgsales.data.utils;

import com.ztstech.vgsales.data.constants.NetConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public class RetrofitUtils {

    public static <T> T createService(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

}
