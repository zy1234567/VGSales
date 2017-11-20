package com.ztstech.vgmate.activitys.main_fragment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.appdomain.repository.UserPreferenceManager;
import com.ztstech.appdomain.user_case.GetMainPageBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class MainPresenter extends PresenterImpl<MainContract.View> implements
        MainContract.Presenter{

    public static String MAIN_PAGE_BEAN = "main_page_bean";

    private UserRepository userRepository;

    private SharedPreferences preferences;

    public MainPresenter(MainContract.View view) {
        super(view);
        userRepository = UserRepository.getInstance();
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
    }

    @Override
    public void loadData() {
        // 先读取缓存
        String cacheMainJson = preferences.getString(MAIN_PAGE_BEAN,"");
        if (!TextUtils.isEmpty(cacheMainJson)){
            MainPageBean mainPageCacheBean = new Gson().fromJson(cacheMainJson,MainPageBean.class);
            mView.setData(mainPageCacheBean);
        }
        // 网络请求加载主界面数据
        new BasePresenterSubscriber<MainPageBean>(mView,false) {

            @Override
            public void childNext(MainPageBean mainPageBean) {
                if (mainPageBean.isSucceed()) {
                    mView.hideLoading(null);
                    mView.setData(mainPageBean);
                    preferences.edit().putString(MAIN_PAGE_BEAN,new Gson().toJson(mainPageBean)).apply();
                }else {
                    mView.loadError(mainPageBean.getErrmsg());
                }
            }
        }.run(new GetMainPageBean(getUserDistrict()).run());

    }

    @Override
    public void loadUserInfo() {
        //加载用户信息
        UserBean userBean = userRepository.getUser().getUserBean();
        if (userBean != null) {
            mView.setUserInfo(userBean);
        }
    }

    /**
     * 获取默认请求地址
     * @return
     */
    private String getUserDistrict() {
        return UserPreferenceManager.getInstance().getUserSelectArea();
    }
}
