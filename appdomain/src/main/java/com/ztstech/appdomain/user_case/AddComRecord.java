package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.AddComRecordApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddComRecordData;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/1/15.
 */

public class AddComRecord {
    private AddComRecordApi api;
    private AddComRecordData data;
    public AddComRecord(AddComRecordData data){
        this.data = data;
        this.api = RetrofitUtils.createService(AddComRecordApi.class);
    }
    public Observable<BaseRespBean> run() {
        return api.addcomRecord(data.makecall,
                data.communicationtype,
                data.spotgps,
                data.spotphotos,
                data.consultphone,
                data.contactsname,
                data.roletype,
                data.backstage,
                data.callon,
                data.followtype,
                data.contactsphone,
                data.msg,
                data.rbiid,
                UserRepository.getInstance().getAuthId());
    }

}
