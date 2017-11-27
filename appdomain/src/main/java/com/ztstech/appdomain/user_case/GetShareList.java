package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.vgmate.data.beans.ShareListBean;

import rx.Observable;

/**
 * Created by smm on 2017/11/27.
 */

public class GetShareList implements UserCase<Observable<ShareListBean>> {

    private int pageNo;

    private CreateShareApi api;

    public GetShareList(int pageNo){
        this.pageNo = pageNo;
        api = RetrofitUtils.createService(CreateShareApi.class);
    }

    @Override
    public Observable<ShareListBean> run() {
        return api.getShareList(UserRepository.getInstance().getAuthId(),pageNo);
    }
}
