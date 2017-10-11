package com.ztstech.vgmate.activitys.sell_chance.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.sell_chance.subview.SellChanceAllFragment;

/**
 * Created by zhiyuan on 2017/8/24.
 */

public class SellChancePagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    public SellChancePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SellChanceAllFragment();
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
