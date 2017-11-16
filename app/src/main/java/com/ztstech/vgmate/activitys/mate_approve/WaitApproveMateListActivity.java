package com.ztstech.vgmate.activitys.mate_approve;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.mate_approve.adapter.WaitApproveMateAdapter;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 伙伴待审批列表
 *
 * @author smm
 * @date 2017/11/13
 */

public class WaitApproveMateListActivity extends MVPActivity<WaitApproveMateContact.Presenter> implements WaitApproveMateContact.View{


    WaitApproveMateAdapter adapter;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SmartRefreshLayout refreshLayout;

    @Override
    protected WaitApproveMateContact.Presenter initPresenter() {
        return new WaitApproveMatePresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_wait_approve_list;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        adapter = new WaitApproveMateAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadData();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });
    }

    @Override
    public void setData(List<WaitApproveMateListBean.ListBean> listData) {
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void showError(String errorMessage) {
        ToastUtil.toastCenter(this,errorMessage);
    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {

    }
}
