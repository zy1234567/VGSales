package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.MainPageApi;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.user_case.UserCase;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/24.
 * 查询主界面相关信息
 */

public class GetMainPageBean implements UserCase<Observable<MainPageBean>>{

    private MainPageApi api;
    private String district;

    public GetMainPageBean(String district) {
        api = RetrofitUtils.createService(MainPageApi.class);
        this.district = district;
    }

    @Override
    public Observable<MainPageBean> run() {
        return api.loadMainPageInfo(UserRepository.getInstance().getAuthId(), district);
    }
}
