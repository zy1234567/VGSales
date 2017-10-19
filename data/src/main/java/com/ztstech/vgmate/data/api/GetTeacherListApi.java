package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.TeacherListBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/19.
 * 查找教师列表
 */

public interface GetTeacherListApi {


    @GET("/exempt/AppSalesGetTeacherByRbiId")
    Observable<TeacherListBean> getTeacherList(
                                               @Query("rbiid") int rbiid,
                                               @Query(NetConstants.PARAM_AUTHID) String authId);

}
