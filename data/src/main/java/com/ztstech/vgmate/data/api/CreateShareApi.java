package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

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
     * 创建分享
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
}
