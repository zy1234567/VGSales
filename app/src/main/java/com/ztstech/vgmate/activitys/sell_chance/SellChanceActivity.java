package com.ztstech.vgmate.activitys.sell_chance;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.sell_chance.widget.SellChancePagerAdapter;
import com.ztstech.vgmate.base.BaseActivity;

import butterknife.BindView;

/**
 * 销售机会界面
 */
public class SellChanceActivity extends MVPActivity<SellChanceContract.Presenter> implements
        SellChanceContract.View {

    private final String[] TITLES = new String[] {"已抢到", "已注册", "全部"};

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    private SellChancePagerAdapter pagerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sell_chance;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        tabLayout.setupWithViewPager(viewPager);

        pagerAdapter = new SellChancePagerAdapter(getSupportFragmentManager());
        pagerAdapter.setTitles(TITLES);

        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    protected SellChanceContract.Presenter initPresenter() {
        return new SellChancePresenter(this);
    }
}
