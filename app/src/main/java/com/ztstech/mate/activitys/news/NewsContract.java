package com.ztstech.mate.activitys.news;

import com.ztstech.mate.activitys.BasePresenter;
import com.ztstech.mate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface NewsContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<NewsContract.View> {

    }
}
