package com.ztstech.vgmate.activitys.add_sell_mate;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.dto.AddSellMateData;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class AddSellMatePresenter extends PresenterImpl<AddSellMateContract.View> implements
        AddSellMateContract.Presenter {

    public AddSellMatePresenter(AddSellMateContract.View view) {
        super(view);
    }

    @Override
    public void submit(AddSellMateData addSellMateData) {

    }
}
