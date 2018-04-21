package com.ztstech.vgmate.activitys.add_certification.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2018/4/20.
 */

public class AddVCertificationAdapter extends FragmentPagerAdapter {

    private final AddVFragmentFactory fragmentFactory = new AddVFragmentFactory();

    public AddVCertificationAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentFactory.getFragment(position);
    }

    @Override
    public int getCount() {
        return fragmentFactory.getCount();
    }
}
