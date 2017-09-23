package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.OrgListApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/23.
 */

public class GetOrgListItems implements UserCase<Observable<GetOrgListItemsBean>> {

    private String status;

    private String locationId;

    private int pageNo;

    private OrgListApi api;

    public GetOrgListItems() {
        api = RetrofitUtils.createService(OrgListApi.class);
    }

    public GetOrgListItems(String status, String locationId, int pageNo) {
        api = RetrofitUtils.createService(OrgListApi.class);
        this.setQueryInfo(status, locationId, pageNo);
    }

    public void setQueryInfo(String status, String locationId, int pageNo) {
        this.locationId = locationId;
        this.status = status;
        this.pageNo = pageNo;
    }

    @Override
    public Observable<GetOrgListItemsBean> run() {
        return api.loadListItems(status, locationId, pageNo,
                UserRepository.getInstance().getAuthId());
    }
}
