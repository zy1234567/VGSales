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


//    public static final int TYPE_REFRESH_FOOTER = 1;

    @Override
    public SimpleViewHolder<GetOrgListItemsBean.ListBean> onCreateViewHolder(ViewGroup parent,
                                                                             int viewType) {
//        if (viewType == TYPE_REFRESH_FOOTER) {
//            return new SimpleLoadingFooterHolder<GetOrgListItemsBean.ListBean>()
//        }
        return new SimpleViewHolder<GetOrgListItemsBean.ListBean>(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orglist, parent, false), this);
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == getItemCount() - 1) {
//            //最后一个
//            return TYPE_REFRESH_FOOTER;
//        }
//        return super.getItemViewType(position);
//    }
}
