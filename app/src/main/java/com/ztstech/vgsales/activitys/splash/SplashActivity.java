package com.ztstech.vgsales.activitys.splash;

import android.content.Intent;
import android.os.Handler;

import com.ztstech.vgsales.R;
import com.ztstech.vgsales.activitys.main.MainActivity;
import com.ztstech.vgsales.base.BaseActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onViewBindFinish() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
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
}
