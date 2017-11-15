package com.ztstech.vgmate.activitys.org_follow.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.mate_approve.adapter.WaitApproveViewHolder;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;

/**
 *
 * @author smm
 * @date 2017/11/14
 */

public class OrgFollowListAdapter extends SimpleRecyclerAdapter<OrgFollowlistBean.ListBean> {

    private int index;

    public OrgFollowListAdapter(int index){
        this.index = index;
    }

    @Override
    public OrgFollowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrgFollowViewHolder((LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_org_follow, parent, false)),index);
    }

}
