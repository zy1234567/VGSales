package com.ztstech.vgmate.data.repository;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ztstech.vgmate.data.api.LoginApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.UpdateUserInfoData;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.events.LogoutEvent;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

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
        return loginApi.sendLoginCode(phone, "00");
    }

    /**
     * 发送修改手机号验证码
     * @param phone
     * @return
     */
    public Observable<BaseRespBean> sendChangePhoneCode(@NonNull String phone) {
        return loginApi.sendLoginCode(phone, "01");
    }


    /**
     * 检查更换手机验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    public Observable<UserBean> checkChangePhoneCode(@NonNull String phone,
                                                         @NonNull String code) {
        return loginApi.updatePhone(phone, code, "00", null);
    }

    /**
     * 更换手机号
     * @param phone
     * @param code
     * @param newPhone
     * @return
     */
    public Observable<UserBean> updatePhone(@NonNull String phone,
                                            @NonNull String code,
                                            @NonNull String newPhone) {

        return loginApi.updatePhone(phone, code, "01", newPhone);
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
                    UserPreferenceManager.getInstance().cacheUser(baseRespBean);
                    user = baseRespBean;
                }
            }
        });
    }

    /**
     * 登出
     * @return
     */
    public Observable<BaseRespBean> logout() {
        return loginApi.logout(getAuthId()).doOnNext(new Action1<BaseRespBean>() {
            @Override
            public void call(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()) {
                    clearUserInfo();
                }
            }
        });
    }

    /**
     * 刷新登录
     * @return
     */
    public Observable<UserBean> refreshLogin() {
        if (user == null) {
            return null;
        }
        return loginApi.refreshLogin(user.info.phone, getAuthId()).doOnNext(new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                //登录成功
                if (userBean.isSucceed()) {
                    UserPreferenceManager.getInstance().cacheUser(userBean);
                    user = userBean;
                }
            }
        });
    }

    /**
     * 清空用户
     */
    public void clearUserInfo() {
        user = null;
        UserPreferenceManager.getInstance().clearUserInfo();
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
    public Observable<BaseRespBean> updateUserInfo(final UpdateUserInfoData bean) {
        return loginApi.updateUserInfo(getAuthId(), bean.picurl, bean.didurl, bean.cardUrl, bean.sex, bean.did,
                bean.bname, bean.banks, bean.status, bean.cardNo, bean.wdistrict, bean.birthday,
                bean.uid, bean.uname)
                .doOnNext(new Action1<BaseRespBean>() {
            @Override
            public void call(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()) {
                    user.info.banks = bean.banks;
                    user.info.picurl = bean.picurl;
                    user.info.didurl = bean.didurl;
                    user.info.cardImg = bean.cardUrl;
                    user.info.wdistrict = bean.wdistrict;
                    user.info.sex = bean.sex;
                    user.info.did = bean.did;
                    user.info.bname = bean.bname;
                    user.info.cardNo = bean.cardNo;
                    user.info.birthday = bean.birthday;
                    user.info.uname = bean.uname;
                    UserPreferenceManager.getInstance().cacheUser(user);
                }
            }
        });

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
