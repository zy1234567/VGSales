package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.MainListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smm on 2017/11/14.
 */

public interface MateApproveApi {

    /**
     * 查询首页列表数据
     * @param type 查询类型  00资讯  01公告
     * @return
     */
    @GET("exempt/AppSalesQueryNewList")
    Observable<MainListBean> queryList(@Query("authId") String authId, @Query("type") String type,
                                       @Query("currentPage") int currentPage);

}
