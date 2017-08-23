package com.ztstech.vgmate.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by zhiyuan on 2017/8/23.
 */

public class UserPreferenceManager {

    public static final String USER_LOGINED = "pref_user_logined";


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
        return preferences.getBoolean(USER_LOGINED, false);
    }

    public void onLoginSucceed() {
        preferences.edit().putBoolean(USER_LOGINED, true).apply();
    }



}
