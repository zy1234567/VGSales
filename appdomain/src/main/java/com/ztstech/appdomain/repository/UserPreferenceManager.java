package com.ztstech.appdomain.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.ztstech.vgmate.data.beans.UserBean;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

import io.reactivex.Observable;



/**
 * Created by zhiyuan on 2017/8/23.
 */

public class UserPreferenceManager {

    private static final String USER = "pref_user_";
    /**用户筛选的地区*/
    public static final String USER_SELECT_AREA = "pref_user_select_area_";

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
        return Observable.create(new ObservableOnSubscribe<UserBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<UserBean> e) throws Exception {
                String data = preferences.getString(USER, null);
                if (data == null) {
                    e.onError(new NullPointerException("无此用户"));
                }else {
                    UserBean bean = new Gson().fromJson(data, UserBean.class);
                    e.onNext(bean);
                }
                e.onComplete();
            }
        });
    }

    public UserBean getCachedUserSync() {
        String data = preferences.getString(USER, null);
        if (data != null) {
            return new Gson().fromJson(data, UserBean.class);
        }
        return null;
    }


    /**
     * 保存用户区县机构，在筛选界面区县机构进行更改后需要保存
     * @param area
     */
    public void saveUserSelectArea(String area) {
        if (UserRepository.getInstance().getUser() == null) {
            throw new NullPointerException("用户不能为空!!");
        }
        String uid = UserRepository.getInstance().getUser().getUserBean().info.uid;
        preferences.edit().putString(USER_SELECT_AREA.concat(uid), area).apply();
    }

    /**
     * 获取用户在筛选界面保存的区划，如果没有选择地址返回用户默认地址
     * @return
     */
    public String getUserSelectArea() {
        if (UserRepository.getInstance().getUser() == null) {
            throw new NullPointerException("用户不能为空!!");
        }
        String uid = UserRepository.getInstance().getUser().getUserBean().info.uid;
        String loc = preferences.getString(USER_SELECT_AREA.concat(uid), null);
        if (loc == null) {
            loc = UserRepository.getInstance().getUser().getUserBean().info.wdistrict;
        }
        return loc;
    }


}
