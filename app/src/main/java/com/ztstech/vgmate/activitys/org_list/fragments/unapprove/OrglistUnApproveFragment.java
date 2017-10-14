package com.ztstech.vgmate.activitys.org_list.fragments.unapprove;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.unapprove.adapter.UnApproveRecyclerAdapter;
import com.ztstech.vgmate.data.beans.OrglistUnApproveBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 待审批
 */
public class OrglistUnApproveFragment extends MVPFragment<OrglistUnApproveContract.Presenter>
        implements OrglistUnApproveContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private UnApproveRecyclerAdapter recyclerAdapter;

    public OrglistUnApproveFragment() {
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_orglist_un_approve;
    }

    @Override
    protected OrglistUnApproveContract.Presenter initPresenter() {
        return new OrglistUnApprovePresenter(this);
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.refresh();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });


        recyclerAdapter = new UnApproveRecyclerAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        mPresenter.refresh();

    }


    @Override
    public void onRefreshFinish(List<OrglistUnApproveBean.ListBean> items, @Nullable String errmsg) {
        if (errmsg != null) {
            ToastUtil.toastCenter(getActivity(), "数据加载失败：" + errmsg);
        } else {
            smartRefreshLayout.finishRefresh();
            recyclerAdapter.setListData(items);
            recyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadFinsh(List<OrglistUnApproveBean.ListBean> items, @Nullable String errmsg) {
        if (errmsg != null) {
            ToastUtil.toastCenter(getActivity(), "数据加载失败：" + errmsg);
        } else {
            smartRefreshLayout.finishLoadmore();
            recyclerAdapter.setListData(items);
            recyclerAdapter.notifyDataSetChanged();
        }
    }
}
