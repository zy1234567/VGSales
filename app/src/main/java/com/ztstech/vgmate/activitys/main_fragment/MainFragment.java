package com.ztstech.vgmate.activitys.main_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.adapter.MainFragmentPagerAdapter;
import com.ztstech.vgmate.activitys.sell_chance.SellChanceActivity;
import com.ztstech.vgmate.data.beans.MainPageBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 资讯
 */
public class MainFragment extends MVPFragment<MainContract.Presenter> implements MainContract.View {

//    @BindView(R.id.xrv_news)
//    RecyclerView recyclerView;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onCreateViewFinish(@Nullable Bundle savedInstanceState) {
        super.onCreateViewFinish(savedInstanceState);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getChildFragmentManager()));

        mPresenter.loadData();

    }

    @OnClick(R.id.fl_sell_chance)
    public void onSellChanceClick(View v) {
        startActivity(new Intent(getActivity(), SellChanceActivity.class));
    }

    @Override
    public void loadError(String errorMessage) {

    }

    @Override
    public void setData(MainPageBean mainPageBean) {

    }
}
