package com.ztstech.vgmate.activitys.org_list.fragments.unapprove.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.org_list.fragments.item.adapter.OrglistHolder;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.OrglistUnApproveBean;

/**
 * Created by zhiyuan on 2017/10/14.
 */

public class UnApproveRecyclerAdapter extends SimpleRecyclerAdapter<OrglistUnApproveBean.ListBean> {

    @Override
    public SimpleViewHolder<OrglistUnApproveBean.ListBean> onCreateViewHolder(ViewGroup parent,
                                                                              int viewType) {
        return new UnApproveHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_orglist, parent, false), this);
    }


}
