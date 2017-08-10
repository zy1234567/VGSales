package com.ztstech.mate.activitys.main;

import com.ztstech.mate.activitys.BasePresenter;
import com.ztstech.mate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface MainContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<MainContract.View>{

    }
}
