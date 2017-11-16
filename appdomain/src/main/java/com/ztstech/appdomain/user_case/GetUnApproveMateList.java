package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.MateApproveApi;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/16.
 */

public class GetUnApproveMateList  implements UserCase<Observable<WaitApproveMateListBean>>{

    private int page;

    private MateApproveApi api;

    public GetUnApproveMateList(int page){
        this.page = page;
        api = RetrofitUtils.createService(MateApproveApi.class);
    }

    @Override
    public Observable<WaitApproveMateListBean> run() {
        return api.queryList(UserRepository.getInstance().getAuthId()
                ,UserRepository.getInstance().getUser().getUserBean().info.uid,page);
    }
}
