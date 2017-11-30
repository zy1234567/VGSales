package com.ztstech.vgmate.activitys.org_follow.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.vgmate.activitys.org_follow.OrgFollowListFragment;

/**
 *
 * @author smm
 * @date 2017/11/13
 */

public class FollowOrgFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] TITLES;

    private String uid;

    public FollowOrgFragmentPagerAdapter(FragmentManager fm,String uid) {
        super(fm);
        TITLES = new String[] {"已确认", "已认领","管理端"};
        this.uid = uid;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            OrgFollowListFragment followListFragment = OrgFollowListFragment.newInstance();
            followListFragment.setIndexStatus(GetOrgFollow.STATUS_INDEX_CONCERN);
            followListFragment.setUid(uid);
            return followListFragment;
        }else if (position == 1){
            OrgFollowListFragment followListFragment = OrgFollowListFragment.newInstance();
            followListFragment.setIndexStatus(GetOrgFollow.STATUS_INDEX_CLAIM);
            followListFragment.setUid(uid);
            return followListFragment;
        }else {
            OrgFollowListFragment followListFragment = OrgFollowListFragment.newInstance();
            followListFragment.setIndexStatus(GetOrgFollow.STATUS_INDEX_MANAGER);
            followListFragment.setUid(uid);
            return followListFragment;
        }
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    public void setTitles(String[] TITLES){
        this.TITLES = TITLES;
    }

}
