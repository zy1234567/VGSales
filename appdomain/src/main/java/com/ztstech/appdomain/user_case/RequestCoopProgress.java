package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.RequestUploadProtocolApi;
import com.ztstech.vgmate.data.dto.CoopProgressData;
import com.ztstech.vgmate.data.dto.UploadProtocolData;

import io.reactivex.Observable;

/**
 * Created by dongdong on 2018/4/26.
 */

public class RequestCoopProgress {
    private String orgid;
    private String rbiid;
    private RequestUploadProtocolApi api;
    public RequestCoopProgress(String rbiid,String orgid){
        this.orgid = orgid;
        this.rbiid = rbiid;
        api = RetrofitUtils.createService(RequestUploadProtocolApi.class);
    }

    public Observable<CoopProgressData> run() {
        return api.requestCoopprogress(rbiid,orgid, UserRepository.getInstance().getAuthId());
    }
}
