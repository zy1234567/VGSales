package com.ztstech.vgmate.activitys.main_fragment.subview.information.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.model.information.InformationModel;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class InformationViewHolder extends SimpleViewHolder<InformationModel> {

    public InformationViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<InformationModel> adapter) {
        super(itemView, adapter);
    }

    @Override
    protected void refreshView(InformationModel data) {

    }
}
