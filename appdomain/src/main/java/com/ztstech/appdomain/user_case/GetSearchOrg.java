package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.GetSearchOrgListApi;
import com.ztstech.vgmate.data.beans.SearchOrgListBean;

import rx.Observable;

/**
 * Created by smm on 2017/12/8.
 */

public class GetSearchOrg implements UserCase<Observable<SearchOrgListBean.ListBean>>{

    private int page;

    private String rbioname;

    private String rbicity;

    private GetSearchOrgListApi api;

    public GetSearchOrg(int page,String rbioname,String rbicity){
        this.rbioname = rbioname;
        this.rbicity = rbicity;
        this.page = page;
        api = RetrofitUtils.createService(GetSearchOrgListApi.class);
    }

    @Override
    public Observable<SearchOrgListBean.ListBean> run() {
        return api.getSearchOrgList(UserRepository.getInstance().getAuthId(),page,rbicity,rbioname);
    }
}
