package com.ztstech.vgmate.activitys.org_detail.dialog.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.RepeatOrgBean;

/**
 * Created by zhiyuan on 2017/9/25.
 */

public class OrgAuditDialogRecyclerAdapter extends SimpleRecyclerAdapter<RepeatOrgBean.ListBean> {

    @Override
    public SimpleViewHolder<RepeatOrgBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrgAuditDialogHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_org_audit, parent, false));
    }
}
