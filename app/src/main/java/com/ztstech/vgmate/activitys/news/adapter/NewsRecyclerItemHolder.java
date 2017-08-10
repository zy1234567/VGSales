package com.ztstech.vgmate.activitys.news.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.model.news.NewsModel;

/**
 * Created by zhiyuan on 2017/8/7.
 */

public class NewsRecyclerItemHolder extends SimpleViewHolder<NewsModel> {

    public NewsRecyclerItemHolder(View itemView, @Nullable SimpleRecyclerAdapter<NewsModel> adapter) {
        super(itemView, adapter);
    }

    @Override
    protected void refreshView(NewsModel data) {

    }
}
