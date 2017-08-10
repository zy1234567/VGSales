package com.ztstech.mate.activitys.main;


import android.support.v4.view.ViewPager;

import com.ztstech.mate.R;
import com.ztstech.mate.activitys.MVPActivity;
import com.ztstech.mate.activitys.main.adapter.MainPagerAdapter;
import com.ztstech.mate.activitys.main.widget.BottomBar;
import com.ztstech.mate.activitys.main.widget.TopBar;

import butterknife.BindView;

/**
 * 主界面
 */
public class MainActivity extends MVPActivity<MainContract.Presenter> implements MainContract.View,
        BottomBar.OnTabItemClickListener{

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.vp_main)
    ViewPager vpMain;



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }


    @Override
    protected void onViewBindFinish() {
        bottomBar.setOnTabItemClickListener(this);

        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onItemClick(int index) {
        vpMain.setCurrentItem(index);
    }
}
