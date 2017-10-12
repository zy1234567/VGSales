package com.ztstech.vgmate.activitys.comment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.CommentBean;

/**
 * Created by zhiyuan on 2017/10/12.
 */

public class CommentRecyclerAdapter extends SimpleRecyclerAdapter<CommentBean.ListBean> {
    @Override
    public SimpleViewHolder<CommentBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false));
    }
}
