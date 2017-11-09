package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.CommentApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/11/2.
 * 删除评论
 */

public class DeleteComment implements UserCase<Observable<BaseRespBean>> {

    private CommentApi commentApi;
    private String lid;

    public DeleteComment(String lid) {
        this.lid = lid;
        this.commentApi = RetrofitUtils.createService(CommentApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return commentApi.deleteComment(lid, UserRepository.getInstance().getAuthId());
    }
}
