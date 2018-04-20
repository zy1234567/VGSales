package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;


import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/4/20.
 */

public interface AddVCertificationApi {
    @FormUrlEncoded
    @POST("exempt/appMapSaleSaveCommunicationRecord")
    Observable<BaseRespBean> AddVCertification();
}
