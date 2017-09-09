package com.ztstech.vgmate.activitys.main_fragment.subview.notice;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.repository.MainListRepository;
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

    private int maxPage = 1;

    private List<MainListBean.ListBean> mListData = new ArrayList<>();

    public NoticePresenter(NoticeContract.View view) {
        super(view);

        repository = MainListRepository.getInstance();
    }

    @Override
    public void loadData() {
        queryDataWithPage(1);
    }

    @Override
    public void appendData() {
        if (currentPager < maxPage) {
            queryDataWithPage(currentPager + 1);
        }else {
            mView.setData(mListData);
        }
    }

    private void queryDataWithPage(int page) {
        new PresenterSubscriber<MainListBean>(mView) {

            @Override
            public void onNext(MainListBean mainListBean) {
                if (mainListBean.isSucceed()) {

                    currentPager = mainListBean.pager.currentPage;
                    maxPage = mainListBean.pager.totalPages;

                    if (currentPager == 1) {
                        mListData.clear();
                    }

                    mListData.addAll(mainListBean.list);

                    mView.setData(mListData);

                    mView.setNoreMoreData(currentPager == maxPage);
                }else {
                    mView.showError(mainListBean.getErrmsg());
                }
            }
        }.run(repository.queryNotice(page));
    }

}
