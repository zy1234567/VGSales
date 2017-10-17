package com.ztstech.vgmate.activitys.org_detail;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.CompleteOrgInfoV2Activity;
import com.ztstech.vgmate.activitys.get_chance.GetChanceActivity;
import com.ztstech.vgmate.activitys.org_detail.dialog.org_confirm.OrgConfirmDialog;
import com.ztstech.vgmate.activitys.org_detail.dialog.org_delete.OrgDeleteDialog;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 机构详情
 */
public class OrgDetailActivity extends MVPActivity<OrgDetailContract.Presenter> implements
        OrgDetailContract.View {

    /**请求完善资料*/
    public static final int REQ_COMPLETE_INFO = 1;


    /**
     * intent传入参数，使用gson格式化
     */
    public static final String ARG_ORG_BEAN = "arg_org_bean";

    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_complete_info)
    TextView tvCompleteInfo;
    @BindView(R.id.tv_connect_time)
    TextView tvConnectTimes;            //沟通次数
    @BindView(R.id.tv_delete)
    TextView tvDelete;

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
        Dialog dialog = new OrgConfirmDialog(this, bean);
        dialog.show();
    }

    @OnClick(R.id.tv_complete_info)
    public void onCompleteInfoClick(View v) {
        Intent it = new Intent(this, CompleteOrgInfoV2Activity.class);
        it.putExtra(CompleteOrgInfoV2Activity.ARG_RBIID, bean.rbiid);
        startActivityForResult(it, REQ_COMPLETE_INFO);
    }

    @OnClick(R.id.tv_connect_time)
    public void onConnectTimesClick(View v) {
        //沟通记录
        startActivity(new Intent(this, GetChanceActivity.class));
    }

    @OnClick(R.id.tv_delete)
    public void onDeleteOrgClick(View v) {
        //删除机构
        Dialog dialog = new OrgDeleteDialog(this, bean);
        dialog.show();
    }


}
