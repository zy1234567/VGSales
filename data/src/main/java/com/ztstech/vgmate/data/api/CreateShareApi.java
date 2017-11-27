package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/13.
 */

public interface CreateShareApi {

    /**创建资讯*/
    String SHARE_INFO = "00";
    /**创建通告*/
    String SHARE_NOTICE = "01";

    /**
     * 创建资讯或公告
     * @return
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleMakeNews")
    Observable<BaseRespBean> createShare(@Field("title") String title,
                                         @Field("summary") String summary,
                                         @Field("contentpicurl") String contentpicurl,
                                         @Field("contentpicsurl") String contentpicsurl,
                                         @Field("picurl") String picurl,
                                         @Field("picsurl") String picsurl,
                                         @Field("picdescribe") String picdescribe,
                                         @Field("type") String type,
                                         @Field("url") String url,
                                         @Field("authId") String authId);
    /**
     * 编辑资讯或公告
     * @return
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleUpdateNews")
    Observable<BaseRespBean> editShare(@Field("nid") String nid,
                                       @Field("title") String title,
                                       @Field("summary") String summary,
                                       @Field("contentpicurl") String contentpicurl,
                                       @Field("contentpicsurl") String contentpicsurl,
                                       @Field("picurl") String picurl,
                                       @Field("picsurl") String picsurl,
                                       @Field("picdescribe") String picdescribe,
                                       @Field("type") String type,
                                       @Field("url") String url,
                                       @Field("authId") String authId);

    /***
     * 资讯或公告重新发送
     * @param nid
     * @return
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleUpdateNews")
    Observable<BaseRespBean> resendShare(@Field("nid") String nid,
                                         @Field(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 创建分享
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleSaveSharing")
    Observable<BaseRespBean> createMyShare(@Field("title") String title,
                                         @Field("content") String content,
                                         @Field("contentpicurl") String contentpicurl,
                                         @Field("contentpicsurl") String contentpicsurl,
                                         @Field("picdescribe") String picdescribe,
                                         @Field("ntype") String ntype,
                                         @Field("linkurl") String linkurl,
                                         @Field("authId") String authId);

    /***
     * 查询分享列表
     * @return
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleListSharing")
    Observable<ShareListBean> getShareList(@Field(NetConstants.PARAM_AUTHID) String authId,
                                                    @Field("pageNo") int pageNo);

    /***
     * 点赞分享
     * status（00:撤销点赞，01:点赞），likid（主键），
      likedid（分享id），likeduid（分享的发布人uid），
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleSaveLikeForSharing")
    Observable<BaseRespBean> priseShare(@Field("status") String status,
                                        @Field("likid") String likid,
                                        @Field("likedid") String likedid,
                                        @Field("likeduid") String likeduid,
                                         @Field(NetConstants.PARAM_AUTHID) String authId);

    /***
     * 删除分享
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleRemoveSharing")
    Observable<BaseRespBean> deleteMyShare(@Field("sid") String sid,
                                           @Field(NetConstants.PARAM_AUTHID) String authId);

    /***
     * 评论分享或删除评论
     * status（00:删除评论，01:评论），cid（主键），comid（分享id），
       comuid（分享发布人uid），content（内容），authId
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleSaveCommentForSharing")
    Observable<BaseRespBean> editShareComment(@Field("status") String status,
                                              @Field("cid") String cid,
                                              @Field("comid") String comid,
                                              @Field("comuid") String comuid,
                                              @Field("content") String content,
                                              @Field(NetConstants.PARAM_AUTHID) String authId);

}
