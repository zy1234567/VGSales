package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/12.
 */

public interface CommentApi {

    /**
     * 查询评论
     * @param newsid 资讯id
     * @param pageNo 页数，接口文档没写此参数
     * @param authId
     * @return
     */
    @GET("exempt/AppSalesGetCommentByNewsId")
    Observable<CommentBean> getCommentList(@Query("newsid") String newsid,
                                           @Query("pageNo") int pageNo,
                                           @Query(NetConstants.PARAM_AUTHID) String authId);


    /**
     * 评论或者回复某人
     * @param flid 父uid
     * @param newid 资讯id
     * @param touid 被评论人uid
     * @param comment 评论内容
     * @return
     */
    @POST("exempt/AppSalesAddComment")
    Observable<BaseRespBean> comment(@Query("flid") String flid,
                                     @Query("newid") String newid,
                                     @Query("touid") String touid,
                                     @Query("comment") String comment,
                                     @Query(NetConstants.PARAM_AUTHID) String authid);

    /**
     * 删除评论
     * @param request 鬼知道这是什么
     * @param lid
     * @return
     */
    @POST("exempt/AppSalesDelComment")
    Observable<BaseRespBean> deleteComment(@Query("request") String request,
                                           @Query("lid") String lid);
}
