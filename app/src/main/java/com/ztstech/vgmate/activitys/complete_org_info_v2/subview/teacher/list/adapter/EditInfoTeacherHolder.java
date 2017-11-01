package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.list.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.TeacherListBean;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/10/19.
 */

public class EditInfoTeacherHolder extends SimpleViewHolder<TeacherListBean.ListBean> {


    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_class)
    TextView tvClass;

    @BindView(R.id.img)
    ImageView img;

    public EditInfoTeacherHolder(View itemView,
                                 @Nullable SimpleRecyclerAdapter<TeacherListBean.ListBean> adapter) {
        super(itemView, adapter);
    }

    @Override
    protected void refreshView(TeacherListBean.ListBean data) {
        super.refreshView(data);
        Glide.with(getContext()).load(data.logosurl).into(img);
        tvName.setText(data.name);
        tvClass.setText(data.position);
    }
}
