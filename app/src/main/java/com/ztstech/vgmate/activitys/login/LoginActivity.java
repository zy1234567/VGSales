package com.ztstech.vgmate.activitys.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.complete_info.FillInfoActivity;
import com.ztstech.vgmate.activitys.main.MainActivity;
import com.ztstech.vgmate.data.events.LogoutEvent;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends MVPActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.btn_next)
    Button btNext;
    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @BindView(R.id.et_code)
    TextInputEditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                tvGetCode.setEnabled(editable.length() == 11);
            }
        });

        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                btNext.setEnabled(editable.length() == 6);
            }
        });

        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvGetCode.setEnabled(false);
                etPhone.setEnabled(false);
                mPresenter.sendCode(etPhone.getText().toString());
            }
        });

    }

    @Override
    public void sendCodeFinish(@Nullable String errorMessage) {
        if (errorMessage == null) {
            tvGetCode.setEnabled(false);
        }
    }

    @Override
    public void loginFinish(@Nullable String errorMessage) {
        if (errorMessage == null) {
            if (mPresenter.isUserinfoCompleted()) {
                startActivity(new Intent(this, MainActivity.class));
            }else {
                startActivity(new Intent(this, FillInfoActivity.class));
            }
            finish();
        }else {
            ToastUtil.toastCenter(this, "登录失败：" + errorMessage);
        }
    }

    @Override
    public void updateSeconds(int second) {
        if (isFinishing()) {
            return;
        }
        if (second == 0) {
            tvGetCode.setEnabled(true);
            tvGetCode.setText("获取验证码");
            etPhone.setEnabled(true);
        }else {
            tvGetCode.setEnabled(false);
            tvGetCode.setText(String.valueOf(second));
        }
    }

    /**
     * 点击下一步
     * @param view
     */
    public void onNextClick(View view) {
        mPresenter.login(etPhone.getText().toString(), etCode.getText().toString());
    }

    @Override
    public void onLogout(LogoutEvent logoutEvent) {

    }
}

