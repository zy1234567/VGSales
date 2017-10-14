package com.ztstech.vgmate.activitys.org_list.fragments.comfirmed.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.org_list.fragments.item.OrglistItemFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.unapprove.OrglistUnApproveFragment;

/**
 * Created by zhiyuan on 2017/10/14.
 * 机构列表已认领
 */

public class OrglistConfirmedPagerAdapter extends FragmentPagerAdapter {

    public OrglistConfirmedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return OrglistItemFragment.newInstance("03");
        }else {
            return new OrglistUnApproveFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
