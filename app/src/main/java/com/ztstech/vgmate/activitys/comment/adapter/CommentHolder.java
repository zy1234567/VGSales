package com.ztstech.vgmate.activitys.comment.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.CommentBean;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/10/12.
 */

public class CommentHolder extends SimpleViewHolder<CommentBean.ListBean> {

    @BindView(R.id.img_title)
    ImageView imgHeader;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_comment)
    TextView tvComment;

    @BindView(R.id.tv_re_comment)
    TextView tvReComment;

    @BindView(R.id.tv_date)
    TextView tvDate;

    public CommentHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(CommentBean.ListBean data) {
        super.refreshView(data);

        tvName.setText(data.name);
        tvComment.setText(data.comment);
        tvDate.setText(data.createtime);


        Glide.with(getContext()).load(data.picsurl).into(imgHeader);


    }
}
