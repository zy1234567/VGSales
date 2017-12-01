package com.ztstech.vgmate.activitys.main_fragment.subview.notice;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.ztstech.appdomain.user_case.DeleteArticle;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.appdomain.repository.MainListRepository;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/15.
 */

public class NoticePresenter extends PresenterImpl<NoticeContract.View> implements
        NoticeContract.Presenter {

    private static String NOTICE_LIST = "notice_list";

    private SharedPreferences preferences;

    private MainListRepository repository;

    private int currentPager = 1;

    private int maxPage = 1;

    private List<MainListBean.ListBean> mListData = new ArrayList<>();

    public NoticePresenter(NoticeContract.View view) {
        super(view);
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
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
    public void loadCacheData() {
        MainListBean mainListBean = new Gson().fromJson(preferences.getString(NOTICE_LIST,""),MainListBean.class);
        if (mainListBean != null){
            mListData.clear();
            mListData.addAll(mainListBean.list);
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
                        preferences.edit().putString(NOTICE_LIST,new Gson().toJson(mainListBean)).apply();
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
