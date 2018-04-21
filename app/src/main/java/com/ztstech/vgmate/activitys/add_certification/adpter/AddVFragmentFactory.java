package com.ztstech.vgmate.activitys.add_certification.adpter;

import android.support.v4.app.Fragment;

import com.ztstech.vgmate.activitys.add_certification.add_home_fragment.HomeFragment;
import com.ztstech.vgmate.activitys.add_certification.add_phone_fragment.PhoneFragment;
import com.ztstech.vgmate.activitys.main_fragment.MainFragment;
import com.ztstech.vgmate.activitys.question.QuestionFragment;
import com.ztstech.vgmate.activitys.share.ShareFragment;

/**
 * Created by Administrator on 2018/4/20.
 */

public class AddVFragmentFactory {
    private Fragment[] fragments = new Fragment[2];


    public Fragment getFragment(int index) {
        if (fragments[index] == null) {
            fragments[index] = createFragment(index);
        }
        return fragments[index];
    }

    public int getCount() {
        return 2;
    }

    private Fragment createFragment(int index) {
        if (index == 0) {
            return PhoneFragment.newInstance();
        }else {
            return HomeFragment.newInstance();
        }
    }
}
