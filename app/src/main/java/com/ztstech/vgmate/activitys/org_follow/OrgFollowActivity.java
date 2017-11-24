package com.ztstech.vgmate.activitys.org_follow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_follow.adapter.FollowOrgFragmentPagerAdapter;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 *
 * @author smm
 * @date 2017/11/13
 */

public class OrgFollowActivity extends MVPActivity<OrgFollowNumContact.Presenter> implements OrgFollowNumContact.View{


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    FollowOrgFragmentPagerAdapter adapter;

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        mPresenter.loadFollowOrgNum();
    }

    @Override
    protected OrgFollowNumContact.Presenter initPresenter() {
        return new OrgFollowNumPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_follow;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tablayout.setupWithViewPager(viewpager);
        adapter = new FollowOrgFragmentPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }

    @Override
    public void onGetFollowNumSucced(OrgFollowNumBean.InfoBean bean) {
        String[] titles = {"已确认".concat("(").concat(bean.confirmNum).concat(")"),
                "已认领".concat("(").concat(bean.claimNum).concat(")"),
                "管理端".concat("(").concat(bean.webNum).concat(")")};
        adapter.setTitles(titles);
        adapter.notifyDataSetChanged();
    }
}
