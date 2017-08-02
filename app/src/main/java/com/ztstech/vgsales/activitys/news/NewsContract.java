package com.ztstech.vgsales.activitys.news;

import com.ztstech.vgsales.activitys.BasePresenter;
import com.ztstech.vgsales.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface NewsContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<NewsContract.View> {

    }
}
