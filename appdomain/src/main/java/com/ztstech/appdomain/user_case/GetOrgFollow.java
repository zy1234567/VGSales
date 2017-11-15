package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.OrgFollowApi;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;

import rx.Observable;

/**
 *
 * @author smm
 * @date 2017/11/14
 */

public class GetOrgFollow implements UserCase<Observable<OrgFollowlistBean>> {

    /**
     * 已确认
     */
    public static final int STATUS_INDEX_CONCERN = 0;
    /**
     * 已认领
     */
    public static final int STATUS_INDEX_CLAIM = 1;
    /**
     * 管理端
     */
    public static final int STATUS_INDEX_MANAGER = 2;

    private OrgFollowApi api;

    private int pageNo;

    /** 查询的是哪个状态的列表 */
    private int status;

    public GetOrgFollow(int status,int pageNo){
        api = RetrofitUtils.createService(OrgFollowApi.class);
        this.status = status;
        this.pageNo = pageNo;
    }

    @Override
    public Observable<OrgFollowlistBean> run() {
        return api.queryList(UserRepository.getInstance().getAuthId(),getStatusParam(),pageNo);
    }

    public String getStatusParam() {
        if (status == STATUS_INDEX_CONCERN){
            return "01";
        }else if (status == STATUS_INDEX_CLAIM){
            return "03";
        }else {
            return "04";
        }
    }
}
