package com.ztstech.vgmate.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.ztstech.vgmate.data.beans.UserBean;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;


/**
 * Created by zhiyuan on 2017/8/23.
 */

public class UserPreferenceManager {

    private static final String USER = "pref_user_";

    private static UserPreferenceManager instance;

    private SharedPreferences preferences;

    private UserPreferenceManager() {
    }

    public static UserPreferenceManager getInstance() {
        if (instance == null) {
            synchronized (UserPreferenceManager.class) {
                if (instance == null) {
                    instance = new UserPreferenceManager();
                }
            }
        }
        return instance;
    }

    public void initPreference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public boolean isUserLogined() {
        return preferences.getString(USER, null) != null;
    }

    /**
     * 清空用户信息
     */
    public void clearUserInfo() {
        preferences.edit().remove(USER).apply();
    }


    /**
     * 缓存用户
     * @param user
     */
    public void cacheUser(final UserBean user) {
        new Thread(){
            @Override
            public void run() {
                String cacheData = new Gson().toJson(user);
                preferences.edit().putString(USER, cacheData).apply();
            }
        }.start();
    }

    /**
     * 获取本地用户
     * @return
     */
    public Observable<UserBean> getCachedUserAsync() {
        return Observable.create(new Action1<Emitter<UserBean>>() {
            @Override
            public void call(Emitter<UserBean> userBeanEmitter) {
                String data = preferences.getString(USER, null);
                if (data == null) {
                    userBeanEmitter.onError(new NullPointerException("无此用户"));
                }else {
                    UserBean bean = new Gson().fromJson(data, UserBean.class);
                    userBeanEmitter.onNext(bean);
                }
                userBeanEmitter.onCompleted();
            }
        }, Emitter.BackpressureMode.NONE);
    }

    public UserBean getCachedUserSync() {
        String data = preferences.getString(USER, null);
        if (data != null) {
            return new Gson().fromJson(data, UserBean.class);
        }
        return null;
    }



}
