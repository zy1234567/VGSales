package com.ztstech.vgmate.activitys.sell_chance.subview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 销售机会列表
 */
public class SellChanceAllFragment extends MVPFragment<SellChanceAllContract.Presenter> implements
        SellChanceAllContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private SellChanceAllRecyclerAdapter adapter;

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
        adapter = new SellChanceAllRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        List<String> mFakeData = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            mFakeData.add("");
        }
        adapter.setListData(mFakeData);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String item, int index) {
                startActivity(new Intent(getActivity(), GetChanceActivity.class));
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
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        mPresenter.refreshData();
    }

    /**
     * 加载更多
     */
    private void appendData() {
        mPresenter.appendData();
    }
}
