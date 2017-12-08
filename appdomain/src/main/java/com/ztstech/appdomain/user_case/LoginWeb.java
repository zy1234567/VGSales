package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QRCodeApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/11/2.
 * 登录服务器
 */

public class LoginWeb implements UserCase<Observable<BaseRespBean>>{

    private String uuid;
    private QRCodeApi api;

    public LoginWeb(String uuid) {
        this.uuid = uuid;
        this.api = RetrofitUtils.createQrcodeService(QRCodeApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.login(UserRepository.getInstance().getUser().getUserBean().info.phone,
                uuid,
                UserRepository.getInstance().getAuthId());
    }
}
