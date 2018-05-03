package com.ztstech.vgmate.activitys.org_follow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.tv_rob_num)
    TextView tvRobNum;
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
        topBar.getRightRedNum().setVisibility(View.GONE);
        tablayout.setupWithViewPager(viewpager);
        adapter = new FollowOrgFragmentPagerAdapter(getSupportFragmentManager(), uid);
        viewpager.setAdapter(adapter);
        mPresenter.loadFollowOrgNum(uid);
    }
    private void table(String [] str,int[] num){
        for (int i =0 ; i < adapter.getCount();i++){
            TabLayout.Tab tab = tablayout.getTabAt(i);
            tab.setCustomView(R.layout.table_layout_item);
            TextView tabtext = tab.getCustomView().findViewById(R.id.tab_text);
            TextView tvTabNum = tab.getCustomView().findViewById(R.id.tv_tab_num);
            tabtext.setText(str[i]);
            tvTabNum.setText(String.valueOf(num[i]));
            if (num[i] == 0){
                tvTabNum.setVisibility(View.GONE);
            }
            if (i == 0){
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
                tabtext.setTextColor(this.getResources().getColor(R.color.color_001));
            }
        }
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
                TextView tabtext = tab.getCustomView().findViewById(R.id.tab_text);
                tabtext.setTextColor(getResources().getColor(R.color.color_001));
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.getCustomView().findViewById(R.id.tab_text).setSelected(false);
                TextView tabtext = tab.getCustomView().findViewById(R.id.tab_text);
                tabtext.setTextColor(getResources().getColor(R.color.color_100));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
        String[] titles = {"我开拓的".concat("(").concat(String.valueOf(bean.info.appointNum).concat(")")),
                "商家介绍".concat("(").concat(String.valueOf(bean.info.introNum).concat(")")),
                "机会抢单".concat("(").concat(String.valueOf(bean.info.rushNum).concat(")"))};
//        adapter.setTitles(titles);
        int[] num = {bean.info.appointingNum,bean.info.introingNum ,0};
        table(titles,num);
//        adapter.notifyDataSetChanged();
        topBar.setTitle("客户跟进".concat("(").concat
                (String.valueOf(bean.info.appointNum + bean.info.introNum + bean.info.rushNum
                +bean.info.rushableNum + bean.info.appointingNum + bean.info.introingNum)).concat(")"));
        if (bean.info.rushableNum != 0){
            tvRobNum.setText( String.valueOf(bean.info.rushableNum > 99 ? 99 : bean.info.rushableNum));
            tvRobNum.setVisibility(View.VISIBLE);
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
