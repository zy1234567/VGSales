package com.ztstech.vgmate.activitys.org_follow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_certification.RobAddVCertificationActivity;
import com.ztstech.vgmate.activitys.org_follow.adapter.FollowOrgFragmentPagerAdapter;
import com.ztstech.vgmate.activitys.rob_chance.RobChanceActivity;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;
import com.ztstech.vgmate.data.events.ApproveOrgEvent;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author smm
 * @date 2017/11/13
 * 客户跟进界面
 */

public class OrgFollowActivity extends MVPActivity<OrgFollowContact.Presenter> implements OrgFollowContact.View {

    public static final String KEY_UID = "uid";

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rl_rob_chance)
    RelativeLayout rlRobChance;

    /**
     * 要显示对应人的界面uid
     */
    private String uid;

    private FollowOrgFragmentPagerAdapter adapter;

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        uid = getIntent().getStringExtra(KEY_UID);
        // 右上角的待审批按钮情况
        if (TextUtils.equals(uid, UserRepository.getInstance().getUser().getUserBean().info.uid)) {
//            topBar.getRightImage().setVisibility(View.VISIBLE);
//        }else {
            topBar.getRightImage().setVisibility(View.GONE);
        }
        tablayout.setupWithViewPager(viewpager);
        adapter = new FollowOrgFragmentPagerAdapter(getSupportFragmentManager(), uid);
        viewpager.setAdapter(adapter);
//        topBar.getRightImage().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 点击右上角跳转到机构反馈列表界面
//                startActivity(new Intent(OrgFollowActivity.this,OrgFeedBackActivity.class));
//            }
//        });
        mPresenter.loadFollowOrgNum(uid);
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
    public void onGetFollowNumSucced(OrgFollowNumBean bean) {
        if (bean == null || bean.info == null) {
            return;
        }
        String[] titles = {"我开拓的".concat(" ").concat(String.valueOf(bean.info.appointNum)),
                "商家介绍".concat(" ").concat(String.valueOf(bean.info.introingNum)),
                "机会抢单".concat(" ").concat(String.valueOf(bean.info.rushNum ))};
        adapter.setTitles(titles);
        adapter.notifyDataSetChanged();
        topBar.setTitle("客户跟进".concat("(").concat
                (String.valueOf(bean.info.confirmNum + bean.info.claimNum + bean.info.webNum)).concat(")"));
        if (bean.info.auditNum > 0 &&
                TextUtils.equals(uid, UserRepository.getInstance().getUser().getUserBean().info.uid)) {
            topBar.getRightRedNum().setVisibility(View.VISIBLE);
            topBar.getRightRedNum().setText(String.valueOf(bean.info.auditNum > 99 ? 99 : bean.info.auditNum));
        } else {
            topBar.getRightRedNum().setVisibility(View.GONE);
        }
    }

    @Subscribe
    private void onRefersh(ApproveOrgEvent event) {
        mPresenter.loadFollowOrgNum(uid);
    }

    @OnClick(R.id.rl_rob_chance)
    public void onClick() {
        Intent intent = new Intent(this, RobChanceActivity.class);
        startActivity(intent);
    }
}
