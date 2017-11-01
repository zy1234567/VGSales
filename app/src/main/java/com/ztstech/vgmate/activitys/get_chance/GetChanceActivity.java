package com.ztstech.vgmate.activitys.get_chance;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.get_chance.adapter.GetChanceRecyclerAdapter;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.List;

import butterknife.BindView;

/**
 * 抢单、沟通记录
 */
public class GetChanceActivity extends MVPActivity<GetChanceContract.Presenter> implements
        GetChanceContract.View {

    /**
     * 传入机构rbiid
     */
    public static final String ARG_RBIID = "arg_rbiid";

    /**
     * 传入机构comid
     */
    public static final String ARG_COMID = "arg_comid";

    /**
     * 传入comid必须传入orgid
     */
    public static final String ARG_ORGID = "arg_orgid";

    /**
     * 传入机构标题
     */
    public static final String ARG_TITLE = "arg_title";


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.top_bar)
    TopBar topBar;

    @BindView(R.id.et)
    EditText editText;

    @BindView(R.id.btn_commit)
    Button btSubmit;

    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    /**
     * 不同id对应不同沟通记录请求方式
     */
    private String rbiid;
    private String comid;
    private String orgid;

    private GetChanceRecyclerAdapter recyclerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_get_chance;
    }

    @Override
    protected GetChanceContract.Presenter initPresenter() {
        return new GetChancePresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        this.rbiid = getIntent().getStringExtra(ARG_RBIID);
        this.comid = getIntent().getStringExtra(ARG_COMID);
        this.orgid = getIntent().getStringExtra(ARG_ORGID);

        if (TextUtils.isEmpty(rbiid) && TextUtils.isEmpty(comid)) {
            throw new RuntimeException("rbiid 与 comid 至少需要传入一个");
        }

        String title = getIntent().getStringExtra(ARG_TITLE);
        if (!TextUtils.isEmpty(title)) {
            topBar.setTitle(title);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new GetChanceRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                btSubmit.setEnabled(s.length() != 0);
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    ToastUtil.toastCenter(GetChanceActivity.this, "请输入沟通内容！");
                    return;
                }
                btSubmit.setEnabled(false);
                if (!TextUtils.isEmpty(rbiid)) {
                    mPresenter.addCommunicateByRbiid(rbiid, msg);
                }else {
                    mPresenter.addCommunicateByComid("00", orgid, comid, msg);
                }
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.refreshData(comid, rbiid);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.loadData(comid, rbiid);
            }
        });

        mPresenter.refreshData(comid, rbiid);
    }

    @Override
    public void onAddCommunicateFinish(@Nullable String errmsg) {
        editText.setText("");
        btSubmit.setEnabled(true);
        if (errmsg == null) {
            ToastUtil.toastCenter(this, "提交成功");
            KeyboardUtils.hideKeyBoard(this, editText);
            mPresenter.refreshData(comid, rbiid);
        }else {
            ToastUtil.toastCenter(this, "提交失败：" + errmsg);
        }
    }

    @Override
    public void onRefreshFinish(List<CommunicationHistoryBean.ListBean> items, @Nullable String errmsg) {
        smartRefreshLayout.finishRefresh();
        if (errmsg == null) {
            recyclerAdapter.setListData(items);
            recyclerAdapter.notifyDataSetChanged();
        }else {
            ToastUtil.toastCenter(this, errmsg);
        }
    }

    @Override
    public void onLoadFinish(List<CommunicationHistoryBean.ListBean> items, @Nullable String errmsg) {
        smartRefreshLayout.finishLoadmore();
        if (errmsg == null) {
            recyclerAdapter.setListData(items);
            recyclerAdapter.notifyDataSetChanged();
        }else {
            ToastUtil.toastCenter(this, errmsg);
        }
    }
}
