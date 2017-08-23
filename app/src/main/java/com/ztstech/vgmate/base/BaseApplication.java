package com.ztstech.vgmate.base;

import android.support.multidex.MultiDexApplication;

import com.ztstech.vgmate.data.repository.UserPreferenceManager;

/**
 * Created by zhiyuan on 2017/7/27.
 * app 实例
 */

public class BaseApplication extends MultiDexApplication {

    private static BaseApplication applicationInstance;

    public static BaseApplication getApplicationInstance() {
        return applicationInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationInstance = this;
        UserPreferenceManager.getInstance().initPreference(this);
    }
}
