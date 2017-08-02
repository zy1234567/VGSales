package com.ztstech.vgsales.activitys.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgsales.manager.MainFragmentFactory;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final MainFragmentFactory fragmentFactory = new MainFragmentFactory();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return fragmentFactory.getCount();
    }
}
