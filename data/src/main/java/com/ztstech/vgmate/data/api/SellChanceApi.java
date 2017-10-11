package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.SellChanceBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/11.
 */

public interface SellChanceApi {


    /**筛选 00 全部，01抢到机会数，02以成功，03可抢机会数*/
    String STATUS_ALL = "00";
    String STATUS_GET = "01";
    String STATUS_SUCCEED = "02";
    String STATUS_CHANCE = "03";

    @GET("exempt/AppSaleQueryCommendListBySale")
    Observable<SellChanceBean> getSellChance(@Query("status") String status,
                                             @Query("currentPage") int currentPage,
                                             @Query(NetConstants.PARAM_AUTHID) String authId);
}
