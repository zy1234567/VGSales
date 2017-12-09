package com.ztstech.appdomain.user_case;

import android.support.annotation.NonNull;

import com.ztstech.vgmate.data.api.CommentApi;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/12.
 * 获取评论列表
 */

public class GetCommentList implements UserCase<Observable<CommentBean>> {

    private CommentApi commentApi;
    private String newsid;
    private int pageNo;

    public GetCommentList(@NonNull String newsid, int pageNo) {
        this.newsid = newsid;
        this.pageNo = pageNo;
        commentApi = RetrofitUtils.createService(CommentApi.class);
    }

    @Override
    public Observable<CommentBean> run() {
        return commentApi.getCommentList(newsid, pageNo, UserRepository.getInstance().getAuthId());
    }
}
