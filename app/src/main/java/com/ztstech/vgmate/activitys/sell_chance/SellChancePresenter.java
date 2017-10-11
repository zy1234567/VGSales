package com.ztstech.vgmate.activitys.sell_chance;

import com.ztstech.vgmate.activitys.PresenterImpl;

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

    }
}
