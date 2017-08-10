package com.ztstech.mate.activitys.news;

import com.ztstech.mate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class NewsPresenter extends PresenterImpl<NewsContract.View> implements
        NewsContract.Presenter{

    public NewsPresenter(NewsContract.View view) {
        super(view);
    }
}
