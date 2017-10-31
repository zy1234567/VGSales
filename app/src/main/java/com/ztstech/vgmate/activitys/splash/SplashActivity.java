package com.ztstech.vgmate.activitys.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.complete_info.FillInfoActivity;
import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.activitys.main.MainActivity;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.utils.LocationUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onViewBindFinish() {
        hideSystemNavigationBar();
        LocationUtils.init(new Runnable() {
            @Override
            public void run() {
                if (UserRepository.getInstance().isUserLogined()) {
                    //刷新登录
                    UserRepository.getInstance().refreshLogin().observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Action1<UserBean>() {
                                @Override
                                public void call(UserBean userBean) {

                                }
                            });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        postJump();
                    }
                });
            }
        });

    }

    private void postJump() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUserLogin()) {
                    if (UserRepository.getInstance().isUserInfoCompleted()) {
                        toMain();
                    }else {
                        toFillInfo();
                    }

                }else {
                    toLogin();
                }
            }
        }, 2000);
    }

    private void hideSystemNavigationBar() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = this.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }


    private void toMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void toLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void toFillInfo() {
        startActivity(new Intent(this, FillInfoActivity.class));
        finish();
    }

    private boolean isUserLogin() {
        return UserRepository.getInstance().isUserLogined();
    }
}
