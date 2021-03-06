package com.ztstech.vgmate.activitys.main_fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.main_fragment.subview.information.InformationFragment;
import com.ztstech.vgmate.activitys.main_fragment.subview.notice.NoticeFragment;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final String[] TITLES = new String[] { "公告/教程","资讯/通知"};

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return NoticeFragment.newInstance();
        }else {
            return InformationFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
