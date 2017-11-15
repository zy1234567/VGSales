package com.ztstech.vgmate.manager;

import android.support.v4.app.Fragment;

import com.ztstech.vgmate.activitys.main_fragment.MainFragment;
import com.ztstech.vgmate.activitys.share.ShareFragment;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class MainFragmentFactory {

    private Fragment[] fragments = new Fragment[4];


    public Fragment getFragment(int index) {
        if (fragments[index] == null) {
            fragments[index] = createFragment(index);
        }
        return fragments[index];
    }

    public int getCount() {
        return 3;
    }

    private Fragment createFragment(int index) {
        if (index == 0) {
            return MainFragment.newInstance();
        }else if (index == 1) {
            return ShareFragment.newInstance();
        }else if (index == 2) {
            return ShareFragment.newInstance();
        }else {
            return ShareFragment.newInstance();
        }
    }
}
