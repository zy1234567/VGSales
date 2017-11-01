package com.ztstech.vgmate.activitys.sell_chance.subview.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.SellChanceBean;

/**
 * Created by zhiyuan on 2017/8/24.
 */

public class SellChanceAllRecyclerAdapter extends SimpleRecyclerAdapter<SellChanceBean.ListBean> {

    @Override
    public SimpleViewHolder<SellChanceBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SellChanceAllHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_sell_chance_all, parent, false), this);
    }

    


}
