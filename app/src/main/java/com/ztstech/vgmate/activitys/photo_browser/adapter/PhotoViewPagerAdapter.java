package com.ztstech.vgmate.activitys.photo_browser.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by smm on 2017/11/17.
 */

public class PhotoViewPagerAdapter extends PagerAdapter {

    private List<View> views;


    public PhotoViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
