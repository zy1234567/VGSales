package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/4/21.
 */

public interface RefuseOrPassReasonApi {
    /**
     * 机构认领来的机构走的审核接口
     * @param rbiid
     * @param calid
     * @param identificationtype
     * @param status
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleAuditClaims")
    Observable<BaseRespBean> approveClaimOrg(@Query("rbiid") String rbiid,
                                             @Query("calid") String calid,
                                             @Query("identificationtype") String identificationtype,
                                             @Query("status") String status,
                                             @Query("testorg") String testorg,
                                             @Query("authId") String authId);

//    /**
//     * 登记来的机构审核通过的接口
//     * @param rbiid
//     * @param rbiostatus 00打点生效
//     * @param flag 固定传00没有沟通记录
//     * @param identificationtype
//     * @param confirmOrUpdateType 传固定值00确认
//     * @return
//     */
//    @POST("exempt/AppSaleAuditRBI")
//    Observable<BaseRespBean> appregisterOrgyes(@Query("rbiid") String rbiid,
//                                               @Query("rbiostatus") String rbiostatus,
//                                               @Query("flag") String flag,
//                                               @Query("identificationtype") String identificationtype,
//                                               @Query("confirmOrUpdateType") String confirmOrUpdateType,
//                                               @Query("testorg") String testorg,
//                                               @Query("authId") String authId);

    /**
     * 登记来的机构审核拒绝接口
     * @param rbiid
     * @param authId
     * @param type 01删除（回收站）    00恢复
     * @return【
     */
    @POST("exempt/appMapsaleRecyclingRbi")
    Observable<BaseRespBean> appregisterOrgno(@Query("rbiid") String rbiid,
                                              @Query("authId") String authId,
                                              @Query("type") String type,
                                              @Query("rubbishtype") String rubbishtype,
                                              @Query("refuse") String refuse,
                                              @Query("oname") String oname);

    /**
     * 路人登记 定位认证
     * 机构登记 加V认证
     *  @param: authId
     * @param: rbiid 机构rbiid
     * @param: rbiostatus 00：审核通过
     * @param: identificationtype 01:定位认证,02:加V认证
     * @param: terminal 处理终端，01：客服平台，02：其他
     * @param: type 00机构沟通记录，01机会沟通记录，02打点机构沟通记录
     * @param: communicationtype 沟通方式,00:电话沟通,01:上门拜访,03:远程审核
     * @param: contactsname 联系人姓名
     * @param: contactsphone 联系人手机
     * @param: roletype 担任职位
     * @param: msg 沟通内容
     * @param: description 补充说明
     * @param: spotgps 实地定位
     * @param: spotphotos 实地拍照
     * @param: wechatid 微信号码
     * @param: videopicurl 视频通话截图
     * @param: positionpicurl 位置共享截图
     */
    @POST("exempt/AppSaleNewAuditRBI")
        Observable<BaseRespBean> nomalorOrgRegister(@Query("authId") String authid,
                                                    @Query("rbiid") String rbiid,
                                                    @Query("rbiostatus") String rbiostatus,
                                                    @Query("identificationtype") String identificationtype,
                                                    @Query("terminal") String terminal,
                                                    @Query("type") String type,
                                                    @Query("communicationtype") String communicationtype,
                                                    @Query("contactsname") String contactsname,
                                                    @Query("contactsphone") String roletype,
                                                    @Query("msg") String msg,
                                                    @Query("description") String description,
                                                    @Query("spotgps") String spotgps,
                                                    @Query("spotphotos") String spotphotos,
                                                    @Query("wechatid") String wechatid,
                                                    @Query("videopicurl") String videopicurl,
                                                    @Query("positionpicurl") String positionpicurl);
}
