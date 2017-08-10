package com.ztstech.vgmate.activitys.main;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public interface MainContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<MainContract.View>{

    }
}
