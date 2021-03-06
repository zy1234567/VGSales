package com.ztstech.vgmate.activitys.question.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.AnwserListBean;

/**
 *
 * @author smm
 * @date 2017/11/22
 */

public class AnwsetListAdapter extends SimpleRecyclerAdapter<AnwserListBean.ListBean>{

    AnwserViewHolder.ClickCallBack longClickCallBack;

    public AnwsetListAdapter(AnwserViewHolder.ClickCallBack longClickCallBack){
        this.longClickCallBack = longClickCallBack;
    }

    @Override
    public SimpleViewHolder<AnwserListBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnwserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_anwser,parent,false),longClickCallBack);
    }
}
