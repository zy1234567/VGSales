package com.ztstech.vgmate.activitys.org_list.fragments.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;

/**
 * Created by zhiyuan on 2017/9/20.
 */

public class OrglistRecyclerAdapter extends SimpleRecyclerAdapter<GetOrgListItemsBean.ListBean> {


    @Override
    public OrglistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrglistHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orglist, parent, false), this);
    }

}
