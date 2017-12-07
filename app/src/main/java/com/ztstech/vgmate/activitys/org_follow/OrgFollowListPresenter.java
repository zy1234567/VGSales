package com.ztstech.vgmate.activitys.org_follow;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author smm
 * @date 2017/11/14
 */

public class OrgFollowListPresenter extends PresenterImpl<OrgFollowListContact.View> implements OrgFollowListContact.Presenter {

    private int currentPage;
    private int totalPage;

    private static String ORG_FOLLOW_LIST = "org_follow_list";

    private SharedPreferences preferences;

    private int status;

    private List<OrgFollowlistBean.ListBean> listBeanList = new ArrayList<>();

    public OrgFollowListPresenter(OrgFollowListContact.View view, int status) {
        super(view);
        this.status = status;
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
    }


    @Override
    public void loadData(String uid) {
        requestData(1,status,uid);
    }

    @Override
    public void loadCacheData(String uid) {
        OrgFollowlistBean listBean = new Gson().fromJson(preferences.getString(
                ORG_FOLLOW_LIST.concat(uid == null ? "" : uid).concat(String.valueOf(status)),""),OrgFollowlistBean.class);
        if (listBean != null){
            listBeanList.addAll(listBean.list);
            mView.setData(listBeanList);
        }
    }

    @Override
    public void appendData(String uid) {
        if (totalPage == currentPage){
            mView.setData(listBeanList);
        }else {
            requestData(currentPage + 1,status,uid);
        }
    }

    private void requestData(final int page, final int status,final String uid) {
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
                        preferences.edit().putString(ORG_FOLLOW_LIST.concat(uid == null ? "" : uid).concat(String.valueOf(status)),new Gson().toJson(bean)).apply();
                    }
                    listBeanList.addAll(bean.list);
                    mView.setData(listBeanList);
                }else {
                    //如果失败
                    mView.showError(bean.getErrmsg());
                }

            }
        }.run(new GetOrgFollow(status, page,uid).run());
    }

}
