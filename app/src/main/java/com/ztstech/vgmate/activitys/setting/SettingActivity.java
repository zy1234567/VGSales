package com.ztstech.vgmate.activitys.setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;

/**
 * 设置界面
 */
public class SettingActivity extends MVPActivity<SettingContract.Presenter> implements
        SettingContract.View, View.OnClickListener {

    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingContract.Presenter initPresenter() {
        return new SettingPresenter(this);

    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        tvLogout.setOnClickListener(this);
        tvVersion.setText(CommonUtil.getVersion());
        tvPhone.setText(mPresenter.getPhone());

    }

    @Override
    public void onLogoutFinish(String errorMessage) {
        if (errorMessage != null) {
            ToastUtil.toastCenter(this, "登出失败：" + errorMessage);
        }else {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvLogout) {
            mPresenter.logout();
        }
    }
}
