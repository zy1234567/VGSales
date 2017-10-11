package com.ztstech.vgmate.activitys.org_detail.dialog.org_confirm;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.ViewImpl;
import com.ztstech.vgmate.activitys.add_sell_mate.AddSellMateActivity;

import rx.functions.Action1;

/**
 * Created by zhiyuan on 2017/10/10.
 */

public class OrgConfirmDialog extends Dialog implements View.OnClickListener,
        OrgConfirmContract.View {

    public static final int REQ_CONTACT = 1;

    private View contentView;

    private TextView tvOrgNameTitle, tvCategoryTitle, tvCategory, tvAreaTitle, tvArea, tvGpsTitle,
            tvGps, tvLocationTitle, tvPhoneTitle, tvContact, tvSubmit;
    private EditText etOrgName, etLocation, etPhone;
    private ImageView imgClose;

    private ViewImpl<OrgConfirmContract.Presenter> viewImpl;
    private OrgConfirmContract.Presenter presenter;


    public OrgConfirmDialog(@NonNull Context context) {
        super(context);

        viewImpl = new ViewImpl<>(context);
        presenter = new OrgConfirmPresenter(this);

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

        setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        if (view == imgClose) {
            dismiss();
        }else if (view == tvContact) {
            //打开联系人
            RxPermissions rxPermissions = new RxPermissions((Activity) getContext());
            rxPermissions
                    .request(Manifest.permission.READ_CONTACTS)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {

                            if (aBoolean) {
                                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                                ((Activity) getContext()).startActivityForResult(i , REQ_CONTACT);
                            }else {
                                Toast.makeText(getContext(), "无权限", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else if (view == tvSubmit) {

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
}
