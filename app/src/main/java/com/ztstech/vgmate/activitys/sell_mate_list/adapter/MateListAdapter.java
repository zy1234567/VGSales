package com.ztstech.vgmate.activitys.sell_mate_list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.MatelistBean;

/**
 *
 * @author smm
 * @date 2017/11/23
 */

public class MateListAdapter extends SimpleRecyclerAdapter<MatelistBean.ListBean>{



    @Override
    public SimpleViewHolder<MatelistBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MateListViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_item_mate,parent,false));
    }
}
