package com.ztstech.vgmate.activitys.mate_approve;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.mate_approve.adapter.WaitApproveMateAdapter;
import com.ztstech.vgmate.activitys.mate_approve.adapter.WaitApproveViewHolder;
import com.ztstech.vgmate.activitys.user_info.edit_info.EditInfoActivity;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 伙伴待审批列表
 *
 * @author smm
 * @date 2017/11/13
 */

public class UnApproveMateListActivity extends MVPActivity<UnApproveMateContact.Presenter>
        implements UnApproveMateContact.View,WaitApproveViewHolder.ClickDetailCallBack{


    WaitApproveMateAdapter adapter;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;

    /** 所点击的查看详情的销售id */
    private String saleid;

    @Override
    protected UnApproveMateContact.Presenter initPresenter() {
        return new UnApproveMatePresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_wait_approve_list;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        adapter = new WaitApproveMateAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        mPresenter.loadCacheData();
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadData();
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });
    }

    @Override
    public void setData(List<WaitApproveMateListBean.ListBean> listData) {
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        if (listData.size() == 0){
            llNoData.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.GONE);
        }else {
            llNoData.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String errorMessage) {
        ToastUtil.toastCenter(this,errorMessage);
    }

    @Override
    public void setNoreMoreData(boolean noMoreData) {
        if (noMoreData){
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public void getMateDetailFinish(UnApproveMateBean bean) {
        Intent intent = new Intent(this, EditInfoActivity.class);
        intent.putExtra(EditInfoActivity.KEY_BEAN,bean);
        intent.putExtra(EditInfoActivity.SHOW_TYPE, EditInfoActivity.FROM_APPROVE_MATE);
        intent.putExtra(EditInfoActivity.KEY_SALEID, saleid);
        startActivity(intent);
    }

    @Override
    public void clickDetail(String saleid) {
        this.saleid = saleid;
        mPresenter.findMateDetail(saleid);
    }
}
