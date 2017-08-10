package com.ztstech.vgmate.activitys.news;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface NewsContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<NewsContract.View> {

    }
}
