package com.ztstech.vgmate.activitys.main_fragment.subview.notice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.subview.notice.adapter.NoticeRecyclerAdapter;
import com.ztstech.vgmate.data.beans.MainListBean;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends MVPFragment<NoticeContract.Presenter> implements
        NoticeContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;


    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;

    private NoticeRecyclerAdapter recyclerAdapter;

    public NoticeFragment() {
        // Required empty public constructor
    }

    public static NoticeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_notice;
    }

    @Override
    protected NoticeContract.Presenter initPresenter() {
        return new NoticePresenter(this);
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        recyclerAdapter = new NoticeRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        refreshLayout.setEnableRefresh(false);  //禁止下拉刷新

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });


        mPresenter.loadData();


    }




    @Override
    public void setData(List<MainListBean.ListBean> listData) {
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadmore();
        }
        recyclerAdapter.setListData(listData);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {

    }
}
