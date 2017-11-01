package com.ztstech.vgmate.activitys.sell_chance.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.sell_chance.subview.SellChanceAllFragment;
import com.ztstech.vgmate.data.api.SellChanceApi;

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
        if (position == 0) {
            //已抢到
            return SellChanceAllFragment.newInstance(SellChanceApi.STATUS_GET);
        }else if (position == 1) {
            //已注册
            return SellChanceAllFragment.newInstance(SellChanceApi.STATUS_SUCCEED);
        }else if (position == 2) {
            //全部
            return SellChanceAllFragment.newInstance(SellChanceApi.STATUS_ALL);
        }
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
