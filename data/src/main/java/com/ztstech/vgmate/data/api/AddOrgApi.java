package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import io.reactivex.Observable;
/**
 * Created by smm on 2017/11/6.
 */

public interface AddOrgApi {

    /** 测试机构标志位 */
    String TEST_ORG = "01";
    String NO_TEST_ORG = "00";

    @FormUrlEncoded
    @POST("exempt/AppSaleRegisterRBI")
    Observable<BaseRespBean> addOrg(@Field("rbioname") String rbioname,
                                         @Field("rbiotype") String rbiotype,
                                         @Field("bigtype") String bigtype,
                                         @Field("rbidistrict") String rbidistrict,
                                         @Field("rbigps") String rbigps,
                                         @Field("authId") String authId,
                                         @Field("rbiaddress") String rbiaddress,
                                         @Field("rbiintroduction") String rbiintroduction,
                                         @Field("rbiphone") String rbiphone,
                                         @Field("testorg") String testorg);

}
