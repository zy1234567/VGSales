package com.ztstech.vgmate.activitys.splash;

import android.content.Intent;
import android.os.Handler;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.activitys.main.MainActivity;
import com.ztstech.vgmate.base.BaseActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onViewBindFinish() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUserLogin()) {
                    toMain();
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
     * 跳转主界面
     */
    private void toMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void toLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private boolean isUserLogin() {
        return false;
    }
}
