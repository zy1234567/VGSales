package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.UploadProtocolApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.UploadProtocolData;
import io.reactivex.Observable;
/**
 * Created by dongdong on 2018/3/26.
 */

public class UploadProtocol {
    private UploadProtocolApi api;
    private UploadProtocolData data;
    public UploadProtocol(UploadProtocolData data){
        this.data = data;
        this.api = RetrofitUtils.createService(UploadProtocolApi.class);
    }
    public Observable<BaseRespBean> run(){
        return api.uploadprotocol(UserRepository.getInstance().getAuthId(),
                data.protocolMap.orgid,
                data.protocolMap.oname,
                data.protocolMap.teamworkprotocalpicurl,
                data.protocolMap.posterpicurl,
                data.protocolMap.secretagreementpicurl,
                data.protocolMap.promisebookpicurl);
    }
}
