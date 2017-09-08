package com.ztstech.vgmate.activitys.org_list.fragments.pass.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;

/**
 * Created by zhiyuan on 2017/9/8.
 */

public class PassRecyclerAdapter extends SimpleRecyclerAdapter<String> {

    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PassViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_org_list_pass, parent, false), this);
    }
}
