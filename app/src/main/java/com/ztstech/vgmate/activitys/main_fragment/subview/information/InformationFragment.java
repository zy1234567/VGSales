package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.subview.information.adapter.InformationRecyclerAdapter;
import com.ztstech.vgmate.data.beans.MainListBean;

import java.util.List;

import butterknife.BindView;


public class InformationFragment extends MVPFragment<InformationContract.Presenter> implements
        InformationContract.View {


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;

    private InformationRecyclerAdapter recyclerAdapter;


    public static InformationFragment newInstance() {

        InformationFragment fragment = new InformationFragment();
        return fragment;
    }

    @Override
    protected InformationContract.Presenter initPresenter() {
        return new InformationPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_information;
    }


    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        recyclerAdapter = new InformationRecyclerAdapter();
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

        mPresenter.loadListData();


    }


    @Override
    public void setListData(List<MainListBean.ListBean> listData) {
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
    public void showNomoreData(boolean nomore) {

    }

    //    @Override
//    public void setListData(List<InformationModel> informationModels) {
//        recyclerAdapter.setListData(informationModels);
//        recyclerAdapter.notifyDataSetChanged();
//    }
}
