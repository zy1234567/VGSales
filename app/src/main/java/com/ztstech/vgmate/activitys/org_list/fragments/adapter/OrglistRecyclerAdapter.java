package com.ztstech.vgmate.activitys.org_list.fragments.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;

/**
 * Created by zhiyuan on 2017/9/20.
 */

public class OrglistRecyclerAdapter extends SimpleRecyclerAdapter<String> {
    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder<String>(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orglist, parent, false));
    }
}
