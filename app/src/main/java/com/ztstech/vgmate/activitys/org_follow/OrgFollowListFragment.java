package com.ztstech.vgmate.activitys.org_follow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity;
import com.ztstech.vgmate.activitys.org_follow.adapter.OrgFollowListAdapter;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.events.ApproveOrgEvent;
import com.ztstech.vgmate.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * @author smm
 * @date 2017/11/13
 * 客户跟进模块展示的机构列表 可以展示四个状态的(已确认，已认领，管理端，机构反馈)
 */

public class OrgFollowListFragment extends MVPFragment<OrgFollowListContact.Presenter> implements OrgFollowListContact.View {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;

    /**
     * 所要显示的是哪个列表
     */
    private int statusIndex;

    /**
     * 要显示谁的名下的客户跟进 是自己的不调用
     */
    private String uid;

    private OrgFollowListAdapter adapter;

    public static OrgFollowListFragment newInstance() {
        return new OrgFollowListFragment();
    }

    public void setIndexStatus(int statusIndex){
        this.statusIndex = statusIndex;
    }

    /***
     * 要显示谁的名下的客户跟进 是自己的不调用
     * @param uid
     */
    public void setUid(String uid){
        this.uid = uid;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        EventBus.getDefault().register(this);
        adapter = new OrgFollowListAdapter(statusIndex);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
        mPresenter.loadCacheData(uid);
        mPresenter.loadData(uid);
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData(uid);
            }
        });

    }

    @Override
    protected OrgFollowListContact.Presenter initPresenter() {
        return new OrgFollowListPresenter(this, statusIndex);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_passed_org;
    }

    @Override
    public void setData(List<OrgFollowlistBean.ListBean> listData) {
        if (smartRefreshLayout.isLoading()){
            smartRefreshLayout.finishLoadmore();
            smartRefreshLayout.finishRefresh();
        }
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        if (listData.size() == 0){
            llNoData.setVisibility(View.VISIBLE);
            smartRefreshLayout.setVisibility(View.GONE);
        }else {
            llNoData.setVisibility(View.GONE);
            smartRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String errorMessage) {
        ToastUtil.toastCenter(getContext(),errorMessage);
    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onRefresh(ApproveOrgEvent event){
        mPresenter.loadData(uid);
    }

}
