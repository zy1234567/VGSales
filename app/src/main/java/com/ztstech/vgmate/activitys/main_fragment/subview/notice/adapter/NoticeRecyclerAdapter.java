package com.ztstech.vgmate.activitys.main_fragment.subview.notice.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.model.notice.NoticeModel;

/**
 * Created by zhiyuan on 2017/8/15.
 */

public class NoticeRecyclerAdapter extends SimpleRecyclerAdapter<NoticeModel> {

    @Override
    public SimpleViewHolder<NoticeModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder<NoticeModel>(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_notice, parent, false));
    }
}
