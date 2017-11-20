package com.ztstech.vgmate.activitys.org_follow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_follow.adapter.OrgFollowListAdapter;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author smm
 * @date 2017/11/13
 * 已确认机构列表
 */

public class OrgFollowListFragment extends MVPFragment<OrgFollowContact.Presenter> implements OrgFollowContact.View {


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

    OrgFollowListAdapter adapter;

    public static OrgFollowListFragment newInstance() {
        return new OrgFollowListFragment();
    }

    public void setIndexStatus(int statusIndex){
        this.statusIndex = statusIndex;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        adapter = new OrgFollowListAdapter(statusIndex);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
        mPresenter.loadCacheData();
        mPresenter.loadData();
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });
    }

    @Override
    protected OrgFollowContact.Presenter initPresenter() {
        return new OrgFollowPresenter(this, statusIndex);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_passed_org;
    }

    @Override
    public void setData(List<OrgFollowlistBean.ListBean> listData) {
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



}
