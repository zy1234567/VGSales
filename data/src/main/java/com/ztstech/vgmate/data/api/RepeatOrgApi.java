package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.RepeatOrgBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/25.
 */

public interface RepeatOrgApi {
//    status 00新打点时，01合并机构时，02审核时查询相似

    /**打点，日他*/
    String STATUS_PIN = "00";
    /**合并*/
    String STATUS_MERGE = "01";
    /**审核时查询相似*/
    String STATUS_SEARCH = "02";


    @POST("exempt/AppSaleFuzzyQueryListByRBI")
    Observable<RepeatOrgBean> getRepeatOrg(@Query(NetConstants.PARAM_AUTHID) String authId,
                                           @Query("rbidistrict") String rbidistrict,
                                           @Query("rbioname") String rbioname,
                                           @Query("status") String status,
                                           @Query("rbiid") int rbiid,
                                           @Query("pageNo") int pageNo);
}
