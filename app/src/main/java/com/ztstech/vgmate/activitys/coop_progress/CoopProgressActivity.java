package com.ztstech.vgmate.activitys.coop_progress;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.dto.CoopProgressData;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dongdong on 2018/4/26.
 */

public class CoopProgressActivity extends BaseActivity {
    //合作进度实体类
    public static final String ORG_COOP_BEAN = "org_coop_bean";
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.img_upload_coop_agree)
    ImageView imgUploadCoopAgree;
    @BindView(R.id.img_coop_partner)
    ImageView imgCoopPartner;
    @BindView(R.id.img_open_adver)
    ImageView imgOpenAdver;
    @BindView(R.id.img_poster)
    ImageView imgPoster;

    CoopProgressData coopProgressData;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        coopProgressData = new Gson().fromJson(getIntent().
                getStringExtra(ORG_COOP_BEAN), CoopProgressData.class);
    }
    //判断合作进度状态
    private void initView(){
        if (TextUtils.equals(coopProgressData.map.teamwork, Constants.PASS)){
            imgUploadCoopAgree.setImageResource(R.mipmap.wancheng_ico);
        }else{
            imgUploadCoopAgree.setImageResource(R.mipmap.weiwan_ico);
        }

        if (TextUtils.equals(coopProgressData.map.twstatus, Constants.PASS)){
            imgCoopPartner.setImageResource(R.mipmap.wancheng_ico);
        }else{
            imgCoopPartner.setImageResource(R.mipmap.weiwan_ico);
        }

        if (TextUtils.equals(coopProgressData.map.picture, Constants.PASS)){
            imgOpenAdver.setImageResource(R.mipmap.wancheng_ico);
        }else{
            imgOpenAdver.setImageResource(R.mipmap.weiwan_ico);
        }

        if (TextUtils.equals(coopProgressData.map.poster, Constants.PASS)){
            imgPoster.setImageResource(R.mipmap.wancheng_ico);
        }else{
            imgPoster.setImageResource(R.mipmap.weiwan_ico);
        }
    }

}
