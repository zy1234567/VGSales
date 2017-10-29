package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.SellChanceApi;
import com.ztstech.vgmate.data.beans.SellChanceCountBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/29.
 * 获取销售机会
 */

public class GetSellChanceCount implements UserCase<Observable<SellChanceCountBean>> {

    private SellChanceApi api;

    public GetSellChanceCount() {
        api = RetrofitUtils.createService(SellChanceApi.class);
    }

    @Override
    public Observable<SellChanceCountBean> run() {
        return api.getSellChanceCount(UserRepository.getInstance().getAuthId());
    }
}
