package com.ztstech.vgmate.activitys.complete_org_info_v2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.enroll_tag.EnrollTagActivity;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.weigets.TopBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import butterknife.BindView;

/**
 * 完善资料第二版
 */
public class CompleteOrgInfoV2Activity extends MVPActivity<CompleteOrgInfoV2Contract.Presenter>
        implements CompleteOrgInfoV2Contract.View, View.OnClickListener {

    /**data参数*/
    public static final String ARG_RBIID = "arg_ribid";

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
    TextView tvUser;
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

        int rbiid = getIntent().getIntExtra(ARG_RBIID, 0);

        mPresenter.loadOrgInfo(rbiid);

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


    @Override
    public void showOrgInfo(OrgInfoBean bean) {
        OrgInfoBean.InfoBean data = bean.info;

        if (!TextUtils.isEmpty(data.updatename) && !TextUtils.isEmpty(data.updatename)) {
            tvLastUpdate.setText("最后更新：" +
                    TimeUtils.InformationTime(data.updatetime) + "(" + data.updatename + ")");
            llUpdate.setVisibility(View.VISIBLE);
        }else {
            llUpdate.setVisibility(View.GONE);
        }

        tvOrgName.setText(data.oname);
        Glide.with(this).load(data.logosurl).into(imgLogo);
        tvPhone.setText(data.phone);
        tvArea.setText(LocationUtils.getFormedString(data.district));

        if (!TextUtils.isEmpty(data.gps)) {
            String[] gpss = data.gps.split(",");
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

        tvUser.setText(data.manager + " " + data.managerphone);
        tvDetailLocation.setText(data.address);

        if (!TextUtils.isEmpty(data.otype)) {
            String[] otypes = data.otype.split(",");
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

        if (!TextUtils.isEmpty(data.advertisingwallsurl)) {
            String[] pics = data.advertisingwallsurl.split(",");
            tvPicVideo.setText(pics.length + "图片，0视频");
        }else {
            tvPicVideo.setText("0图片，0视频");
        }

        String orgInfo = data.introduction;
        if (!TextUtils.isEmpty(orgInfo)) {
            if (orgInfo.length() > 10) {
                orgInfo = data.introduction.substring(0, 10) + "...";
            }
        }

        tvOrgDesc.setText(orgInfo);

        String classInfo = data.courseintroduction;
        if (!TextUtils.isEmpty(classInfo)) {
            if (classInfo.length() > 10) {
                classInfo = data.courseintroduction.substring(0, 10) + "...";
            }
        }
        tvClassDesc.setText(classInfo);

        String chargeInfo = data.tollintroduction;
        if (!TextUtils.isEmpty(chargeInfo)) {
            if (chargeInfo.length() > 10) {
                chargeInfo = data.tollintroduction.substring(0, 10) + "...";
            }
        }
        tvChargeDesc.setText(chargeInfo);

        try {
            JSONArray jsonArray = new JSONArray(data.tag);
            String tagStr = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                tagStr = tagStr + jsonArray.get(i).toString() + ",";
            }
            if (tagStr.length() > 0) {
                tagStr = tagStr.substring(0, tagStr.length() - 1);
            }
            tvTag.setText(tagStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLoadOrgInfoError(String errmsg) {

    }
}
