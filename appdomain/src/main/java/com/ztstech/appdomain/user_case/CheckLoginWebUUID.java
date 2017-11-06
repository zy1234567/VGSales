package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.QRCodeApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/11/2.
 * 网页登录之前检查
 */

public class CheckLoginWebUUID implements UserCase<Observable<BaseRespBean>> {

    private QRCodeApi api;
    private String uuid;

    public CheckLoginWebUUID(String uuid) {
        this.uuid = uuid;
        this.api = RetrofitUtils.createService(QRCodeApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.checkUUID(uuid);
    }
}
