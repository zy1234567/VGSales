package com.ztstech.vgmate.activitys.search_org;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectDialog;
import com.ztstech.vgmate.activitys.search_org.adapter.SearchOrgAdapter;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author smm
 * @date 2017/12/8
 * 搜索机构界面
 */

public class SearchOrgActivity extends MVPActivity<SearchOrgContact.Presenter> implements SearchOrgContact.View {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.img_arrow_pro)
    ImageView imgArrowPro;
    @BindView(R.id.ll_province)
    LinearLayout llProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.img_arrow_city)
    ImageView imgArrowCity;
    @BindView(R.id.ll_city)
    LinearLayout llCity;
    @BindView(R.id.tv_district)
    TextView tvDistrict;
    @BindView(R.id.img_arrow_district)
    ImageView imgArrowDistrict;
    @BindView(R.id.ll_district)
    LinearLayout llDistrict;

    private SearchOrgAdapter adapter;

    private String keyword;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        etSearch.setHint("输入要搜索的机构名称");
        adapter = new SearchOrgAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        smartRefreshLayout.setEnableRefresh(false);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                        keyword = etSearch.getText().toString();
                        adapter.setKeyWord(keyword);
                        mPresenter.LoadDataByKeword(keyword);
                    }
                }
                return false;
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendDada(keyword);
            }
        });
    }

    @Override
    protected SearchOrgContact.Presenter initPresenter() {
        return new SearchOrgPresenter(this);
    }

    @Override
    public void setListData(List<OrgFollowlistBean.ListBean> listData) {
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        KeyboardUtils.hideKeyBoard(this, etSearch);
        if (listData.size() == 0) {
            llNoData.setVisibility(View.VISIBLE);
            smartRefreshLayout.setVisibility(View.GONE);
        } else {
            llNoData.setVisibility(View.GONE);
            smartRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String msg) {
        ToastUtil.toastCenter(this, msg);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search_org;
    }

    @OnClick({R.id.tv_cancel,R.id.ll_province})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                KeyboardUtils.hideKeyBoard(this, etSearch);
                finish();
                break;
            case R.id.ll_province:
//                LocationSelectDialog dialog =  new LocationSelectDialog(this);
//                dialog.show();
                break;
            default:
                break;
        }
    }

}
