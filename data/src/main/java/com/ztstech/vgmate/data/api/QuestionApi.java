package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.QuestionListBean;
import com.ztstech.vgmate.data.constants.NetConstants;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by smm on 2017/11/22.
 */

public interface QuestionApi {

    /**
     * 发布问题
     * @param descrption (问题描述)
     * @param supplement (问题补充)
     * @return
     */
    @POST("exempt/AppSaleSaveQuestion")
    Observable<BaseRespBean> createQuestion(
            @Query("descrption") String descrption,
            @Query("supplement") String supplement,
            @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 删除问题
     * queid(问题id)
     * @param queid
     * @return
     */
    @POST("exempt/AppSaleDeleteQuestion")
    Observable<BaseRespBean> deleteQuestion(
            @Query("queid") String queid,
            @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 查询问题答案列表
     * @param queid (问题id)
     */
    @POST("exempt/AppSaleGetQuestionDetail")
    Observable<BaseRespBean> getQuestionAnwserList(
            @Query("queid") String queid,
            @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 查询问题列表（包括全部的，我提的，搜索的）
     * @param keyword (关键字)
     * @param pageNo
     * @param myquestion 我的问题 传"01"
     * @return
     */
    @GET("exempt/AppSaleListQuestion")
    Observable<QuestionListBean> getQuestionList(
            @Query("keyword") String keyword,
            @Query("myquestion") String myquestion,
            @Query("pageNo") int pageNo,
            @Query(NetConstants.PARAM_AUTHID) String authId);


    /**
     * 点赞问题
     * @param status (00:撤销点赞,01:点赞)
     * @param likedid (回答id)
     * @param likeduid (回答的发布人uid)
     * @param authId
     * @return
     */
    @POST("exempt/AppSaleSaveUpdateLike")
    Observable<BaseRespBean> priseAnwser(
            @Query("status") String status,
            @Query("likedid") String likedid,
            @Query("likeduid") String likeduid,
            @Query(NetConstants.PARAM_AUTHID) String authId);

    /**
     * 删除答案
     * @param ansid (答案id)
     * @return
     */
    @POST("exempt/AppSaleDeleteAnswer")
    Observable<BaseRespBean> deleteAnswer(
            @Query("ansid") String ansid,
            @Query(NetConstants.PARAM_AUTHID) String authId);

}
