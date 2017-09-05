package com.ztstech.vgmate.activitys.self_organization_detail.request_records.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;

/**
 * Created by zhiyuan on 2017/9/5.
 */

public class RequestRecordsRecyclerAdapter extends SimpleRecyclerAdapter<String> {

    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RequestRecordsRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_records, parent, false));
    }
}
