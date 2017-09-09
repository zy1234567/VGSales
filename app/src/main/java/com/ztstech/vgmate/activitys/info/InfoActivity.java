package com.ztstech.vgmate.activitys.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 编辑个人资料
 */
public class InfoActivity extends MVPActivity<InfoContract.Presenter> implements InfoContract.View {


    @BindView(R.id.top_bar)
    TopBar topBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fill_info;
    }

    @Override
    protected InfoContract.Presenter initPresenter() {
        return new InfoPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        topBar.setTitle("个人资料");

    }
}
