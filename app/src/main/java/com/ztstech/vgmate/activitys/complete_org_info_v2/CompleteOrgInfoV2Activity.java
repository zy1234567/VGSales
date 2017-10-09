package com.ztstech.vgmate.activitys.complete_org_info_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.enroll_tag.EnrollTagActivity;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 完善资料第二版
 */
public class CompleteOrgInfoV2Activity extends MVPActivity<CompleteOrgInfoV2Contract.Presenter>
        implements CompleteOrgInfoV2Contract.View, View.OnClickListener {

    public static final int REQ_LOCATION = 1;

    public static final int REQ_GPS = 2;

    public static final int REQ_CATEGORY = 3;

    public static final int REQ_TAG = 4;

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_last_update)
    TextView tvLastUpdate;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.et_org_name)
    EditText etOrgName;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_gps)
    TextView tvGps;
    @BindView(R.id.et_detail_location)
    EditText etDetailLocation;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.tv_pic_video)
    TextView tvPicVideo;
    @BindView(R.id.tv_org_desc)
    TextView tvOrgDesc;
    @BindView(R.id.tv_class_desc)
    TextView tvClassDesc;
    @BindView(R.id.tv_charge_desc)
    TextView tvChargeDesc;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_teacher)
    TextView tvTeacher;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_complete_org_info_v2;
    }

    @Override
    protected CompleteOrgInfoV2Contract.Presenter initPresenter() {
        return new CompleteOrgInfoV2Presenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tvArea.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
        tvChargeDesc.setOnClickListener(this);
        tvClassDesc.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        tvPicVideo.setOnClickListener(this);
        tvGps.setOnClickListener(this);
        imgLogo.setOnClickListener(this);
        tvTag.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tvArea) {
            startActivityForResult(new Intent(this, LocationSelectActivity.class), REQ_LOCATION);
        }else if (view == tvGps) {
            startActivityForResult(new Intent(this, GpsActivity.class), REQ_GPS);
        }else if (view == tvTag) {
            startActivityForResult(new Intent(this, EnrollTagActivity.class), REQ_TAG);
        }
    }
}
