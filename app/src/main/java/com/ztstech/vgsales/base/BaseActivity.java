package com.ztstech.vgsales.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
        onSuperCreateFinish(savedInstanceState);
        setContentView(getLayoutRes());
        unbinder = ButterKnife.bind(this);
        onViewBindFinish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {}

    protected void onViewBindFinish() {}


    protected abstract int getLayoutRes();
}
