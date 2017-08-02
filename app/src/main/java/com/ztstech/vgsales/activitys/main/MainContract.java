package com.ztstech.vgsales.activitys.main;

import com.ztstech.vgsales.activitys.BasePresenter;
import com.ztstech.vgsales.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface MainContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<MainContract.View>{

    }
}
