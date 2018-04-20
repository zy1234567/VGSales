package com.ztstech.vgmate.activitys.add_certification;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_certification.adpter.AddVCertificationAdapter;
import com.ztstech.vgmate.weigets.NoScrollViewPager;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/20.
 */

public class RobAddVCertificationActivity extends MVPActivity<AddVContract.Presenter> implements AddVContract.View {
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_way)
    TextView tvWay;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rb_phone)
    RadioButton rbPhone;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rg_button)
    RadioGroup rgButton;
    @BindView(R.id.view_phone)
    View viewPhone;
    @BindView(R.id.view_home)
    View viewHome;
   @BindView(R.id.viewpager_way)
    NoScrollViewPager noScrollViewPager;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recoder_pass;
    }
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        rbPhone.setChecked(true);
        viewHome.setVisibility(View.INVISIBLE);
        noScrollViewPager.setCurrentItem(0);
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.rb_phone){
                    viewHome.setVisibility(View.INVISIBLE);
                    noScrollViewPager.setCurrentItem(0);
                    viewPhone.setVisibility(View.VISIBLE);
                }else {
                    viewPhone.setVisibility(View.INVISIBLE);
                    noScrollViewPager.setCurrentItem(1);
                    viewHome.setVisibility(View.VISIBLE);
                }
            }
        });
        noScrollViewPager.setAdapter(new AddVCertificationAdapter(getSupportFragmentManager()));
        noScrollViewPager.setNoScroll(true);
    }

    @Override
    public void onSubmitFinish(String msg) {

    }

    @Override
    protected AddVContract.Presenter initPresenter() {
        return new AddVPresenter(this);
    }

}
