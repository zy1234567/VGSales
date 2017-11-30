package com.ztstech.vgmate.activitys.article_detail;

import com.ztstech.appdomain.user_case.Comment;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public class ArticleDetailPresenter extends PresenterImpl<ArticleDetailContract.View> implements
        ArticleDetailContract.Presenter {

    public ArticleDetailPresenter(ArticleDetailContract.View view) {
        super(view);
    }


    @Override
    public void comment(String flid, String newid, String touid, String comment,String flg) {

        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.onCommentFinish(baseRespBean.getErrmsg());
            }
        }.run(new Comment(flid, newid, touid, comment,flg).run());
    }
}
