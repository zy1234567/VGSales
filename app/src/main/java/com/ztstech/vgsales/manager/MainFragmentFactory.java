package com.ztstech.vgsales.manager;

import android.support.v4.app.Fragment;

import com.ztstech.vgsales.activitys.news.NewsFragment;
import com.ztstech.vgsales.activitys.share.ShareFragment;

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
        return 4;
    }

    private Fragment createFragment(int index) {
        if (index == 0) {
            return NewsFragment.newInstance();
        }else if (index == 1) {
            return ShareFragment.newInstance();
        }else if (index == 2) {
            return ShareFragment.newInstance();
        }else {
            return ShareFragment.newInstance();
        }
    }
}
