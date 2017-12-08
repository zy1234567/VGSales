package com.ztstech.vgmate.activitys.search_org;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_follow.adapter.OrgFollowListAdapter;
import com.ztstech.vgmate.data.beans.SearchOrgListBean;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;
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
    SmartRefreshLayout srl;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;

    private OrgFollowListAdapter adapter;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

    }

    @Override
    protected SearchOrgContact.Presenter initPresenter() {
        return new SearchOrgPresenter(this);
    }

    @Override
    public void setListData(SearchOrgListBean.ListBean listData) {

    }

    @Override
    public void showError(String msg) {
        ToastUtil.toastCenter(this, msg);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search_org;
    }

    @OnClick({R.id.et_search, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search:
                break;
            case R.id.tv_cancel:
                KeyboardUtils.hideKeyBoard(this,etSearch);
                finish();
                break;
            default:
                break;
        }
    }
}
