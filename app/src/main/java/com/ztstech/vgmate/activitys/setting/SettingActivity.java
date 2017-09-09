package com.ztstech.vgmate.activitys.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

/**
 * 设置界面
 */
public class SettingActivity extends MVPActivity<SettingContract.Presenter> implements SettingContract.View {


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingContract.Presenter initPresenter() {
        return new SettingPresenter(this);
    }
}
