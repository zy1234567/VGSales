package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dongdong on 2018/3/26.
 */

public interface UploadProtocolApi {
    /**
     *
     * @param orgid
     * @param oname 机构名
     * @param authId
     * @param teamworkprotocalpicurl 合作伙伴协议
     * @param posterpicurl 海报张贴效果图
     * @param secretagreementpicurl 保密协议
     * @param promisebookpicurl 承诺书
     * @return
     */
    @FormUrlEncoded
    @POST("exempt/AppSaleUploadProtocol")
    Observable<BaseRespBean> uploadprotocol(@Field("authId") String authId,
                                            @Field("orgid") String orgid,
                                            @Field("oname") String oname,
                                            @Field("teamworkprotocalpicurl") String teamworkprotocalpicurl,
                                            @Field("posterpicurl") String posterpicurl,
                                            @Field("secretagreementpicurl") String secretagreementpicurl,
                                            @Field("promisebookpicurl") String promisebookpicurl);
}
