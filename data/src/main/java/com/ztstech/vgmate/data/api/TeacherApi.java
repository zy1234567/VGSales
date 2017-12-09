package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.TeacherListBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/19.
 * 教师相关api
 */

public interface TeacherApi {


    @GET("/exempt/AppSalesGetTeacherByRbiId")
    Observable<TeacherListBean> getTeacherList(
                                               @Query("rbiid") int rbiid,
                                               @Query(NetConstants.PARAM_AUTHID) String authId);

    @POST("/exempt/AppSalesAddTeacher")
    Observable<BaseRespBean> addTeacher(@Query("rbiid") int rbiid,
                                        @Query("name") String name,
                                        @Query("age") String age,
                                        @Query("tecphone") String tecphone,
                                        @Query("sex") String sex,
                                        @Query("position") String position,
                                        @Query("introduction") String introduction,
                                        @Query("logourl") String logourl,
                                        @Query("logosurl") String logosurl,
                                        @Query(NetConstants.PARAM_AUTHID) String authId);

}
