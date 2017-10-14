package com.ztstech.vgmate.activitys.org_list.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.org_list.fragments.comfirmed.OrglistConfirmedFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.item.OrglistItemFragment;

/**
 * Created by zhiyuan on 2017/9/8.
 */

public class OrgListPageAdapter extends FragmentPagerAdapter{

    private String[] titles;


    public OrgListPageAdapter(FragmentManager fm) {
        super(fm);
    }


    /**
     * 设置标题
     * @param titles
     */
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return OrglistItemFragment.newInstance("02");
        }else if (position == 1) {
            return OrglistItemFragment.newInstance("00");
        }else if (position == 2) {
            return new OrglistConfirmedFragment();
        }else if (position == 3) {
            return OrglistItemFragment.newInstance("04");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null) {
            return titles[position];
        }
        return super.getPageTitle(position);
    }
}
