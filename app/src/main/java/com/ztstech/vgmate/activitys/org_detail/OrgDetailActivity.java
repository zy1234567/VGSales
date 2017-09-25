package com.ztstech.vgmate.activitys.org_detail;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_detail.dialog.OrgAuditDialog;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 机构详情
 */
public class OrgDetailActivity extends MVPActivity<OrgDetailContract.Presenter> implements
        OrgDetailContract.View {

    /**
     * intent传入参数，使用gson格式化
     */
    public static final String ARG_ORG_BEAN = "arg_org_bean";

    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_complete_info)
    TextView tvCompleteInfo;

    private GetOrgListItemsBean.ListBean bean;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_detail;
    }

    @Override
    protected OrgDetailContract.Presenter initPresenter() {
        return new OrgDetailPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        String json = getIntent().getStringExtra(ARG_ORG_BEAN);
        if (TextUtils.isEmpty(json)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        this.bean = new Gson().fromJson(json, GetOrgListItemsBean.ListBean.class);
    }

    @OnClick(R.id.tv_confirm)
    public void onConfirmClick(View v) {
        Dialog dialog = new OrgAuditDialog(this, bean);
        dialog.show();
    }

    @OnClick(R.id.tv_complete_info)
    public void onCompleteInfoClick(View v) {

    }
}
