package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;

/**
 * Created by smm on 2017/11/27.
 */

public class CreateMyShare implements UserCase<Observable<BaseRespBean>>{

    /**
     * title(标题）,content（内容），ntype（分享类型
       contentpicsurl（高清图），contentpicurl（缩略图）
       linkurl（链接地址），picdescribe（图片描述）
     */
    private CreateShareApi api;

    private String title,content,ntype,contentpicsurl,contentpicurl,linkurl,picdescribe;

    public CreateMyShare(String title,String content,String ntype,String contentpicsurl,
                         String contentpicurl,String linkurl,String picdescribe){
        api = RetrofitUtils.createService(CreateShareApi.class);
        this.title = title;
        this.content = content;
        this.contentpicurl = contentpicurl;
        this.contentpicsurl = contentpicsurl;
        this.ntype = ntype;
        this.linkurl = linkurl;
        this.picdescribe = picdescribe;
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.createMyShare(title,content,contentpicurl,
                contentpicsurl,picdescribe,ntype,linkurl, UserRepository.getInstance().getAuthId());
    }
}
