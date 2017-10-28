package com.ztstech.vgmate.activitys.complete_org_info_v2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsActivity;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsPresenter;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.manager.EditOrgManagerActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.multiple_line.EditOrgInfoMultipleInputActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.org_logo.EditOrgLogoActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.pic_video.EditOrgPicVideoActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.signle_line.EditOrgInfoSignleInputActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.list.EditOrgInfoTeacherActivity;
import com.ztstech.vgmate.activitys.enroll_tag.EnrollTagActivity;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import org.json.JSONArray;

import java.text.DecimalFormat;

import butterknife.BindView;

/**
 * 完善资料第二版
 */
public class CompleteOrgInfoV2Activity extends MVPActivity<CompleteOrgInfoV2Contract.Presenter>
        implements CompleteOrgInfoV2Contract.View, View.OnClickListener {

    /**data参数*/
    public static final String ARG_RBIID = "arg_ribid";
    /**请求编辑所在地区*/
    public static final int REQ_LOCATION = 1;
    /**请求gps定位*/
    public static final int REQ_GPS = 2;
    /**请求分类标签*/
    public static final int REQ_CATEGORY = 3;
    /**请求招生标签*/
    public static final int REQ_TAG = 4;
    /**请求机构名称*/
    public static final int REQ_ORG_NAME = 5;
    /**请求机构图标*/
    public static final int REQ_LOGO = 6;
    /**请求详细地址*/
    public static final int REQ_DETAIL_LOCATION = 7;
    /**请求电话*/
    public static final int REQ_PHONE = 8;
    /**请求责任人*/
    public static final int REQ_MANAGER = 9;
    /**请求图片、视频*/
    public static final int REQ_PIC_VIDEO = 10;
    /**请求机构简介*/
    public static final int REQ_ORG_DESC = 11;
    /**请求课程简介*/
    public static final int REQ_CLASS_DESC = 12;
    /**请求收费简介*/
    public static final int REQ_CHARGE_DESC = 13;
    /**请求老师、教练*/
    public static final int REQ_TEACHERS = 14;

    /**保存的数据*/
    public static final String KEY_SAVED_INSTANCE = "key_saved_instance";


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_last_update)
    TextView tvLastUpdate;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.tv_org_name)
    TextView tvOrgName;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_gps)
    TextView tvGps;
    @BindView(R.id.tv_detail_location)
    TextView tvDetailLocation;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_user)
    TextView tvManager;
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
    @BindView(R.id.ll_last_update)
    LinearLayout llUpdate;


    private int rbiid;
    private OrgInfoBean.InfoBean infoBean;

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

        tvOrgName.setOnClickListener(this);
        tvArea.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
        tvChargeDesc.setOnClickListener(this);
        tvClassDesc.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        tvPicVideo.setOnClickListener(this);
        tvGps.setOnClickListener(this);
        imgLogo.setOnClickListener(this);
        tvTag.setOnClickListener(this);
        tvManager.setOnClickListener(this);
        tvPhone.setOnClickListener(this);
        tvTeacher.setOnClickListener(this);
        tvOrgDesc.setOnClickListener(this);
        tvDetailLocation.setOnClickListener(this);

        rbiid = getIntent().getIntExtra(ARG_RBIID, 0);

        mPresenter.loadOrgInfo(rbiid);

    }

    @Override
    public void onClick(View view) {
        if (infoBean == null) {
            infoBean = new OrgInfoBean.InfoBean();
        }
        if (view == tvArea) {
            Intent it = new Intent(this, LocationSelectActivity.class);
            it.putExtra(LocationSelectActivity.ARG_DEFAULT_AREA, infoBean.district);
            startActivityForResult(it, REQ_LOCATION);
        }else if (view == tvGps) {
            startActivityForResult(new Intent(this, GpsActivity.class), REQ_GPS);
        }else if (view == tvTag) {
            Intent it = new Intent(this, EnrollTagActivity.class);
            it.putExtra(EnrollTagActivity.ARG_TAG, infoBean.tag);
            startActivityForResult(it, REQ_TAG);
        }else if (view == tvOrgName) {
            Intent it = new Intent(this, EditOrgInfoSignleInputActivity.class);
            it.putExtra(EditOrgInfoSignleInputActivity.ARG_DATA, infoBean.oname);
            it.putExtra(EditOrgInfoSignleInputActivity.ARG_TITLE, "机构名称");
            startActivityForResult(it, REQ_ORG_NAME);
        }else if (view == imgLogo) {
            Intent it = new Intent(this, EditOrgLogoActivity.class);
            if (!TextUtils.isEmpty(infoBean.localLogoPath)) {
                it.putExtra(EditOrgLogoActivity.ARG_LOGO_URL, infoBean.localLogoPath);
            }else {
                it.putExtra(EditOrgLogoActivity.ARG_LOGO_URL, infoBean.logourl);
            }
            startActivityForResult(it, REQ_LOGO);
        }else if (view == tvDetailLocation) {
            Intent it = new Intent(this, EditOrgInfoMultipleInputActivity.class);
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_TITLE, "详细地址");
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_DATA, infoBean.address);
            startActivityForResult(it, REQ_DETAIL_LOCATION);
        }else if (view == tvPhone) {
            Intent it = new Intent(this, EditOrgInfoSignleInputActivity.class);
            it.putExtra(EditOrgInfoSignleInputActivity.ARG_TITLE, "资讯电话");
            it.putExtra(EditOrgInfoSignleInputActivity.ARG_DATA, infoBean.phone);
            startActivityForResult(it, REQ_PHONE);
        }else if (view == tvManager) {
            Intent it = new Intent(this, EditOrgManagerActivity.class);
            it.putExtra(EditOrgManagerActivity.ARG_NAME, infoBean.manager);
            it.putExtra(EditOrgManagerActivity.ARG_PHONE, infoBean.managerphone);
            startActivityForResult(it, REQ_MANAGER);
        }else if (view == tvPicVideo) {
            Intent it = new Intent(this, EditOrgPicVideoActivity.class);
            if (TextUtils.isEmpty(infoBean.localWallPath)) {
                it.putExtra(EditOrgPicVideoActivity.ARG_PIC_URLS, infoBean.advertisingwallsurl);
            }else {
                it.putExtra(EditOrgPicVideoActivity.ARG_PIC_URLS, infoBean.localWallPath);
            }
            it.putExtra(EditOrgPicVideoActivity.ARG_PIC_DESCS, infoBean.walldescribe);
            startActivityForResult(it, REQ_PIC_VIDEO);
        }else if (view == tvOrgDesc) {
            Intent it = new Intent(this, EditOrgInfoMultipleInputActivity.class);
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_TITLE, "机构简介");
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_DATA, infoBean.introduction);
            startActivityForResult(it, REQ_ORG_DESC);
        }else if (view == tvClassDesc) {
            Intent it = new Intent(this, EditOrgInfoMultipleInputActivity.class);
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_TITLE, "课程简介");
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_DATA, infoBean.courseintroduction);
            startActivityForResult(it, REQ_CLASS_DESC);
        }else if (view == tvChargeDesc) {
            Intent it = new Intent(this, EditOrgInfoMultipleInputActivity.class);
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_TITLE, "收费简介");
            it.putExtra(EditOrgInfoMultipleInputActivity.ARG_DATA, infoBean.tollintroduction);
            startActivityForResult(it, REQ_CHARGE_DESC);
        }else if (view == tvTeacher) {
            Intent it = new Intent(this, EditOrgInfoTeacherActivity.class);
            it.putExtra(EditOrgInfoTeacherActivity.ARG_RBIID, rbiid);
            startActivityForResult(it, REQ_TEACHERS);
        }else if (view == tvCategory) {
            Intent it = new Intent(this, CategoryTagsActivity.class);
            it.putExtra(CategoryTagsActivity.ARG_IDS, infoBean.otype);
            it.putExtra(CategoryTagsActivity.ARG_NAMES, tvCategory.getText().toString());
            startActivityForResult(it, REQ_CATEGORY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (infoBean != null) {
            outState.putString(KEY_SAVED_INSTANCE, new Gson().toJson(infoBean));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String saved = savedInstanceState.getString(KEY_SAVED_INSTANCE);
        if (!TextUtils.isEmpty(saved)) {
            infoBean = new Gson().fromJson(saved, OrgInfoBean.InfoBean.class);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (infoBean == null) {
            infoBean = new OrgInfoBean.InfoBean();
        }
        if (REQ_CATEGORY == requestCode) {
            infoBean.otype = data.getStringExtra(CategoryTagsActivity.PARAM_ID);
        }else if (REQ_CHARGE_DESC == requestCode) {
            infoBean.tollintroduction =
                    data.getStringExtra(EditOrgInfoMultipleInputActivity.RESULT_TEXT);
        }else if (REQ_CLASS_DESC == requestCode) {
            infoBean.courseintroduction =
                    data.getStringExtra(EditOrgInfoMultipleInputActivity.RESULT_TEXT);
        }else if (REQ_DETAIL_LOCATION == requestCode) {
            infoBean.address = data.getStringExtra(EditOrgInfoMultipleInputActivity.RESULT_TEXT);
        }else if (REQ_GPS == requestCode) {
            infoBean.gps = data.getStringExtra(GpsActivity.RESULT_LATITUDE)
                    .concat(",")
                    .concat(data.getStringExtra(GpsActivity.RESULT_LONGITUDE));
        }else if (REQ_ORG_NAME == requestCode) {
            infoBean.oname = data.getStringExtra(EditOrgInfoSignleInputActivity.RESULT_TEXT);
        }else if (REQ_LOGO == requestCode) {
            infoBean.localLogoPath = data.getStringExtra(EditOrgLogoActivity.RESULT_IMAGE_PATH);
        }else if (REQ_LOCATION == requestCode) {
            infoBean.district = data.getStringExtra(LocationSelectActivity.RESULT_A);
        }else if (REQ_PHONE == requestCode) {
            infoBean.phone = data.getStringExtra(EditOrgInfoSignleInputActivity.RESULT_TEXT);
        }else if (REQ_MANAGER == requestCode) {
            infoBean.manager = data.getStringExtra(EditOrgManagerActivity.RESULT_NAME);
            infoBean.managerphone = data.getStringExtra(EditOrgManagerActivity.RESULT_PHONE);
            infoBean.manageruid = null;
        }else if (REQ_PIC_VIDEO == requestCode) {
            infoBean.localWallPath = data.getStringExtra(EditOrgPicVideoActivity.RESULT_IMG_FILE_PATH);
            infoBean.walldescribe = data.getStringExtra(EditOrgPicVideoActivity.RESULT_IMG_DESC);
        }else if (REQ_ORG_DESC == requestCode) {
            infoBean.introduction = data.getStringExtra(EditOrgInfoMultipleInputActivity.RESULT_TEXT);
        }else if (REQ_TEACHERS == requestCode) {

        }else if (REQ_TAG == requestCode) {
            infoBean.tag = data.getStringExtra(EnrollTagActivity.RESULT_TAG);
        }

        showOrgInfo(infoBean);

    }

    @Override
    public void showOrgInfo(OrgInfoBean.InfoBean bean) {
        infoBean = bean;
        if (infoBean == null) {
            return;
        }

        if (!TextUtils.isEmpty(infoBean.updatename) && !TextUtils.isEmpty(infoBean.updatename)) {
            tvLastUpdate.setText("最后更新：" +
                    TimeUtils.InformationTime(infoBean.updatetime) + "(" + infoBean.updatename + ")");
            llUpdate.setVisibility(View.VISIBLE);
        }else {
            llUpdate.setVisibility(View.GONE);
        }

        tvOrgName.setText(infoBean.oname);
        if (TextUtils.isEmpty(infoBean.localLogoPath)) {
            Glide.with(this).load(infoBean.logosurl).into(imgLogo);
        }else {
            Glide.with(this).load(infoBean.localLogoPath).into(imgLogo);
        }

        tvPhone.setText(infoBean.phone);
        tvArea.setText(LocationUtils.getFormedString(infoBean.district));

        if (!TextUtils.isEmpty(infoBean.gps)) {
            String[] gpss = infoBean.gps.split(",");
            if (gpss.length == 2) {
                DecimalFormat format = new DecimalFormat("#.00");
                try {
                    String la = format.format(Double.parseDouble(gpss[0]));
                    String lo = format.format(Double.parseDouble(gpss[1]));
                    tvGps.setText(lo + "°E," + la + "°N");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        tvManager.setText(infoBean.manager + " " + infoBean.managerphone);
        tvDetailLocation.setText(infoBean.address);

        if (!TextUtils.isEmpty(infoBean.otype)) {
            String[] otypes = infoBean.otype.split(",");
            if (otypes.length == 0) {
                tvCategory.setText("");
            }else {
                String otypenames = "";
                for (String tp : otypes) {
                    otypenames = otypenames.concat(CategoryUtil.getCategoryName(tp)).concat(",");
                }
                if (otypenames.length() > 0) {
                    otypenames = otypenames.substring(0, otypenames.length() - 1);
                }
                tvCategory.setText(otypenames);
            }
        }

        if (!TextUtils.isEmpty(infoBean.localWallPath)) {
            //如果筛选的本地数据不为空
            String[] pics = infoBean.localWallPath.split(",");
            tvPicVideo.setText(pics.length + "图片，0视频");
        }else if (!TextUtils.isEmpty(infoBean.advertisingwallsurl)) {
            String[] pics = infoBean.advertisingwallsurl.split(",");
            tvPicVideo.setText(pics.length + "图片，0视频");
        }else {
            tvPicVideo.setText("0图片，0视频");
        }

        String orgInfo = infoBean.introduction;
        if (!TextUtils.isEmpty(orgInfo)) {
            if (orgInfo.length() > 10) {
                orgInfo = infoBean.introduction.substring(0, 10) + "...";
            }
        }

        tvOrgDesc.setText(orgInfo);

        String classInfo = infoBean.courseintroduction;
        if (!TextUtils.isEmpty(classInfo)) {
            if (classInfo.length() > 10) {
                classInfo = infoBean.courseintroduction.substring(0, 10) + "...";
            }
        }
        tvClassDesc.setText(classInfo);

        String chargeInfo = infoBean.tollintroduction;
        if (!TextUtils.isEmpty(chargeInfo)) {
            if (chargeInfo.length() > 10) {
                chargeInfo = infoBean.tollintroduction.substring(0, 10) + "...";
            }
        }
        tvChargeDesc.setText(chargeInfo);

        try {
            if (!TextUtils.isEmpty(infoBean.tag)) {
                JSONArray jsonArray = new JSONArray(infoBean.tag);
                String tagStr = "";
                for (int i = 0; i < jsonArray.length(); i++) {
                    tagStr = tagStr + jsonArray.get(i).toString() + ",";
                }
                if (tagStr.length() > 0) {
                    tagStr = tagStr.substring(0, tagStr.length() - 1);
                }
                tvTag.setText(tagStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadOrgInfoError(String errmsg) {
        ToastUtil.toastCenter(this, "获取机构资料失败：" + errmsg);
    }

    @Override
    public void editOrgInfoFinish(@Nullable String errmsg) {
        if (errmsg != null) {
            ToastUtil.toastCenter(this, "编辑机构资料失败：" + errmsg);
        }
    }
}
