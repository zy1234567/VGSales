package com.ztstech.vgmate.activitys.question.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztstech.vgmate.activitys.question.question_list.QuestionListFragment;

/**
 * Created by smm on 2017/11/16.
 */

public class QuestionViewPgerAdapter extends FragmentPagerAdapter {

    public static final String[] TITLES = new String[] {"问答列表", "我的提问"};

    public QuestionViewPgerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            QuestionListFragment fragment = QuestionListFragment.newInstance();
            fragment.setShowType(false);
            return fragment;
        }else {
            QuestionListFragment fragment = QuestionListFragment.newInstance();
            fragment.setShowType(true);
            return fragment;
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
}
