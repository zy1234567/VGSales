package com.ztstech.vgmate.activitys.self_organization_detail;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.self_organization_detail.adapter.SelfOrganizationDetailPagerAdapter;

import butterknife.BindView;

public class SelfOrganizationDetailActivity extends
        MVPActivity<SelfOrganizationDetailContract.Presenter> implements
        SelfOrganizationDetailContract.View {


    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tableLayout;

    private SelfOrganizationDetailPagerAdapter pagerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_self_organization_detail;
    }

    @Override
    protected SelfOrganizationDetailContract.Presenter initPresenter() {
        return new SelfOrganizationDetailPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tableLayout.setupWithViewPager(viewPager);

        pagerAdapter = new SelfOrganizationDetailPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
    }
}
