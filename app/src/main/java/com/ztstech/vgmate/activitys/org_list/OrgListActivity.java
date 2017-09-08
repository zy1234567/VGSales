package com.ztstech.vgmate.activitys.org_list;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_list.adapter.OrgListPageAdapter;
import com.ztstech.vgmate.activitys.self_organization_detail.adapter.SelfOrganizationDetailPagerAdapter;

import butterknife.BindView;

/**
 * 组织名录
 */
public class OrgListActivity extends MVPActivity<OrgListContract.Presenter> implements
        OrgListContract.View {


    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tableLayout;

    private OrgListPageAdapter pagerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_list;
    }

    @Override
    protected OrgListContract.Presenter initPresenter() {
        return new OrglistPresenter(this);
    }


    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tableLayout.setupWithViewPager(viewPager);

        pagerAdapter = new OrgListPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
    }
}
