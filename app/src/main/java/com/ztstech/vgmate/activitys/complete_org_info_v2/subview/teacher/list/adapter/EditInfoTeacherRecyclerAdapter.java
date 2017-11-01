package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.TeacherListBean;

/**
 * Created by zhiyuan on 2017/10/19.
 */

public class EditInfoTeacherRecyclerAdapter extends SimpleRecyclerAdapter<TeacherListBean.ListBean> {




    @Override
    public SimpleViewHolder<TeacherListBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditInfoTeacherHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_teachers, parent, false), this);
    }
}
