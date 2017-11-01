package com.ztstech.vgmate.activitys.sell_chance;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.SellChanceCountBean;
import com.ztstech.appdomain.user_case.GetSellChanceCount;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/10/11.
 */

public class SellChancePresenter extends PresenterImpl<SellChanceContract.View> implements
        SellChanceContract.Presenter {

    public SellChancePresenter(SellChanceContract.View view) {
        super(view);
    }

    @Override
    public void loadTitleCount() {
        new BasePresenterSubscriber<SellChanceCountBean>(mView) {

            @Override
            protected void childNext(SellChanceCountBean sellChanceCountBean) {
                mView.onLoadCountFinish(sellChanceCountBean);
            }

        }.run(new GetSellChanceCount().run());
    }
}
