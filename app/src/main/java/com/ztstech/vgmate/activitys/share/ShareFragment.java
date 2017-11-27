package com.ztstech.vgmate.activitys.share;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.share.adapter.ShareListAdapter;
import com.ztstech.vgmate.data.beans.ShareListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分享
 *
 * @author smm
 */
public class ShareFragment extends MVPFragment<ShareContact.Presenter> implements ShareContact.View{

    @BindView(R.id.recycler)
    RecyclerView recycler;

    private ShareListAdapter adapter;

    public static ShareFragment newInstance() {
        Bundle args = new Bundle();
        ShareFragment fragment = new ShareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        adapter = new ShareListAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ShareListBean.ListBean> lists = new ArrayList<>();
        lists.add(new ShareListBean.ListBean());
        lists.add(new ShareListBean.ListBean());
        lists.add(new ShareListBean.ListBean());
        lists.add(new ShareListBean.ListBean());
        lists.add(new ShareListBean.ListBean());
        lists.add(new ShareListBean.ListBean());
        lists.add(new ShareListBean.ListBean());
        adapter.setListData(lists);
        recycler.setAdapter(adapter);
        mPresenter.loadNetData();
    }

    @Override
    protected ShareContact.Presenter initPresenter() {
        return new SharePresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_share;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onDeleteSuccess() {

    }
}
