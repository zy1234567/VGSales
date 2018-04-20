package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.AddVCertificationApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddVData;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/4/20.
 */

public class AddVcertification implements UserCase<BaseRespBean> {
    private AddVData addVData;
    private AddVCertificationApi addVCertificationApi;
    public  AddVCertificationApi(AddVData addVData){
        this.addVData=addVData;
    this.addVCertificationApi= RetrofitUtils.createService(AddVCertificationApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() { {
        return null;
    }
}
