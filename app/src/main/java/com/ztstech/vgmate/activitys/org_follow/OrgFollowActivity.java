package com.ztstech.vgmate.activitys.org_follow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_follow.adapter.FollowOrgFragmentPagerAdapter;
import com.ztstech.vgmate.activitys.org_follow.feedback.OrgFeedBackActivity;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;
import com.ztstech.vgmate.data.events.ApproveOrgEvent;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 *
 * @author smm
 * @date 2017/11/13
 * 客户跟进界面
 */

public class OrgFollowActivity extends MVPActivity<OrgFollowContact.Presenter> implements OrgFollowContact.View{

    public static final String KEY_UID = "uid";

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private FollowOrgFragmentPagerAdapter adapter;

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        mPresenter.loadFollowOrgNum();
    }

    @Override
    protected OrgFollowContact.Presenter initPresenter() {
        return new OrgFollowPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_follow;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tablayout.setupWithViewPager(viewpager);
        adapter = new FollowOrgFragmentPagerAdapter(getSupportFragmentManager(),getIntent().getStringExtra(KEY_UID));
        viewpager.setAdapter(adapter);
        topBar.getRightImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击右上角跳转到机构反馈列表界面
                startActivity(new Intent(OrgFollowActivity.this,OrgFeedBackActivity.class));
            }
        });
    }

    @Override
    public void onGetFollowNumSucced(OrgFollowNumBean bean) {
        String[] titles = {"已确认".concat(" ").concat(String.valueOf(bean.info.confirmNum)),
                "已认领".concat(" ").concat(String.valueOf(bean.info.claimNum)),
                "管理端".concat(" ").concat(String.valueOf(bean.info.webNum))};
        adapter.setTitles(titles);
        adapter.notifyDataSetChanged();
        topBar.setTitle("客户跟进".concat("(").concat
                (String.valueOf(bean.info.confirmNum + bean.info.claimNum + bean.info.webNum)).concat(")"));
        if (bean.info.auditNum > 0){
            topBar.getRightRedNum().setVisibility(View.VISIBLE);
            topBar.getRightRedNum().setText(String.valueOf(bean.info.auditNum > 99 ? 99 : bean.info.auditNum));
        }else {
            topBar.getRightRedNum().setVisibility(View.GONE);
        }
    }

    @Subscribe
    private void onRefersh(ApproveOrgEvent event){
        mPresenter.loadFollowOrgNum();
    }

}
