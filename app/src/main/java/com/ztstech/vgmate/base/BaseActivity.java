package com.ztstech.vgmate.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.data.events.LogoutEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        onSuperCreateFinish(savedInstanceState);
        setContentView(getLayoutRes());
        unbinder = ButterKnife.bind(this);
        onViewBindFinish(savedInstanceState);
        onViewBindFinish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {}

    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {}

    protected void onViewBindFinish() {}



    protected abstract int getLayoutRes();


    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent logoutEvent) {
        if (!isFinishing() && !(this instanceof LoginActivity)) {
            finish();
        }
    }
}
