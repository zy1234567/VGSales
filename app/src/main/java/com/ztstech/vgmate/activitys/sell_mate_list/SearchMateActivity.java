package com.ztstech.vgmate.activitys.sell_mate_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.ztstech.appdomain.user_case.GetMateList;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.sell_mate_list.adapter.MateListAdapter;
import com.ztstech.vgmate.data.beans.MatelistBean;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * @author smm
 * @date 2017/11/25
 * 搜索伙伴列表界面
 */

public class SearchMateActivity extends MVPActivity<SellMateContact.Presenter> implements SellMateContact.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    MateListAdapter adapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_hint)
    TextView tvHint;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        etSearch.setHint("请输入关键词搜索伙伴");
        tvHint.setText("没有搜索结果");
        adapter = new MateListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        srl.setEnableRefresh(false);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    String keyword = etSearch.getText().toString();
                    if (!TextUtils.isEmpty(keyword)) {
                        recyclerView.scrollToPosition(0);
                        mPresenter.loadNetData(GetMateList.FILTER_ALL,keyword);
                    }
                }
                return false;
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData(GetMateList.FILTER_ALL,etSearch.getText().toString());
            }
        });

    }

    @Override
    protected SellMateContact.Presenter initPresenter() {
        return new SellMatePresenter(this);
    }

    @Override
    public void setListData(List<MatelistBean.ListBean> listData) {
        if (srl.isLoading()) {
            srl.finishLoadmore();
        }
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        if (listData.size() == 0){
            srl.setVisibility(View.GONE);
            llNoData.setVisibility(View.VISIBLE);
            KeyboardUtils.showKeyBoard(this,etSearch);
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
        if (srl.isLoading()) {
            srl.finishRefresh();
            srl.finishLoadmore();
        }
        ToastUtil.toastCenter(this, msg);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search_mate;
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        KeyboardUtils.hideKeyBoard(this,etSearch);
        finish();
    }
}
