package com.ztstech.vgmate.activitys.sell_chance.subview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.get_chance.GetChanceActivity;
import com.ztstech.vgmate.activitys.sell_chance.subview.adapter.SellChanceAllRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.SellChanceBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 销售机会列表
 */
public class SellChanceAllFragment extends MVPFragment<SellChanceAllContract.Presenter> implements
        SellChanceAllContract.View {

    /**传入请求参数*/
    public static final String ARG_STATUS = "arg_status";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private SellChanceAllRecyclerAdapter adapter;

    private String status;

    /**
     * 获取新实例
     * @param status
     * @return
     */
    public static SellChanceAllFragment newInstance(String status) {
        SellChanceAllFragment instance = new SellChanceAllFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_STATUS, status);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected SellChanceAllContract.Presenter initPresenter() {
        return new SellChanceAllPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_sell_chance_all;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        status = getArguments().getString(ARG_STATUS);
        if (TextUtils.isEmpty(status)) {
            throw new RuntimeException("未传入status");
        }

        adapter = new SellChanceAllRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(
                new SimpleRecyclerAdapter.OnItemClickListener<SellChanceBean.ListBean>() {
            @Override
            public void onItemClick(SellChanceBean.ListBean item, int index) {
                Intent it = new Intent(getActivity(), GetChanceActivity.class);
                it.putExtra(GetChanceActivity.ARG_ID, item.comid);
                startActivity(it);
            }
        });

        smartRefreshLayout.setBackgroundColor(ContextCompat.getColor(getActivity(),
                R.color.color_107));

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

        refreshData();
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        mPresenter.refreshData(status);
    }

    /**
     * 加载更多
     */
    private void appendData() {
        mPresenter.appendData(status);
    }

    @Override
    public void onRefreshFinish(List<SellChanceBean.ListBean> result, @Nullable String errmsg) {
        smartRefreshLayout.finishRefresh();
        if (errmsg != null) {
            ToastUtil.toastCenter(getActivity(), "请求数据失败：" + errmsg);
        }else {
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadFinish(List<SellChanceBean.ListBean> result, @Nullable String errmsg) {
        smartRefreshLayout.finishLoadmore();
        if (errmsg != null) {
            ToastUtil.toastCenter(getActivity(), "请求数据失败：" + errmsg);
        }else {
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
        }
    }
}
