package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.GetComRecordBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2018/1/15.
 */

public interface AddComRecordApi {
    /**
     * 添加沟通记录
     * @param makecall 本次拨打电话
     * @param communicationtype 沟通方式,00:电话沟通,01:上门拜访
     * @param spotgps 实地定位gps
     * @param spotphotos 实地照片
     * @param consultphone 咨询电话
     * @param contactsname 联系人
     * @param authId
     * @param roletype 担任职位
     * @param backstage 管理后台 00:没有,01:有'
     * @param callon 是否约下次拜访,00:否,01:是
     * @param followtype 跟进方式 01:加速跟进,02:正常跟进,03:长期跟进,04:暂不跟进
     * @param contactsphone 联系人手机
     * @param msg 内容
     * @return
     */
    @FormUrlEncoded
    @POST("exempt/appMapSaleSaveCommunicationRecord")
    Observable<BaseRespBean> addcomRecord(@Field("makecall") String makecall,
                                          @Field("communicationtype") String communicationtype,
                                          @Field("spotgps") String spotgps,
                                          @Field("spotphotos") String spotphotos,
                                          @Field("consultphone") String consultphone,
                                          @Field("contactsname") String contactsname,
                                          @Field("roletype") String roletype,
                                          @Field("backstage") String backstage,
                                          @Field("callon") String callon,
                                          @Field("followtype") String followtype,
                                          @Field("contactsphone") String contactsphone,
                                          @Field("msg") String msg,
                                          @Field("rbiid") String rbiid,
                                          @Field("authId") String authId);

    @POST("exempt/appMapSalesGetRecordByRbiId")
    Observable<GetComRecordBean> getcomRecord(@Query("rbiid") String rbiid,
                                              @Query("pageNo") int pageNo,
                                              @Query("authId") String authId);
}
