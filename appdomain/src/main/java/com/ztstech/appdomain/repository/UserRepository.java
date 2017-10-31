package com.ztstech.appdomain.repository;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.domain.AdminUser;
import com.ztstech.appdomain.domain.NormalUser;
import com.ztstech.appdomain.domain.User;
import com.ztstech.vgmate.data.api.LoginApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.UpdateUserInfoData;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.appdomain.utils.RetrofitUtils;

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

    private User user;

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
        return user.getUserBean().authId;
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
                    initUser(baseRespBean);
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
        return loginApi.refreshLogin(user.getUserBean().info.phone,
                getAuthId()).doOnNext(new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                //登录成功
                if (userBean.isSucceed()) {
                    UserPreferenceManager.getInstance().cacheUser(userBean);
                    initUser(userBean);
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
                    user.getUserBean().info.banks = bean.banks;
                    user.getUserBean().info.picurl = bean.picurl;
                    user.getUserBean().info.didurl = bean.didurl;
                    user.getUserBean().info.cardImg = bean.cardUrl;
                    user.getUserBean().info.wdistrict = bean.wdistrict;
                    user.getUserBean().info.sex = bean.sex;
                    user.getUserBean().info.did = bean.did;
                    user.getUserBean().info.bname = bean.bname;
                    user.getUserBean().info.cardNo = bean.cardNo;
                    user.getUserBean().info.birthday = bean.birthday;
                    user.getUserBean().info.uname = bean.uname;
                    UserPreferenceManager.getInstance().cacheUser(user.getUserBean());
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
                initUser(userBean);
            }
        });
    }

    public User getCachedBeanSync() {
        initUser(UserPreferenceManager.getInstance().getCachedUserSync());
        return user;
    }

    /**
     * 用户资料是否完善
     * @return
     */
    public boolean isUserInfoCompleted() {
        UserBean userBean = user.getUserBean();
        return !TextUtils.isEmpty(userBean.info.picurl)
                && !TextUtils.isEmpty(userBean.info.birthday)
                && !TextUtils.isEmpty(userBean.info.uname)
                && !TextUtils.isEmpty(userBean.info.didurl)
                && !TextUtils.isEmpty(userBean.info.cardImg)
                && !TextUtils.isEmpty(userBean.info.sex)
                && !TextUtils.isEmpty(userBean.info.bname)
                && !TextUtils.isEmpty(userBean.info.did)
                && !TextUtils.isEmpty(userBean.info.banks)
                && !TextUtils.isEmpty(userBean.info.cardNo)
                && !TextUtils.isEmpty(userBean.info.wdistrict);
    }


    public User getUser() {
        return user;
    }


    /**
     * 初始化用户
     * @param userBean
     */
    private void initUser(UserBean userBean) {
        if (userBean.info != null && userBean.info.salelev == Constants.LEV_ADMIN) {
            user = new AdminUser(userBean);
        }else {
            user = new NormalUser(userBean);
        }
    }
}
