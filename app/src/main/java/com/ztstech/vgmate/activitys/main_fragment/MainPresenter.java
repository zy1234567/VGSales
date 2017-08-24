package com.ztstech.vgmate.activitys.main_fragment;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.repository.MainRepository;
import com.ztstech.vgmate.utils.PresenterSubscriber;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class MainPresenter extends PresenterImpl<MainContract.View> implements
        MainContract.Presenter{

    private MainRepository repository;

    public MainPresenter(MainContract.View view) {
        super(view);
        repository = new MainRepository();
    }

    @Override
    public void loadData() {
        new PresenterSubscriber<MainPageBean>() {

            @Override
            public void onNext(MainPageBean mainPageBean) {
                mView.hideLoading(null);
                mView.setData(mainPageBean);

            }
        }.run(repository.loadMainPageInfo());
    }
}
