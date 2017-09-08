package com.ztstech.vgmate.activitys.org_list.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.org_list.fragments.AuditFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.InvalidFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.PassFragment;

/**
 * Created by zhiyuan on 2017/9/8.
 */

public class OrgListPageAdapter extends FragmentPagerAdapter{

    public OrgListPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PassFragment();
        }else if (position == 1) {
            return new AuditFragment();
        }else if (position == 2) {
            return new InvalidFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return PassFragment.TITLE;
        }else if (position == 1) {
            return AuditFragment.TITLE;
        }else if (position == 2) {
            return InvalidFragment.TITLE;
        }
        return super.getPageTitle(position);
    }
}
