package com.ztstech.vgmate.activitys.org_list.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.org_list.fragments.AuditFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.invalid.InvalidFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.pass.PassFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.v2.claimed.OrglistClaimedFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.v2.unclaim.OrglistUnclaimFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.v2.unsure.OrglistUnsureFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.v2.web.OrglistWebFragment;

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
