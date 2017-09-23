package com.ztstech.vgmate.activitys.org_list.fragments.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;

/**
 * Created by zhiyuan on 2017/9/20.
 */

public class OrglistRecyclerAdapter extends SimpleRecyclerAdapter<GetOrgListItemsBean.ListBean> {
    @Override
    public SimpleViewHolder<GetOrgListItemsBean.ListBean> onCreateViewHolder(ViewGroup parent,
                                                                             int viewType) {
        return new SimpleViewHolder<GetOrgListItemsBean.ListBean>(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orglist, parent, false), this);
    }
}
