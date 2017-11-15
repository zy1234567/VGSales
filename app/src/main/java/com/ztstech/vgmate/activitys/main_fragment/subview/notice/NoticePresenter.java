package com.ztstech.vgmate.activitys.main_fragment.subview.notice;

import com.ztstech.appdomain.user_case.DeleteArticle;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.appdomain.repository.MainListRepository;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;
import com.ztstech.vgmate.utils.Go2EditShareUtils;

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

    @Override
    public void deleteNotice(String nid) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean mainListBean) {
                mView.deleteArticleFinish(mainListBean.getErrmsg());
            }

        }.run(new DeleteArticle(nid).run());
    }

    /**
     * 重新上传
     * @param bean
     */
    @Override
    public void resendArticle(MainListBean.ListBean bean) {
        //上传数据
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
                mView.resendFinish(baseRespBean.getErrmsg());
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.hideLoading(null);
            }

        }.run(RetrofitUtils.resendShare(bean.nid));
    }

    private void queryDataWithPage(int page) {

        new BasePresenterSubscriber<MainListBean>(mView,false) {

            @Override
            public void childNext(MainListBean mainListBean) {
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

            @Override
            protected void childError(Throwable e) {
                mView.showError("网络请求出错".concat(e.getLocalizedMessage()));
            }
        }.run(repository.queryNotice(page));
    }

}
