package com.ztstech.vgmate.activitys.splash;

import android.content.Intent;
import android.os.Handler;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.complete_info.FillInfoActivity;
import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.data.repository.UserRepository;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onViewBindFinish() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUserLogin()) {
                    toFillInfo();
                }else {
                    toLogin();
                }
            }
        }, 2000);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }


    /**
     * 跳转填写资料
     */
    private void toFillInfo() {
        startActivity(new Intent(this, FillInfoActivity.class));
        finish();
    }

    private void toLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private boolean isUserLogin() {
        return UserRepository.getInstance().isUserLogined();
    }
}
