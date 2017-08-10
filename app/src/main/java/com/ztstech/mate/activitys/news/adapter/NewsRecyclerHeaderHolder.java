package com.ztstech.mate.activitys.news.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.ztstech.mate.base.SimpleRecyclerAdapter;
import com.ztstech.mate.base.SimpleViewHolder;
import com.ztstech.mate.model.news.NewsModel;

/**
 * Created by zhiyuan on 2017/8/7.
 */

public class NewsRecyclerHeaderHolder extends SimpleViewHolder<NewsModel> {


    public NewsRecyclerHeaderHolder(View itemView, @Nullable SimpleRecyclerAdapter<NewsModel> adapter) {
        super(itemView, adapter);
    }

    @Override
    protected void refreshView(NewsModel data) {

    }
}
