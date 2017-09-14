package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

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
    @POST("exempt/AppSaleMakeNews")
    Observable<BaseRespBean> createShare(@Query("title") String title,
                                         @Query("summary") String summary,
                                         @Query("contentpicurl") String contentpicurl,
                                         @Query("contentpicsurl") String contentpicsurl,
                                         @Query("picurl") String picurl,
                                         @Query("picsurl") String picsurl,
                                         @Query("picdescribe") String picdescribe,
                                         @Query("type") String type,
                                         @Query("url") String url,
                                         @Query("authId") String authId);
}
