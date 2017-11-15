package com.ztstech.vgmate.activitys.org_follow;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_follow.adapter.FollowOrgFragmentPagerAdapter;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 *
 * @author smm
 * @date 2017/11/13
 */

public class OrgFollowActivity extends MVPActivity {


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_follow;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tablayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(new FollowOrgFragmentPagerAdapter(getSupportFragmentManager()));
    }

}
