package com.ztstech.vgsales.activitys.news.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.ztstech.vgsales.base.SimpleRecyclerAdapter;
import com.ztstech.vgsales.base.SimpleViewHolder;
import com.ztstech.vgsales.model.news.NewsModel;

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
