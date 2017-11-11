package com.ztstech.vgmate.activitys.main_fragment;

import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;

/**
 * Created by smm on 2017/11/2.
 */

public class MainFragmentNew extends MVPFragment<MainContract.Presenter> implements MainContract.View {
    @Override
    public void loadError(String errorMessage) {

    }

    @Override
    public void setData(MainPageBean mainPageBean) {

    }

    @Override
    public void setUserInfo(UserBean userBean) {

    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return null;
    }
}
