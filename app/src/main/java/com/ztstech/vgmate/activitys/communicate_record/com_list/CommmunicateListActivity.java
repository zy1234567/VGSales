package com.ztstech.vgmate.activitys.communicate_record.com_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.communicate_record.add_communcate.AddComRecordActivity;
import com.ztstech.vgmate.activitys.communicate_record.com_list.adapter.CommunicateListAdapter;
import com.ztstech.vgmate.data.beans.CommunicateListBean;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author smm
 * @date 2018/1/11
 */

public class CommmunicateListActivity extends MVPActivity<ComListContact.Presenter> implements ComListContact.View {

    public static final String ARG_RBIID = "rbiid";

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.tv_com)
    TextView tvCom;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;

    private CommunicateListAdapter adapter;

    private String rbiid;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        rbiid = getIntent().getStringExtra(ARG_RBIID);
        adapter = new CommunicateListAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        setData(null);
    }

    @Override
    protected ComListContact.Presenter initPresenter() {
        return new ComListPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_communicate_list;
    }

    @Override
    public void setData(List<CommunicateListBean.ListBean> listData) {
        listData = new ArrayList<>();
        listData.add(new CommunicateListBean.ListBean());
        listData.add(new CommunicateListBean.ListBean());
        listData.add(new CommunicateListBean.ListBean());
        listData.add(new CommunicateListBean.ListBean());
        listData.add(new CommunicateListBean.ListBean());
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @OnClick(R.id.ll_add)
    public void onViewClicked() {
        Intent intent = new Intent(this, AddComRecordActivity.class);
        intent.putExtra(CommmunicateListActivity.ARG_RBIID,rbiid);
        startActivity(intent);
    }
}
