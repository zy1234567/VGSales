package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/16.
 */

public interface OrgInfoApi {

    /**
     * 查询机构资料
     * @param rbiid
     * @param authId
     * @return
     */
    @GET("exempt/AppSaleBackOrgMsg")
    Observable<OrgInfoBean> getOrgInfo(@Query("rbiid") int rbiid,
                                       @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 删除机构资料
     * @param rbiid
     * @param delmsg
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleDelRbiByRbiId")
    Observable<BaseRespBean> deleteOrgInfo(@Query("rbiid") String rbiid,
                                           @Query("delmsg") String delmsg,
                                           @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 审核通过
     * @return
     */
    @POST("exempt/AppSaleAuditRBI")
    Observable<BaseRespBean> confirmOrgInfo(@Query("repeatrbiid") String repeatrbiid,
                                            @Query("rbiid") String rbiid,
                                            @Query("rbibackmsg") String rbibackmsg,
                                            @Query("rbiostatus") String rbiostatus,
                                            @Query("oname") String oname,
                                            @Query("otype") String otype,
                                            @Query("district") String district,
                                            @Query("address") String address,
                                            @Query("gps") String gps,
                                            @Query("contphone") String contphone,
                                            @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 编辑机构资料
     * @param rbiid 机构rbiid
     * @param oname 机构名
     * @param advertisingwall 照片墙
     * @param advertisingwallsurl 照片墙缩略图
     * @param walldescribe 照片墙描述
     * @param introduction 机构简介
     * @param courseintroduction 课程简介
     * @param tollintroduction 收费简介
     * @param tag 机构标签
     * @param contphone 联系电话
     * @param manager 负责人姓名
     * @param managerphone 负责人手机号
     * @param logourl 机构logo
     * @param logosurl 机构logo缩略图
     * @return
     */
    @POST("exempt/AppSaleUpdateRbiOrg")
    Observable<BaseRespBean> editOrgInfo(@Query("rbiid") String rbiid,
                                         @Query("oname") String oname,
                                         @Query("advertisingwall") String advertisingwall,
                                         @Query("advertisingwallsurl") String advertisingwallsurl,
                                         @Query("walldescribe") String walldescribe,
                                         @Query("introduction") String introduction,
                                         @Query("courseintroduction") String courseintroduction,
                                         @Query("tollintroduction") String tollintroduction,
                                         @Query("tag") String tag,
                                         @Query("contphone") String contphone,
                                         @Query("manager") String manager,
                                         @Query("managerphone") String managerphone,
                                         @Query("logourl") String logourl,
                                         @Query("logosurl") String logosurl,
                                         @Query(NetConstants.PARAM_AUTHID) String authId);
}
