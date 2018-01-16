package com.ztstech.vgmate.activitys.communicate_record.com_list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.GetComRecordBean;

/**
 * Created by smm on 2018/1/11.
 */

public class CommunicateListAdapter extends SimpleRecyclerAdapter<GetComRecordBean.ListBean> {

    @Override
    public CommunicateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommunicateViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_communicate_record,parent,false));
    }

}
