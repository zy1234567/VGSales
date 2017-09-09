package com.ztstech.vgmate.activitys.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.setting.SettingActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑个人资料
 */
public class EditInfoActivity extends MVPActivity<InfoContract.Presenter> implements InfoContract.View {


    @BindView(R.id.top_bar)
    TopBar topBar;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected InfoContract.Presenter initPresenter() {
        return new InfoPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

    }

    @OnClick(R.id.tv_setting)
    public void onSettingClick(View v) {
        startActivity(new Intent(this, SettingActivity.class));
    }
}
