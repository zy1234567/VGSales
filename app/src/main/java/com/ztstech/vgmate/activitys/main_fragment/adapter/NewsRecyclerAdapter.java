//package com.ztstech.vgmate.activitys.news.adapter;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import com.ztstech.vgmate.R;
//import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
//import com.ztstech.vgmate.base.SimpleViewHolder;
//import com.ztstech.vgmate.model.news.NewsModel;
//
///**
// * Created by zhiyuan on 2017/8/7.
// */
//
//public class NewsRecyclerAdapter extends SimpleRecyclerAdapter<NewsModel> {
//
//    public static final int TYPE_HEADER = 0;
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @Override
//    public SimpleViewHolder<NewsModel> onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (TYPE_HEADER == viewType) {
//            return new NewsRecyclerHeaderHolder(LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_news_recycler_header,parent, false), this);
//        }else {
//            return new NewsRecyclerItemHolder(LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_news_recycler,parent, false), this);
//        }
//    }
//}
