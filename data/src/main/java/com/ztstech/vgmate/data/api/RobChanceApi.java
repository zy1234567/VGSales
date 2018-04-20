package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.LastTimeBean;
import com.ztstech.vgmate.data.beans.RobChanceBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dongdong on 2018/4/18.
 */

public interface RobChanceApi {
    /**
     * 可抢机会列表
     * @param pageNo
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleListEnableChanceOrg")
    Observable<RobChanceBean> robChance(@Query("pageNo") int pageNo,
                                        @Query("authId") String authId);

    /**
     * 锁定抢单
     * @param rbiid
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleLockOrgByRbiid")
    Observable<RobChanceBean> lockOrg(@Query("rbiid") String rbiid,
                                      @Query("authId") String authId);

    /**
     *
     * @param rbiid
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleGetRushorderByRbiid")
    Observable<LastTimeBean> lasttime(@Query("rbiid") String rbiid,
                                      @Query("authId") String authId);
}
