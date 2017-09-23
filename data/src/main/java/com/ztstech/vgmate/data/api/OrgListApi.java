package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.GetOrgListCountBean;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/23.
 *
 */

public interface OrgListApi {

    /**
     * 获取上方数字
     * @param authId
     * @param rbidistrict
     * @return
     */
    @GET("exempt/AppSaleQueryRBInum")
    Observable<GetOrgListCountBean> loadCount(@Query(NetConstants.PARAM_AUTHID) String authId,
                                              @Query("rbidistrict") String rbidistrict);

    /**
     * 加载列表信息
     * @param status
     * @param rbidistrict
     * @param pageNo
     * @param authId
     * @return
     */
    @GET("exempt/AppSaleQueryRBIList")
    Observable<GetOrgListItemsBean> loadListItems(@Query("status") String status,
                                                  @Query("rbidistrict") String rbidistrict,
                                                  @Query("pageNo") int pageNo,
                                                  @Query(NetConstants.PARAM_AUTHID) String authId);

}
