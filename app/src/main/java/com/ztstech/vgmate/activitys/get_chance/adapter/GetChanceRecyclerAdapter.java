package com.ztstech.vgmate.activitys.get_chance.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class GetChanceRecyclerAdapter extends SimpleRecyclerAdapter<String> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 2;


    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = null;
        if (TYPE_HEADER == viewType) {
            v = inflater.inflate(R.layout.item_get_chance_header, parent, false);
        }else if (TYPE_FOOTER == viewType) {
            v = inflater.inflate(R.layout.item_get_chance_footer, parent, false);
        }else if (TYPE_ITEM == viewType) {
            v = inflater.inflate(R.layout.item_get_chance, parent, false);
        }

        return new GetChanceViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }else if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
        }
    }
}
