package com.ztstech.vgmate.activitys.mate_approve;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.ztstech.appdomain.user_case.GetUnApproveMateDetail;
import com.ztstech.appdomain.user_case.GetUnApproveMateList;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/13
 */

public class UnApproveMatePresenter extends PresenterImpl<UnApproveMateContact.View> implements UnApproveMateContact.Presenter {

    private static String UN_APPROVE_MATE_LIST = "un_approve_mate_list";

    private SharedPreferences preferences;

    private int currentPage;
    private int totalPage;

    private List<WaitApproveMateListBean.ListBean> listBeanList = new ArrayList<>();

    public UnApproveMatePresenter(UnApproveMateContact.View view){
        super(view);
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
    }

    @Override
    public void loadData(String myflg) {
        requestData(1,myflg);
    }

    @Override
    public void appendData(String myflg) {
        if (totalPage == currentPage){
            mView.setNoreMoreData(true);
        }else {
            requestData(currentPage + 1,myflg);
        }
    }

    @Override
    public void loadCacheData() {
        WaitApproveMateListBean mainListBean = new Gson().fromJson(preferences.getString(UN_APPROVE_MATE_LIST,""),WaitApproveMateListBean.class);
        if (mainListBean != null){
            listBeanList.addAll(mainListBean.list);
            mView.setData(listBeanList);
        }
    }

    @Override
    public void findMateDetail(String saleid) {
        new BasePresenterSubscriber<UnApproveMateBean>(mView) {

            @Override
            protected void childError(Throwable e) {
                mView.showError("查询销售资料出错".concat(e.getMessage()));
            }

            @Override
            public void childNext(UnApproveMateBean bean) {
                if (bean.isSucceed()) {
                    mView.getMateDetailFinish(bean);
                }else {
                    //如果失败
                    mView.showError(bean.getErrmsg());
                }

            }
        }.run(new GetUnApproveMateDetail(saleid).run());
    }

    /**
     * 请求待审批列表
     * @param page
     */
    private void requestData(final int page, final String myflg) {
        new BasePresenterSubscriber<WaitApproveMateListBean>(mView,false) {

            @Override
            protected void childError(Throwable e) {
                mView.showError("查询待审批销售列表出错：".concat(e.getMessage()));
            }

            @Override
            public void childNext(WaitApproveMateListBean bean) {
                if (bean.isSucceed()) {
                    currentPage = bean.pager.currentPage;
                    totalPage = bean.pager.totalPages;
                    if (currentPage == 1) {
                        //刷新
                        listBeanList.clear();
                        if (GetUnApproveMateList.FILTER_ALL.equals(myflg)) {
                            // 只缓存不筛选的
                            preferences.edit().putString(UN_APPROVE_MATE_LIST, new Gson().toJson(bean)).apply();
                        }
                    }
                    listBeanList.addAll(bean.list);
                    mView.setData(listBeanList);
                }else {
                    //如果失败
                    mView.showError(bean.getErrmsg());
                }
            }
        }.run(new GetUnApproveMateList(page,myflg).run());
    }


}
