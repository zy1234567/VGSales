package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.SearchOrgListBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smm on 2017/12/8.
 */

public interface GetSearchOrgListApi {

    /**
     * 查询销售列表
     * @param authId
     * @param pageNo
     * @param rbicity 所在区县的编码
     * @param rbioname 搜索的机构名字
     * @return
     */
    @GET("exempt/AppSaleCountSalesList")
    Observable<SearchOrgListBean.ListBean> getSearchOrgList(@Query(NetConstants.PARAM_AUTHID) String authId,
                                                   @Query("pageNo") int pageNo,
                                                   @Query("rbicity") String rbicity,
                                                   @Query("rbioname") String rbioname);
}
