package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.SellChanceApi;
import com.ztstech.vgmate.data.beans.SellChanceBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/11.
 * 获取销售机会
 */

public class GetSellChance implements UserCase<Observable<SellChanceBean>> {

    private SellChanceApi api;
    private int currentPage;
    private String status;

    public GetSellChance(int page, String status) {
        this.currentPage = page;
        this.status = status;
        this.api = RetrofitUtils.createService(SellChanceApi.class);
    }


    @Override
    public Observable<SellChanceBean> run() {
        return api.getSellChance(status, currentPage, UserRepository.getInstance().getAuthId());
    }
}
