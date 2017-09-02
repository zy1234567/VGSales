package com.ztstech.vgmate.activitys.main_fragment;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.repository.MainRepository;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.utils.PresenterSubscriber;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class MainPresenter extends PresenterImpl<MainContract.View> implements
        MainContract.Presenter{

    private MainRepository repository;
    private UserRepository userRepository;

    public MainPresenter(MainContract.View view) {
        super(view);
        repository = new MainRepository();
        userRepository = UserRepository.getInstance();
    }

    @Override
    public void loadData() {
        //加载主界面数据
        new PresenterSubscriber<MainPageBean>(mView) {

            @Override
            public void onNext(MainPageBean mainPageBean) {
                if (mainPageBean.isSucceed()) {
                    mView.hideLoading(null);
                    mView.setData(mainPageBean);
                }else {
                    mView.loadError(mainPageBean.getErrmsg());
                }
            }
        }.run(repository.loadMainPageInfo());

        //加载用户信息
        UserBean userBean = userRepository.getUser();
        if (userBean != null) {
            mView.setUserInfo(userBean);
        }
    }
}
