package com.ztstech.vgmate.activitys.org_list.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.org_list.fragments.claimed.OrglistClaimedFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.unclaim.OrglistUnclaimFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.unsure.OrglistUnsureFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.web.OrglistWebFragment;

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
            return new OrglistUnsureFragment();
        }else if (position == 1) {
            return new OrglistUnclaimFragment();
        }else if (position == 2) {
            return new OrglistClaimedFragment();
        }else if (position == 3) {
            return new OrglistWebFragment();
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
