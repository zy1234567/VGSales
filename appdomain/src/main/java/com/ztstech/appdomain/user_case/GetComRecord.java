package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.AddComRecordApi;
import com.ztstech.vgmate.data.beans.GetComRecordBean;

import io.reactivex.Observable;


/**
 * Created by lenovo on 2018/1/16.
 */

public class GetComRecord {
    private String rbiid;
    private AddComRecordApi api;
    private int pageNo;
    public GetComRecord(String rbiid,int pageNo){
        this.rbiid = rbiid;
        this.pageNo = pageNo;
        api = RetrofitUtils.createService(AddComRecordApi.class);
    }

    public Observable<GetComRecordBean> run() {
        return api.getcomRecord(rbiid,pageNo,UserRepository.getInstance().getAuthId());
    }
}
