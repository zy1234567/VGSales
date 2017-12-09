package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.domain.User;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.MainListApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/11/2.
 * 删除文章
 */

public class DeleteArticle implements UserCase<Observable<BaseRespBean>>{

    private String nid;
    private MainListApi api;

    public DeleteArticle(String nid) {
        this.nid = nid;
        this.api = RetrofitUtils.createService(MainListApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.deleteArticle(nid, UserRepository.getInstance().getAuthId());
    }
}
