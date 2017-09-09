package com.ztstech.vgmate.activitys.main;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.info.EditInfoActivity;
import com.ztstech.vgmate.activitys.main.adapter.MainPagerAdapter;
import com.ztstech.vgmate.activitys.main.widget.BottomBar;
import com.ztstech.vgmate.weigets.TopBar;
import com.ztstech.vgmate.activitys.provide_chance.ProvideChanceActivity;

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

        topBar.getRightImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转提供销售机会
                startActivity(new Intent(MainActivity.this, ProvideChanceActivity.class));
            }
        });

        topBar.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditInfoActivity.class));
            }
        });


    }

    @Override
    public void onItemClick(int index) {
        vpMain.setCurrentItem(index);
    }
}
