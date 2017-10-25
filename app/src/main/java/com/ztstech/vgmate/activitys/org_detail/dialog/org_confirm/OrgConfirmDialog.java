package com.ztstech.vgmate.activitys.org_detail.dialog.org_confirm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.ViewImpl;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.ContractUtils;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.utils.ViewUtils;

/**
 * Created by zhiyuan on 2017/10/10.
 * 审核通过
 */

public class OrgConfirmDialog extends Dialog implements View.OnClickListener,
        OrgConfirmContract.View {

    public static final int REQ_CONTACT = 1;
    public static final int REQ_GPS = 2;

    private View contentView;

    private TextView tvOrgNameTitle, tvCategoryTitle, tvCategory, tvAreaTitle, tvArea, tvGpsTitle,
            tvGps, tvLocationTitle, tvPhoneTitle, tvContact, tvSubmit;
    private EditText etOrgName, etLocation, etPhone;
    private ImageView imgClose;

    private ViewImpl<OrgConfirmContract.Presenter> viewImpl;
    private OrgConfirmContract.Presenter presenter;

    private Activity activityContext;
    private GetOrgListItemsBean.ListBean bean;

    private OnConfirmListener listener;


    public OrgConfirmDialog(@NonNull Context context, final GetOrgListItemsBean.ListBean bean) {
        super(context);

        activityContext = (Activity) context;
        this.bean = bean;


        viewImpl = new ViewImpl<>(context);
        presenter = new OrgConfirmPresenter(this);

        ViewUtils.setDialogFullScreen(this);

        contentView = getLayoutInflater().inflate(R.layout.dialog_org_pass, null, false);

        tvOrgNameTitle = contentView.findViewById(R.id.tv_org_name);
        etOrgName = contentView.findViewById(R.id.et_org_name);
        tvCategoryTitle = contentView.findViewById(R.id.tv_category_title);
        tvCategory = contentView.findViewById(R.id.tv_category);
        tvAreaTitle = contentView.findViewById(R.id.tv_area_title);
        tvArea = contentView.findViewById(R.id.tv_area);
        tvGpsTitle = contentView.findViewById(R.id.tv_gps_title);
        tvGps = contentView.findViewById(R.id.tv_gps);
        tvLocationTitle = contentView.findViewById(R.id.tv_location_title);
        etLocation = contentView.findViewById(R.id.et_location);
        tvPhoneTitle = contentView.findViewById(R.id.tv_phone_title);
        etPhone = contentView.findViewById(R.id.et_phone);
        tvContact = contentView.findViewById(R.id.tv_contact);
        tvSubmit = contentView.findViewById(R.id.tv_submit);
        imgClose = contentView.findViewById(R.id.img_close);

        setContentView(contentView);

        imgClose.setOnClickListener(this);
        tvContact.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvGps.setOnClickListener(this);

        setCancelable(false);


        //设置为红色 *
        TextView[] titles = new TextView[] {tvOrgNameTitle, tvCategoryTitle, tvAreaTitle, tvGpsTitle,
                tvLocationTitle, tvPhoneTitle};
        for (TextView tv: titles) {
            tv.setText(ViewUtils.getDiffColorSpan(null,
                    new String[] {tv.getText().toString().substring(0, 1),
                            tv.getText().toString().substring(1, tv.getText().length())},
                    new int[] {ContextCompat.getColor(context, R.color.color_006),
                            ContextCompat.getColor(context, R.color.color_100)}));
        }

        //设置数据
        etOrgName.setText(bean.rbioname);
        tvCategory.setText(CategoryUtil.getCategoryId(bean.rbiotype));
        tvArea.setText(LocationUtils.getLocationNameByCode(bean.rbidistrict));
        // TODO: 2017/10/11 后台没有gps信息
        etLocation.setText(bean.rbiaddress);
        etPhone.setText(bean.rbiphone);

    }

    public void setOnConfirmListener(OnConfirmListener listener) {
        this.listener = listener;
    }

    public void setPhone(String text) {
        if (!TextUtils.isEmpty(text)) {
            etPhone.setText(text.trim());
        }
    }

    public void setGps(String gps) {
        tvGps.setText(gps);
    }

    public void setDetailLocationByGps(String gpsLocation) {
        if (etLocation.getText().length() == 0) {
            etLocation.setText(gpsLocation);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == imgClose) {
            dismiss();
        }else if (view == tvContact) {
            //打开联系人
            ContractUtils.toContract(activityContext, REQ_CONTACT);
        }else if (view == tvSubmit) {
            submit();
        }else if (view == tvGps) {
            Intent it = new Intent(activityContext, GpsActivity.class);
            activityContext.startActivityForResult(it, REQ_GPS);
        }
    }

    @Override
    public void showLoading(String message) {
        viewImpl.showLoading(message);
    }

    @Override
    public void hideLoading(@Nullable String errorMessage) {
        viewImpl.hideLoading(errorMessage);
    }

    @Override
    public boolean isViewFinish() {
        return !isShowing();
    }

    private void submit() {
        String oname = etOrgName.getText().toString();
        String otype = bean.rbiotype;
        String district = bean.rbidistrict;
        String gps = tvGps.getText().toString();
        String detailLocation = etLocation.getText().toString();
        String phone = etPhone.getText().toString();

        if (TextUtils.isEmpty(oname)) {
            ToastUtil.toastCenter(activityContext, "请输入机构名称！");
            return;
        }
        if (TextUtils.isEmpty(otype)) {
            ToastUtil.toastCenter(activityContext, "请选择分类标签！");
            return;
        }
        if (TextUtils.isEmpty(district)) {
            ToastUtil.toastCenter(activityContext, "请选择所在区县！");
            return;
        }
        if (TextUtils.isEmpty(gps)) {
            ToastUtil.toastCenter(activityContext, "请选择gps位置！");
            return;
        }
        if (TextUtils.isEmpty(detailLocation)) {
            ToastUtil.toastCenter(activityContext, "请输入详细地址！");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.toastCenter(activityContext, "请输入资讯电话！");
            return;
        }

        presenter.submit(String.valueOf(bean.rbiid), oname, otype, district, detailLocation,
                gps, phone);
        tvSubmit.setText("提交中");
        tvSubmit.setEnabled(false);
        imgClose.setClickable(false);

    }

    @Override
    public void onFinish(@Nullable String errmsg) {
        if (!isShowing() || activityContext.isFinishing()) {
            return;
        }
        imgClose.setClickable(true);
        tvSubmit.setEnabled(true);
        tvSubmit.setText("提交");
        if (errmsg == null) {
            dismiss();
            ToastUtil.toastCenter(activityContext, "提交成功！");
            if (listener != null) {
                listener.onConfirm();
            }
        }else {
            ToastUtil.toastCenter(activityContext, "提交失败：" + errmsg);
        }
    }

    public interface OnConfirmListener {

        void onConfirm();
    }
}
