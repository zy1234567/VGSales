package com.ztstech.vgmate.activitys.self_organization_detail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.self_organization_detail.organization_detail.OrganizationDetailFragment;
import com.ztstech.vgmate.activitys.self_organization_detail.request_records.RequestRecordsFragment;

/**
 * Created by zhiyuan on 2017/8/26.
 */

public class SelfOrganizationDetailPagerAdapter extends FragmentPagerAdapter {

    public SelfOrganizationDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new OrganizationDetailFragment();
        }else {
            return new RequestRecordsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "机构资料";
        }else {
            return "申请记录";
        }
    }
}
