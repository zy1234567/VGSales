package com.ztstech.vgmate.activitys.sell_mate_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.sell_mate_list.adapter.MateListAdapter;
import com.ztstech.vgmate.data.beans.MatelistBean;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.BottomDoubleSelectDialog;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 销售伙伴列表
 *
 * @author smm
 */
public class SellMateListActivity extends MVPActivity<SellMateContact.Presenter> implements SellMateContact.View {

    public static final String KEY_MATE_NUM = "key_mate_num";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.top_bar)
    TopBar topBar;

    MateListAdapter adapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sell_mate_list;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        topBar.setTitle("销售伙伴".concat("(").concat(getIntent().getStringExtra(KEY_MATE_NUM)).concat(")"));
        adapter = new MateListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mPresenter.loadCacheData();
        srl.autoRefresh();
        topBar.getRightImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterDialog();
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });

        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadNetData();
            }
        });


    }


    @Override
    protected SellMateContact.Presenter initPresenter() {
        return new SellMatePresenter(this);
    }

    private void showFilterDialog() {
        new BottomDoubleSelectDialog(this, "查看全部", "只看我的直属伙伴", new BottomDoubleSelectDialog.ClickListener() {
            @Override
            public void onClickOne() {

            }

            @Override
            public void onClickTwo() {

            }
        });
    }

    @Override
    public void setListData(List<MatelistBean.ListBean> listData) {
        srl.finishLoadmore();
        srl.finishRefresh();
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        if (listData.size() == 0){
            srl.setVisibility(View.GONE);
            llNoData.setVisibility(View.VISIBLE);
        }else {
            srl.setVisibility(View.VISIBLE);
            llNoData.setVisibility(View.GONE);
        }
    }

    @Override
    public void setNoMoreData(boolean noMoreData) {
        srl.setEnableAutoLoadmore(!noMoreData);
    }

    @Override
    public void showError(String msg) {
        ToastUtil.toastCenter(this,msg);
    }

}
