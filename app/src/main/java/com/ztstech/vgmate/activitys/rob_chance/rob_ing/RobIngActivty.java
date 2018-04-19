package com.ztstech.vgmate.activitys.rob_chance.rob_ing;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dongdong on 2018/4/19.
 */

public class RobIngActivty extends BaseActivity {
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_add_type)
    TextView tvAddType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_org_name)
    TextView tvOrgName;
    @BindView(R.id.tv_class_types)
    TextView tvClassTypes;
    @BindView(R.id.tv_otype)
    TextView tvOtype;
    @BindView(R.id.tv_locations)
    TextView tvLocations;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_detial_locations)
    TextView tvDetialLocations;
    @BindView(R.id.tv_detial_location)
    TextView tvDetialLocation;
    @BindView(R.id.tv_phones)
    TextView tvPhones;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_gpss)
    TextView tvGpss;
    @BindView(R.id.tv_gps)
    TextView tvGps;
    @BindView(R.id.img_ico)
    ImageView imgIco;
    @BindView(R.id.tv_ico_gps)
    TextView tvIcoGps;
    @BindView(R.id.rl_ico_gps)
    RelativeLayout rlIcoGps;
    @BindView(R.id.tv_names)
    TextView tvNames;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_org_count)
    TextView tvOrgCount;
    @BindView(R.id.tv_postions)
    TextView tvPostions;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_tels)
    TextView tvTels;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_refuse)
    TextView tvRefuse;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_rob_ing;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
    }

    @OnClick({R.id.rl_ico_gps, R.id.tv_refuse, R.id.tv_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_ico_gps:
                break;
            case R.id.tv_refuse:
                break;
            case R.id.tv_pass:
                break;
        }
    }
}
