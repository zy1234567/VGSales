package com.ztstech.vgmate.activitys.org_detail;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.CompleteOrgInfoV2Activity;
import com.ztstech.vgmate.activitys.get_chance.GetChanceActivity;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.activitys.org_detail.dialog.org_confirm.OrgConfirmDialog;
import com.ztstech.vgmate.activitys.org_detail.dialog.org_delete.OrgDeleteDialog;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.utils.ContractUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 机构详情
 */
public class OrgDetailActivity extends MVPActivity<OrgDetailContract.Presenter> implements
        OrgDetailContract.View {

    /**请求完善资料*/
    public static final int REQ_COMPLETE_INFO = 1;

    /**设置刷新result*/
    public static final String RESULT_REFRESH = "result_refresh";

    /**
     * 当前状态
     */
    public static final String ARG_STATUS = "arg_status";


    /**
     * intent传入参数，使用gson格式化
     */
    public static final String ARG_ORG_BEAN = "arg_org_bean";

    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    /**完善资料*/
    @BindView(R.id.tv_complete_info)
    TextView tvCompleteInfo;

    /**沟通次数*/
    @BindView(R.id.tv_connect_time)
    TextView tvConnectTimes;
    /**删除机构*/
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    /**审批*/
    @BindView(R.id.tv_approval)
    TextView tvApproval;

    private OrgConfirmDialog confirmDialog;

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

        checkBottomButtons();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK ) {
            return;
        }
        if (requestCode == OrgConfirmDialog.REQ_CONTACT) {
            ContractUtils.ContractUser user = ContractUtils.readContract(this, data);
            if (confirmDialog != null && confirmDialog.isShowing()) {
                confirmDialog.setPhone(user.phone);
            }
        }else if (requestCode == OrgConfirmDialog.REQ_GPS) {
            if (confirmDialog != null && confirmDialog.isShowing()) {
                double lat = data.getDoubleExtra(GpsActivity.RESULT_LATITUDE, 0);
                double lot = data.getDoubleExtra(GpsActivity.RESULT_LONGITUDE, 0);
                String location = data.getStringExtra(GpsActivity.RESULT_LOCATION);

                String gps = String.valueOf(lat) + "," + String.valueOf(lot);
                confirmDialog.setGps(gps);
                confirmDialog.setDetailLocationByGps(location);
            }

        }else if (requestCode == OrgConfirmDialog.REQ_CATEGORY) {
            if (confirmDialog != null && confirmDialog.isShowing()) {
                String category = data.getStringExtra(CategoryTagsActivity.PARAM_ID);
                String categoryText = data.getStringExtra(CategoryTagsActivity.PARAM_NAME);
                confirmDialog.setCategory(category, categoryText);
            }
        }else if (requestCode == OrgConfirmDialog.REQ_LOCATION) {
            if (confirmDialog != null && confirmDialog.isShowing()) {
                String area = data.getStringExtra(LocationSelectActivity.RESULT_A);
                String name = data.getStringExtra(LocationSelectActivity.RESULT_NAME);
                confirmDialog.setDistrict(area, name);
            }
        }
    }

    @OnClick(R.id.tv_confirm)
    public void onConfirmClick(View v) {
        confirmDialog = new OrgConfirmDialog(this, bean);
        confirmDialog.setOnConfirmListener(new OrgConfirmDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                finishAndRefresh();
            }
        });
        confirmDialog.show();
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
        Intent it = new Intent(this, GetChanceActivity.class);
        it.putExtra(GetChanceActivity.ARG_RBIID, String.valueOf(bean.rbiid));
        it.putExtra(GetChanceActivity.ARG_TITLE, bean.rbioname);
        startActivity(it);
    }

    @OnClick(R.id.tv_approval)
    public void onApprovalClick(View v) {
        //点击认领审批

    }

    @OnClick(R.id.tv_delete)
    public void onDeleteOrgClick(View v) {
        //删除机构
        OrgDeleteDialog dialog = new OrgDeleteDialog(this, bean);
        dialog.setOnDeleteListener(new OrgDeleteDialog.OnDeleteListener() {
            @Override
            public void onDelete() {
                finishAndRefresh();
            }
        });
        dialog.show();
    }

    /**
     * 关闭当前页面，刷新上一个界面列表
     */
    public void finishAndRefresh() {
        Intent it = new Intent();
        it.putExtra(RESULT_REFRESH, true);
        setResult(RESULT_OK, it);
        finish();
    }

    /**
     * 根据传入状态，设置底部按钮
     */
    private void checkBottomButtons() {
        String status = getIntent().getStringExtra(ARG_STATUS);
        if (TextUtils.isEmpty(status)) {
            throw new RuntimeException("未传入状态");
        }

        if (Constants.ORG_STATUS_LOCATED.equals(status)) {
            //已确定
            tvConfirm.setVisibility(View.GONE);
        }else if (Constants.ORG_STATUS_UN_CONFIRM.equals(status)) {
            //待确认
            tvApproval.setVisibility(View.GONE);
        }else if (Constants.ORG_STATUS_WEB.equals(status)) {
            //网页端
            tvApproval.setVisibility(View.GONE);
            tvConfirm.setVisibility(View.GONE);
            tvDelete.setVisibility(View.GONE);
        }else if (Constants.ORG_STATUS_UN_APPROVE.equals(status)) {
            //待审批
            tvConfirm.setVisibility(View.GONE);
        }

    }
}
