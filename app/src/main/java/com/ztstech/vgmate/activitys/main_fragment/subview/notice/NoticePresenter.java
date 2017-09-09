package com.ztstech.vgmate.activitys.main_fragment.subview.notice;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.repository.MainListRepository;
import com.ztstech.vgmate.model.notice.NoticeModel;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/15.
 */

public class NoticePresenter extends PresenterImpl<NoticeContract.View> implements
        NoticeContract.Presenter {

    private MainListRepository repository;

    private int currentPager = 1;

    public NoticePresenter(NoticeContract.View view) {
        super(view);

        repository = MainListRepository.getInstance();
    }

    @Override
    public void loadData() {
        new PresenterSubscriber<MainListBean>(mView) {

            @Override
            public void onNext(MainListBean mainListBean) {
                if (mainListBean.isSucceed()) {
                    mView.setData(mainListBean);
                }else {
                    mView.showError(mainListBean.getErrmsg());
                }
            }
        }.run(repository.queryNotice());
    }

    @Override
    public void appendData() {

    }
}
