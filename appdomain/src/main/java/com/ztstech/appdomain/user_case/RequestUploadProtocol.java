package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.AddComRecordApi;
import com.ztstech.vgmate.data.api.RequestUploadProtocolApi;
import com.ztstech.vgmate.data.beans.GetComRecordBean;
import com.ztstech.vgmate.data.dto.UploadProtocolData;

import io.reactivex.Observable;

/**
 * Created by dongdong on 2018/3/27.
 */

public class RequestUploadProtocol {
    private String orgid;
    private RequestUploadProtocolApi api;
    public RequestUploadProtocol(String orgid){
        this.orgid = orgid;
        api = RetrofitUtils.createService(RequestUploadProtocolApi.class);
    }

    public Observable<UploadProtocolData> run() {
        return api.requestUploadprotocol(orgid, UserRepository.getInstance().getAuthId());
    }
}
