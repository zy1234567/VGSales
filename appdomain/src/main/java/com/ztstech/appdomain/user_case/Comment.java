package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.CommentApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/13.
 * 评论
 */

public class Comment implements UserCase<Observable<BaseRespBean>> {

    private CommentApi commentApi;

    private String newid;
    private String flid;
    private String touid;
    private String comment;
    private String flg;

    public Comment(String flid, String newid, String touid, String comment,String flg) {
        this.newid = newid;
        this.flid = flid;
        this.touid = touid;
        this.comment = comment;
        this.flg = flg;

        commentApi = RetrofitUtils.createService(CommentApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return commentApi.comment(flid, flg,newid, touid, comment,
                UserRepository.getInstance().getAuthId());
    }
}
