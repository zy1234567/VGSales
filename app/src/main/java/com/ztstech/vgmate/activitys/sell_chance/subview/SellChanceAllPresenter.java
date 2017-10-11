package com.ztstech.vgmate.activitys.sell_chance.subview;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.api.SellChanceApi;
import com.ztstech.vgmate.data.beans.SellChanceBean;
import com.ztstech.vgmate.data.user_case.GetSellChance;
import com.ztstech.vgmate.utils.PresenterSubscriber;

/**
 * Created by zhiyuan on 2017/8/24.
 * 提供销售机会界面presenter
 */

public class SellChanceAllPresenter extends PresenterImpl<SellChanceAllContract.View> implements
        SellChanceAllContract.Presenter {

    private int currentPage = 1;
    private int totalPage = 2;

    public SellChanceAllPresenter(SellChanceAllContract.View view) {
        super(view);
    }


    @Override
    public void refreshData() {
        currentPage = 1;
        loadData();
    }

    @Override
    public void appendData() {

    }

    private void loadData() {
        new PresenterSubscriber<SellChanceBean>() {

            @Override
            public void onNext(SellChanceBean sellChanceBean) {

                if (currentPage == 1) {
                    //刷新成功

                }
            }
        }.run(new GetSellChance(currentPage, SellChanceApi.STATUS_ALL).run());
    }
}
