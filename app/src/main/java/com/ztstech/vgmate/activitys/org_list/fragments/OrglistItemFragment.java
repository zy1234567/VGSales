package com.ztstech.vgmate.activitys.org_list.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_list.OrgListActivity;
import com.ztstech.vgmate.activitys.org_list.adapter.OrgListPageAdapter;
import com.ztstech.vgmate.activitys.org_list.fragments.adapter.OrglistRecyclerAdapter;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;

import java.util.List;

import butterknife.BindView;

/**
 * item
 */
public class OrglistItemFragment extends MVPFragment<OrglistItemContract.Presenter> implements
        OrglistItemContract.View {

    public static final String ARG_STATUS = "status";

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

//        smartRefreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshData();
//            }
//        });


        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                appendData();
            }
        });
//
//        smartRefreshLayout.setOn


        adapter = new OrglistRecyclerAdapter();

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
                smartRefreshLayout.finishLoadmore();
//                smartRefreshLayout.setRefreshing(false);
                adapter.setListData(items);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefreshFinish(final List<GetOrgListItemsBean.ListBean> items, String errmsg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                smartRefreshLayout.finishRefresh();
//                smartRefreshLayout.setRefreshing(false);
                adapter.setListData(items);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
