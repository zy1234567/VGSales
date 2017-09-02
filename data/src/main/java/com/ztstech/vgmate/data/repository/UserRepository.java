package com.ztstech.vgmate.data.repository;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ztstech.vgmate.data.api.LoginApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UpdateUserInfoBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhiyuan on 2017/8/22.
 */

public class UserRepository {


    /**登录*/
    public static final String TYPE_LOGIN = "00";
    /**修改手机号码*/
    public static final String TYPE_CHANGE = "01";


    private static UserRepository instance;

    private LoginApi loginApi;

    private UserBean user;

    private UserRepository() {
        loginApi = RetrofitUtils.createService(LoginApi.class);
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                    //初始化数据
                    instance.getCachedBeanSync();
                }
            }
        }
        return instance;
    }

    /**
     * 获取authid
     * @return
     */
    public String getAuthId() {
        return user.authId;
    }



    /**
     * 发送登录验证码
     * @param phone
     * @return
     */
    public Observable<BaseRespBean> sendLoginCode(@NonNull String phone) {
        return loginApi.sendLoginCode(phone);
    }

    /**
     * 登录
     * @param phone
     * @param code
     * @return
     */
    public Observable<UserBean> login(@NonNull String phone, @NonNull String code) {
        return loginApi.login(phone, code, TYPE_LOGIN).doOnNext(new Action1<UserBean>() {
            @Override
            public void call(UserBean baseRespBean) {
                if (baseRespBean.isSucceed()) {
                    //登录成功
                    UserPreferenceManager.getInstance().onLoginSucceed(baseRespBean);
                    user = baseRespBean;
                }
            }
        });
    }

    /**
     * 用户是否登录
     * @return
     */
    public boolean isUserLogined() {
        return UserPreferenceManager.getInstance().isUserLogined();
    }

    /**
     * 更新用户信息
     * @param bean
     * @return
     */
    public Observable<BaseRespBean> updateUserInfo(UpdateUserInfoBean bean) {
        return loginApi.updateUserInfo(bean.picurl, bean.didurl, bean.cardUrl, bean.sex, bean.did,
                bean.bname, bean.banks, bean.status, bean.cardNo);
    }

    /**
     * 获取缓存用户
     * @return
     */
    public Observable<UserBean> getCachedBeanAsync() {
        return UserPreferenceManager.getInstance().getCachedUserAsync().doOnNext(
                new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                user = userBean;
            }
        });
    }

    public UserBean getCachedBeanSync() {
        user = UserPreferenceManager.getInstance().getCachedUserSync();
        return user;
    }

    /**
     * 用户资料是否完善
     * @return
     */
    public boolean isUserInfoCompleted() {
        return !TextUtils.isEmpty(user.info.picurl);
    }



    /**
     * 获取内存用户
     * @return
     */
    public UserBean getUser() {
        return user;
    }
}
