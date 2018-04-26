package com.ztstech.vgmate.activitys.coop_progress;

import android.os.Bundle;
import android.widget.ImageView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dongdong on 2018/4/26.
 */

public class CoopProgressActivity extends BaseActivity {
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

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
    }
}
