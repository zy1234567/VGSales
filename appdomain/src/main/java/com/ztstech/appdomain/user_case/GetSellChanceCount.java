package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.SellChanceApi;
import com.ztstech.vgmate.data.beans.SellChanceCountBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

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
