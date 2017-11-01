package com.ztstech.vgmate.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public class SimpleViewHolder<T> extends RecyclerView.ViewHolder{

    private ItemViewClickListener itemViewClickListener;
    private SimpleRecyclerAdapter<T> adapter;
    private View itemView;

    public SimpleViewHolder(View itemView) {
        this(itemView, null);
    }

    /**
     * 传入adapter 设置onItemClickListener才会有效
     * @param itemView
     * @param adapter
     */
    public SimpleViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<T> adapter) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        if (adapter != null) {
            itemViewClickListener = new ItemViewClickListener();
            itemView.setOnClickListener(itemViewClickListener);
            this.adapter = adapter;
        }
    }

    public final void refresh(T data, int position) {
        if (itemViewClickListener != null) {
            itemViewClickListener.data = data;
            itemViewClickListener.position = position;
        }
        refreshView(data);
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    protected void refreshView(T data) {};

    private class ItemViewClickListener implements View.OnClickListener {

        T data;

        int position;

        @Override
        public void onClick(View v) {
            adapter.onItemClick(data, position);
        }
    }
}