package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.OrgListApi;
import com.ztstech.vgmate.data.beans.OrglistUnApproveBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/14.
 * 获取未审批列表
 */

public class GetUnApproveList implements UserCase<Observable<OrglistUnApproveBean>> {


    private String district;
    private int pageNo;

    private OrgListApi api;

    public GetUnApproveList(String district, int pageNo) {
        this.district = district;
        this.pageNo = pageNo;

        this.api = RetrofitUtils.createService(OrgListApi.class);
    }

    @Override
    public Observable<OrglistUnApproveBean> run() {
        return api.loadUnApproveItems(district, pageNo, UserRepository.getInstance().getAuthId());
    }
}
