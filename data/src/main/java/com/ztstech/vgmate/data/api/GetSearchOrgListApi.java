package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by smm on 2017/12/8.
 */

public interface GetSearchOrgListApi {

    /**
     * 查询销售列表
     * @param authId
     * @param pageNo
     * @param rbidistrict 所在区县的编码
     * @param rbioname 搜索的机构名字
     * @return
     */
    @GET("exempt/AppSalesFindOrgByRegionTypeName")
    Observable<OrgFollowlistBean> getSearchOrgList(@Query(NetConstants.PARAM_AUTHID) String authId,
                                                            @Query("pageNo") int pageNo,
                                                            @Query("rbidistrict") String rbidistrict,
                                                            @Query("rbioname") String rbioname);
}
