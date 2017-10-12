package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.CommentApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import retrofit2.http.Query;
import rx.Observable;

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

    public Comment(String flid, String newid, String touid, String comment) {
        this.newid = newid;
        this.flid = flid;
        this.touid = touid;
        this.comment = comment;

        commentApi = RetrofitUtils.createService(CommentApi.class);
    }

    @Override
    public Observable<BaseRespBean> run() {
        return commentApi.comment(null, flid, newid, touid, comment,
                UserRepository.getInstance().getAuthId());
    }
}
