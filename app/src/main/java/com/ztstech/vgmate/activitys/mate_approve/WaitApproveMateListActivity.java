package com.ztstech.vgmate.activitys.mate_approve;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.mate_approve.adapter.WaitApproveMateAdapter;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

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

public class WaitApproveMateListActivity extends MVPActivity implements WaitApproveMateContact.View{


    WaitApproveMateAdapter adapter;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected BasePresenter initPresenter() {
        return new WaitApproveMatePresenter();
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
        setData(new ArrayList<WaitApproveMateListBean.ListBean>());
    }

    @Override
    public void setData(List<WaitApproveMateListBean.ListBean> listData) {

        listData.add(new WaitApproveMateListBean.ListBean());
        listData.add(new WaitApproveMateListBean.ListBean());
        listData.add(new WaitApproveMateListBean.ListBean());
        listData.add(new WaitApproveMateListBean.ListBean());
        listData.add(new WaitApproveMateListBean.ListBean());
        listData.add(new WaitApproveMateListBean.ListBean());
        listData.add(new WaitApproveMateListBean.ListBean());
        listData.add(new WaitApproveMateListBean.ListBean());

        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {

    }
}
