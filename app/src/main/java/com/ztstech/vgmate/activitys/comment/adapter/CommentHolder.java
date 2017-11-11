package com.ztstech.vgmate.activitys.comment.adapter;

import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ViewUtils;

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

    @BindView(R.id.body)
    RelativeLayout body;



    public int[] colors;

    private CommentRecyclerAdapter.CommentRecyclerCallback callback;

    public CommentHolder(View itemView) {
        super(itemView);
        colors = new int[] {ContextCompat.getColor(getContext(), R.color.color_004),
                ContextCompat.getColor(getContext(), R.color.color_100)};

    }

    public void setCallback(CommentRecyclerAdapter.CommentRecyclerCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void refreshView(CommentBean.ListBean data) {
        super.refreshView(data);

    }


}
