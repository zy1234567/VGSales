package com.ztstech.vgmate.activitys.main_fragment.subview.information.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.model.information.InformationModel;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class InformationRecyclerAdapter extends SimpleRecyclerAdapter<MainListBean.ListBean> {

    @Override
    public SimpleViewHolder<MainListBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InformationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_recycler, parent, false), this);
    }
}
