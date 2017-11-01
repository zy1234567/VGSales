package com.ztstech.vgmate.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public abstract class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<SimpleViewHolder<T>> {

    protected List<T> mListData;

    private SimpleRecyclerAdapter.OnItemClickListener<T> onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    void onItemClick(T data, int index) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(data, index);
        }
    }

    /**
     * 设置列表数据
     * @param data
     */
    public void setListData(List<T> data) {
        this.mListData = data;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder<T> holder, int position) {
        holder.refresh(mListData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }


    public interface OnItemClickListener<T> {
        void onItemClick(T item, int index);
    }
}