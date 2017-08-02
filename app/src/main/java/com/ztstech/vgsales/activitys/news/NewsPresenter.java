package com.ztstech.vgsales.activitys.news;

import com.ztstech.vgsales.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class NewsPresenter extends PresenterImpl<NewsContract.View> implements
        NewsContract.Presenter{

    public NewsPresenter(NewsContract.View view) {
        super(view);
    }
}
