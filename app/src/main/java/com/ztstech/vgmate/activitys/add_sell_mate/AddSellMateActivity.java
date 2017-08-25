package com.ztstech.vgmate.activitys.add_sell_mate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

/**
 * 添加销售伙伴
 */
public class AddSellMateActivity extends MVPActivity<AddSellMateContract.Presenter> implements
        AddSellMateContract.View {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_sell_mate;
    }


    @Override
    protected AddSellMateContract.Presenter initPresenter() {
        return new AddSellMatePresenter(this);
    }

}
