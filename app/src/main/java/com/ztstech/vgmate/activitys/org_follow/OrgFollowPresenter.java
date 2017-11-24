package com.ztstech.vgmate.activitys.org_follow;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author smm
 * @date 2017/11/14
 */

public class OrgFollowPresenter extends PresenterImpl<OrgFollowContact.View> implements OrgFollowContact.Presenter {

    private int currentPage;
    private int totalPage;

    private static String ORG_FOLLOW_LIST = "org_follow_list";

    private SharedPreferences preferences;

    private int status;

    private List<OrgFollowlistBean.ListBean> listBeanList = new ArrayList<>();

    public OrgFollowPresenter(OrgFollowContact.View view,int status) {
        super(view);
        this.status = status;
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
    }


    @Override
    public void loadData() {
        requestData(1,status);
    }

    @Override
    public void loadCacheData() {
        OrgFollowlistBean listBean = new Gson().fromJson(preferences.getString(
                ORG_FOLLOW_LIST.concat(String.valueOf(status)),""),OrgFollowlistBean.class);
        if (listBean != null){
            listBeanList.addAll(listBean.list);
            mView.setData(listBeanList);
        }
    }

    @Override
    public void appendData() {
        if (totalPage == currentPage){
            mView.setData(listBeanList);
        }else {
            requestData(currentPage + 1,status);
        }
    }

    private void requestData(final int page, final int status) {
        new BasePresenterSubscriber<OrgFollowlistBean>(mView,false) {

            @Override
            protected void childError(Throwable e) {
                mView.showError("查询跟进机构列表出错：".concat(e.getMessage()));
            }

            @Override
            public void childNext(OrgFollowlistBean bean) {
                if (bean.isSucceed()) {
                    currentPage = bean.pager.currentPage;
                    totalPage = bean.pager.totalPages;

                    if (currentPage == 1) {
                        //刷新
                        listBeanList.clear();
                        preferences.edit().putString(ORG_FOLLOW_LIST.concat(String.valueOf(status)),new Gson().toJson(bean)).apply();
                    }
                    listBeanList.addAll(bean.list);
                    mView.setData(listBeanList);
                }else {
                    //如果失败
                    mView.showError(bean.getErrmsg());
                }

            }
        }.run(new GetOrgFollow(status, page).run());
    }

}
