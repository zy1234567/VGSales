package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public interface MainListApi {


    /**
     * 查询首页列表数据
     * @param type 查询类型  00资讯  01公告
     * @return
     */
    @GET("exempt/AppSalesQueryNewList")
    Observable<MainListBean> queryList(@Query("authId") String authId, @Query("type") String type,
                                       @Query("pageNo") int currentPage);

    /**
     * 删除文章
     * @param nid 文章内容
     * @param authId
     * @return
     */
    @POST("exempt/AppSalesDelNews")
    Observable<BaseRespBean> deleteArticle(@Query("nid") String nid,
                                           @Query(NetConstants.PARAM_AUTHID) String authId);
}
