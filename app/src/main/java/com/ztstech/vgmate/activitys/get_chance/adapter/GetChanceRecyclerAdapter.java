package com.ztstech.vgmate.activitys.get_chance.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class GetChanceRecyclerAdapter extends SimpleRecyclerAdapter<String> {
    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GetChanceViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_get_chance, parent, false));
    }


}
