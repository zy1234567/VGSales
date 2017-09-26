package com.ztstech.vgmate.activitys.org_list.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_detail.OrgDetailActivity;
import com.ztstech.vgmate.activitys.org_list.OrgListActivity;
import com.ztstech.vgmate.activitys.org_list.adapter.OrgListPageAdapter;
import com.ztstech.vgmate.activitys.org_list.fragments.adapter.OrglistRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;

import java.util.List;

import butterknife.BindView;

/**
 * item
 */
public class OrglistItemFragment extends MVPFragment<OrglistItemContract.Presenter> implements
        OrglistItemContract.View {

    public static final String ARG_STATUS = "status";

    /**intent 跳转详情，如果result_ok需要刷新*/
    public static final int REQ_DETAIL = 1;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    /**00以生效打点(待认领)，01无效打点，02待审核打点(待确定),03以认领机构（即是以生效打点并且有机构id）,04管理端*/
    private String status;
    /**地址*/
    private String rbidistrict;

    private OrglistRecyclerAdapter adapter;


    public static OrglistItemFragment newInstance(String status) {
        
        Bundle args = new Bundle();
        args.putString(ARG_STATUS, status);
        
        OrglistItemFragment fragment = new OrglistItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected OrglistItemContract.Presenter initPresenter() {
        return new OrglistitemPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_orglist_item;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        status = getArguments().getString(ARG_STATUS);
        if (TextUtils.isEmpty(status)) {
            throw new IllegalArgumentException("状态不能为空");
        }

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
            }
        });


        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                appendData();
            }
        });


        adapter = new OrglistRecyclerAdapter();
        adapter.setOnItemClickListener(
                new SimpleRecyclerAdapter.OnItemClickListener<GetOrgListItemsBean.ListBean>() {
            @Override
            public void onItemClick(GetOrgListItemsBean.ListBean item, int index) {
                Intent it = new Intent(getActivity(), OrgDetailActivity.class);
                it.putExtra(OrgDetailActivity.ARG_ORG_BEAN, new Gson().toJson(item));
                startActivityForResult(it, REQ_DETAIL);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        refreshData();
    }

    private void refreshData() {
        rbidistrict = ((OrgListActivity) getActivity()).getLocationId();
        mPresenter.refreshList(rbidistrict, status);
    }

    private void appendData() {
        rbidistrict = ((OrgListActivity) getActivity()).getLocationId();
        mPresenter.appendList(rbidistrict, status);
    }


    @Override
    public void onLoadMoreFinish(final List<GetOrgListItemsBean.ListBean> items, String errmsg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                smartRefreshLayout.setRefreshing(false);
                adapter.setListData(items);
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishLoadmore();

            }
        });
    }

    @Override
    public void onRefreshFinish(final List<GetOrgListItemsBean.ListBean> items, String errmsg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                smartRefreshLayout.setRefreshing(false);
                adapter.setListData(items);
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishRefresh();

            }
        });
    }
}
