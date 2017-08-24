package com.ztstech.vgmate.activitys.sell_chance.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.sell_chance.subview.all.SellChanceAllFragment;

/**
 * Created by zhiyuan on 2017/8/24.
 */

public class SellChancePagerAdapter extends FragmentPagerAdapter {

    private final String[] titles = new String[] {"全部", "可抢数", "已抢到", "已注册"};

    public SellChancePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SellChanceAllFragment();
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
