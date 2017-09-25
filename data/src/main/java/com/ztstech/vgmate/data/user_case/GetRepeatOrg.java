package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.RepeatOrgApi;
import com.ztstech.vgmate.data.beans.RepeatOrgBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/25.
 * 获取重复机构
 */

public class GetRepeatOrg implements UserCase<Observable<RepeatOrgBean>> {

    private RepeatOrgApi repeatOrgApi;

    private String rbidistrict;
    private String rbioname;
    private int rbiid;
    private int pageNo;

    public GetRepeatOrg(String rbidistrict, String rbioname, int rbiid, int pageNo) {
        repeatOrgApi = RetrofitUtils.createService(RepeatOrgApi.class);
        this.rbidistrict = rbidistrict;
        this.rbiid = rbiid;
        this.rbioname = rbioname;
        this.pageNo = pageNo;
    }

    @Override
    public Observable<RepeatOrgBean> run() {
        return repeatOrgApi.getRepeatOrg(UserRepository.getInstance().getAuthId(),
                rbidistrict,
                rbioname,
                RepeatOrgApi.STATUS_SEARCH,
                rbiid,
                pageNo);
    }
}
