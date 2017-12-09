package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.user_case.DeleteArticle;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.appdomain.repository.MainListRepository;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;
import com.ztstech.vgmate.utils.Go2EditShareUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class InformationPresenter extends PresenterImpl<InformationContract.View> implements
        InformationContract.Presenter{

    private static String INFO_LIST = "info_list";

    private SharedPreferences preferences;

    /**当前页数*/
    private int currentPage = 1;

    private int maxPage = 1;

    private MainListRepository mainListRepository;

    /**列表数据*/
    private List<MainListBean.ListBean> listData = new ArrayList<>();

    public InformationPresenter(InformationContract.View view) {
        super(view);
        mainListRepository = MainListRepository.getInstance();
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
    }


    @Override
    public void loadListData() {
        loadDataWithPage(1);
    }

    @Override
    public void appendData() {
        if (currentPage < maxPage) {
            loadDataWithPage(currentPage + 1);
        }else {
            mView.setListData(listData);
        }
    }

    @Override
    public void deleteArticle(String nid) {
        new BasePresenterSubscriber<BaseRespBean>(mView){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.deleteArticleFinish(baseRespBean.getErrmsg());
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
            public void onComplete() {
                super.onComplete();
                mView.hideLoading(null);
            }

        }.run(RetrofitUtils.createShare(Go2EditShareUtils.transData(bean)));
    }

    @Override
    public void loadCache() {
        MainListBean mainListBean = new Gson().fromJson(preferences.getString(INFO_LIST,""),MainListBean.class);
        if (mainListBean != null){
            listData.clear();
            listData.addAll(mainListBean.list);
            mView.setListData(listData);
        }
    }

    /**
     * 根据页数加载数据
     * @param page
     */
    private void loadDataWithPage(int page) {
        new BasePresenterSubscriber<MainListBean>(mView,false){

            @Override
            public void childNext(MainListBean mainListBean) {
                if (mainListBean.isSucceed()) {
                    maxPage = mainListBean.pager.totalPages;
                    currentPage = mainListBean.pager.currentPage;

                    if (currentPage == 1) {
                        //如果当前是第一页，清除数据
                        listData.clear();
                        preferences.edit().putString(INFO_LIST,new Gson().toJson(mainListBean)).apply();
                    }

                    listData.addAll(mainListBean.list);
                    mView.setListData(listData);

                    mView.showNomoreData(currentPage == maxPage);
                }else {
                    mView.showError(mainListBean.getErrmsg());
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("网络访问出错:" + e.getMessage());
            }
        }.run(mainListRepository.queryInformation(page));
    }
}
