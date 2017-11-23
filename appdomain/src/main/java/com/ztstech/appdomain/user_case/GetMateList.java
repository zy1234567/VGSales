package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.GetMateDetailApi;
import com.ztstech.vgmate.data.api.GetMateListApi;
import com.ztstech.vgmate.data.beans.MatelistBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/23.
 * 获取销售伙伴列表
 */

public class GetMateList implements UserCase<Observable<MatelistBean>> {

    private int pageNo;

    private GetMateListApi api;

    public GetMateList(int pageNo){
        api = RetrofitUtils.createService(GetMateListApi.class);
        this.pageNo = pageNo;
    }

    @Override
    public Observable<MatelistBean> run() {
        return api.getMateList(UserRepository.getInstance().getAuthId(),pageNo);
    }
}
