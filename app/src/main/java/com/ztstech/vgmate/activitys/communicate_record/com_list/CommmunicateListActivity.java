package com.ztstech.vgmate.activitys.communicate_record.com_list;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.communicate_record.add_communcate.AddComRecordActivity;
import com.ztstech.vgmate.activitys.communicate_record.com_list.adapter.CommunicateListAdapter;
import com.ztstech.vgmate.data.beans.GetComRecordBean;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.List;

import butterknife.BindView;
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
        mPresenter.loadData(rbiid);
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
    public void setData(List<GetComRecordBean.ListBean> listData) {
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void setListData(List<GetComRecordBean.ListBean> listData) {
        srl.finishRefresh();
        srl.finishLoadmore();
        if (listData.size() == 0){
            recycler.setVisibility(View.GONE);
        }else {
            recycler.setVisibility(View.VISIBLE);
        }
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ll_add)
    public void onViewClicked() {
        Intent intent = new Intent(this, AddComRecordActivity.class);
        intent.putExtra(CommmunicateListActivity.ARG_RBIID,rbiid);
        startActivity(intent);
    }
}
