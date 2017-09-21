package com.ztstech.vgmate.activitys.org_detail;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_detail.widget.OrgAuditDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 机构详情
 */
public class OrgDetailActivity extends MVPActivity<OrgDetailContract.Presenter> implements
        OrgDetailContract.View {

    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_complete_info)
    TextView tvCompleteInfo;

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

    }

    @OnClick(R.id.tv_confirm)
    public void onConfirmClick(View v) {
        Dialog dialog = new OrgAuditDialog(this);
        dialog.show();
    }

    @OnClick(R.id.tv_complete_info)
    public void onCompleteInfoClick(View v) {

    }
}
