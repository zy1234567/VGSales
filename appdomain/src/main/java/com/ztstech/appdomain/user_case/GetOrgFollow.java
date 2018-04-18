package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.OrgFollowApi;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;

import io.reactivex.Observable;

/**
 *
 * @author smm
 * @date 2017/11/14
 */

public class GetOrgFollow implements UserCase<Observable<OrgFollowlistBean>> {

    /**
     * 我开拓的
     */
    public static final int STATUS_INDEX_CONCERN = 0;
    /**
     * 商家介绍
     */
    public static final int STATUS_INDEX_CLAIM = 1;
    /**
     * 机会抢单
     */
    public static final int STATUS_INDEX_MANAGER = 2;

    /**
     * 待审批
     */
    public static final int STATUS_INDEX_FEEDBACK = 3;

    private OrgFollowApi api;

    private int pageNo;

    /** 查询的是哪个状态的列表 */
    private int status;

    /** 查询其他人的客户跟进列表要传uid 看自己的传null*/
    private String uid;

    public GetOrgFollow(int status,int pageNo){
        api = RetrofitUtils.createService(OrgFollowApi.class);
        this.status = status;
        this.pageNo = pageNo;
    }

    @Override
    public Observable<OrgFollowlistBean> run() {
        if (status == STATUS_INDEX_FEEDBACK){
            return api.queryFeedBackList(UserRepository.getInstance().getAuthId(),pageNo);
        }else {
            return api.queryList(UserRepository.getInstance().getAuthId(), getFlag(), pageNo);
        }
    }

    public String getFlag() {
        if (status == STATUS_INDEX_CONCERN){
            return "00";
        }else if (status == STATUS_INDEX_CLAIM){
            return "01";
        }else {
            return "02";
        }
    }
}
