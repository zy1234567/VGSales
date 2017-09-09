package com.ztstech.vgmate.activitys.article_detail;

import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public class ArticleDetailPresenter extends PresenterImpl<ArticleDetailContract.View> implements
        ArticleDetailContract.Presenter {

    public ArticleDetailPresenter(ArticleDetailContract.View view) {
        super(view);
    }
}
