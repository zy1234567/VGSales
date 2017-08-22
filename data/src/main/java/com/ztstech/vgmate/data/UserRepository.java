package com.ztstech.vgmate.data;

import android.support.annotation.NonNull;

import com.ztstech.vgmate.data.api.LoginApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

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

    private UserRepository() {
        loginApi = RetrofitUtils.createService(LoginApi.class);
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
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
    public Observable<BaseRespBean> login(@NonNull String phone, @NonNull String code) {
        return loginApi.login(phone, code, TYPE_LOGIN);
    }
}
