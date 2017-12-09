package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.CommentApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/11/2.
 * 删除评论
 */

public class DeleteComment implements UserCase<Observable<BaseRespBean>> {

    private CommentApi commentApi;
    private String lid,flg;

    public DeleteComment(String lid,String flg) {
        this.lid = lid;
        this.flg = flg;
        this.commentApi = RetrofitUtils.createService(CommentApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return commentApi.deleteComment(lid,flg,UserRepository.getInstance().getAuthId());
    }
}
