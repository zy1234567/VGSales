package com.ztstech.vgmate.activitys.rob_chance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.communicate_record.com_list.adapter.CommunicateListAdapter;
import com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceAdapter;
import com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder.PASSER_CHECK_IN;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChanceActivity extends MVPActivity<RobChanceContract.Presenter> implements RobChanceContract.View {
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private RobChanceAdapter adapter;
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        adapter = new RobChanceAdapter(new RobChanceViewHolder.lockorgCallBack() {
            @Override
            public void lockOrgClick(String rbiid, Object object, TextView textView,int i) {
                mPresenter.lockOrg(rbiid,textView,object,i);
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        srl.autoRefresh();
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadData();
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });
    }

    @Override
    public void showLoading(String message) {

    }

    @Override
    public void hideLoading(@Nullable String errorMessage) {

    }

    @Override
    public boolean isViewFinish() {
        return false;
    }

    @Override
    protected RobChanceContract.Presenter initPresenter() {
        return new RobChancePresenter(this);
    }

    @Override
    public void setData(List<RobChanceBean.ListBean> listData) {
        srl.finishLoadmore();
        srl.finishRefresh();
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        if (listData.size() == 0){
            recycler.setVisibility(View.GONE);
        }else {
            recycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setDataPage(RobChanceBean.PagerBean pagerBean) {
        topBar.setTitle("可抢机会".concat("(").concat(String.valueOf(pagerBean.totalRows).concat(")")));
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void onSubmitFinish(String errorMessage, TextView textView, Object object,int i) {
        textView.setText("处理中");
        textView.setBackgroundResource(R.drawable.bg_c_1_f_009);
        Intent intent;
        if (i == PASSER_CHECK_IN){
//            intent = new Intent(this,)
        }
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_rob_chance;
    }
}
