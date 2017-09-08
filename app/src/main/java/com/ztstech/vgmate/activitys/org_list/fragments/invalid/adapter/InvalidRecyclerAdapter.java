package com.ztstech.vgmate.activitys.org_list.fragments.invalid.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;

/**
 * Created by zhiyuan on 2017/9/8.
 */

public class InvalidRecyclerAdapter extends SimpleRecyclerAdapter<String> {

    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InvalidHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_invalid, parent, false), this);
    }
}
