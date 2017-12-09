package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.OrgInfoApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/25.
 * 审核通过
 */

public class ConfirmOrgPass implements UserCase<Observable<BaseRespBean>>{

    private OrgInfoApi api;

    private String rbiid, oname, otype, district, address, gps, contphone;

    public ConfirmOrgPass(String rbiid, String oname, String otype, String district,
                          String address, String gps, String contphone) {
        this.rbiid = rbiid;
        this.oname = oname;
        this.otype = otype;
        this.district = district;
        this.address = address;
        this.gps = gps;
        this.contphone = contphone;
        api = RetrofitUtils.createService(OrgInfoApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.confirmOrgInfo("", rbiid, "", "00", oname, otype, district, address, gps,
                contphone, UserRepository.getInstance().getAuthId());
    }
}
