package com.ztstech.vgmate.activitys.self_organization.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;

/**
 * Created by zhiyuan on 2017/8/26.
 */

public class SelfOrganizationRecyclerAdapter extends SimpleRecyclerAdapter<String> {

    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelfOrganizationRecyclerHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_self_organization, parent, false), this);
    }
}
