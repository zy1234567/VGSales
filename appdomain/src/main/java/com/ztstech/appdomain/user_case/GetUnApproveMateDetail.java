package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.GetMateDetailApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;

import rx.Observable;

/**
 *
 * @author smm
 * @date 2017/11/17
 */

public class GetUnApproveMateDetail implements UserCase<Observable<UnApproveMateBean>>{

    /** 所查询的销售uid */
    public String saleid;

    private GetMateDetailApi api;

    public GetUnApproveMateDetail(String saleid){
        this.saleid = saleid;
        api = RetrofitUtils.createService(GetMateDetailApi.class);
    }

    @Override
    public Observable<UnApproveMateBean> run() {
        return api.queryList(UserRepository.getInstance().getAuthId(),saleid);
    }
}
