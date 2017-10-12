package com.ztstech.vgmate.activitys.article_detail;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.user_case.Comment;
import com.ztstech.vgmate.utils.PresenterSubscriber;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public class ArticleDetailPresenter extends PresenterImpl<ArticleDetailContract.View> implements
        ArticleDetailContract.Presenter {

    public ArticleDetailPresenter(ArticleDetailContract.View view) {
        super(view);
    }

    @Override
    public void comment(String comment, MainListBean.ListBean data) {
//        new PresenterSubscriber<BaseRespBean>(mView){
//
//            @Override
//            public void onNext(BaseRespBean baseRespBean) {
//
//            }
//        }.run(new Comment());
    }
}
