package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.MatelistBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smm on 2017/11/23.
 */

public interface GetMateListApi {

    /**
     * 查询销售列表
     * @param authId
     * @param pageNo
     * @return
     */
    @GET("exempt/AppSaleCountSalesList")
    Observable<MatelistBean> getMateList(@Query(NetConstants.PARAM_AUTHID) String authId,
                                         @Query("pageNo") int pageNo);

}
