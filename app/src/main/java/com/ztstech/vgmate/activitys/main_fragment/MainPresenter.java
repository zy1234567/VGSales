package com.ztstech.vgmate.activitys.main_fragment;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.appdomain.repository.UserPreferenceManager;
import com.ztstech.appdomain.user_case.GetMainPageBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class MainPresenter extends PresenterImpl<MainContract.View> implements
        MainContract.Presenter{

    private UserRepository userRepository;

    public MainPresenter(MainContract.View view) {
        super(view);
        userRepository = UserRepository.getInstance();
    }

    @Override
    public void loadData() {
        //加载主界面数据
        new BasePresenterSubscriber<MainPageBean>(mView,false) {

            @Override
            public void childNext(MainPageBean mainPageBean) {
                if (mainPageBean.isSucceed()) {
                    mView.hideLoading(null);
                    mView.setData(mainPageBean);
                }else {
                    mView.loadError(mainPageBean.getErrmsg());
                }
            }
        }.run(new GetMainPageBean(getUserDistrict()).run());

    }

    @Override
    public void loadUserInfo() {
        //加载用户信息
        UserBean userBean = userRepository.getUser().getUserBean();
        if (userBean != null) {
            mView.setUserInfo(userBean);
        }
    }

    /**
     * 获取默认请求地址
     * @return
     */
    private String getUserDistrict() {
        return UserPreferenceManager.getInstance().getUserSelectArea();
    }
}
