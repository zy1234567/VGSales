package com.ztstech.vgmate.data.repository;

import com.ztstech.vgmate.data.api.MainPageApi;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/8/24.
 * 查询主界面相关信息
 */

public class MainRepository {

    private MainPageApi api;

    public MainRepository() {
        api = RetrofitUtils.createService(MainPageApi.class);
    }

    public Observable<MainPageBean> loadMainPageInfo() {
        return api.loadMainPageInfo();
    }
}
