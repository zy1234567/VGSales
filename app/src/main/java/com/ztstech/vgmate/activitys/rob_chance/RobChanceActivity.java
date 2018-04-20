package com.ztstech.vgmate.activitys.rob_chance;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceAdapter;
import com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder;
import com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.utils.HUDUtils;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.List;

import butterknife.BindView;

import static com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder.ORG_CHECK_IN_OR_CALIM;
import static com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder.PASSER_CHECK_IN;
import static com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty.ORG_BEAN_ROB;
import static com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty.ORG_IDENTITY;

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
    private KProgressHUD kProgressHUD;
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
//        hud.create(this,"请求抢单中");
        kProgressHUD =HUDUtils.create(this);
        adapter = new RobChanceAdapter(new RobChanceViewHolder.lockorgCallBack() {
            @Override
            public void lockOrgClick(String rbiid, String object, TextView textView,int i,String j) {
                //判断 j 是否为null 如果不为null，直接请求剩余时间接口，不在去走锁定订单的接口
                mPresenter.lockOrg(rbiid,textView,object,i,j);
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
    public void onSubmitFinish(String errorMessage, TextView textView, String object,int i,String j) {
        kProgressHUD.show();
        textView.setText("处理中");
        textView.setBackgroundResource(R.drawable.bg_c_1_f_009);
        Intent intent  = new Intent(this, RobIngActivty.class);
        intent.putExtra(ORG_BEAN_ROB,object);
        if (i == PASSER_CHECK_IN){
            intent.getIntExtra(ORG_IDENTITY,PASSER_CHECK_IN);
        }else{
            intent.getIntExtra(ORG_IDENTITY,ORG_CHECK_IN_OR_CALIM);
        }
        startActivity(intent);
        kProgressHUD.dismiss();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_rob_chance;
    }
}
