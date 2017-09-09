package com.ztstech.vgmate.activitys.main_fragment.subview.information.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.model.information.InformationModel;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class InformationViewHolder extends SimpleViewHolder<MainListBean.ListBean> {


    @BindView(R.id.iv_item_recycler_news)
    ImageView ivTitle;

    @BindView(R.id.tv_item_recycler_notice_title)
    TextView tvTitle;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;

    /**点击处理*/
    private ClickListener clickListener;

    public InformationViewHolder(View itemView,
                                 @Nullable SimpleRecyclerAdapter<MainListBean.ListBean> adapter) {
        super(itemView, adapter);

        clickListener = new ClickListener();
        itemView.setOnClickListener(clickListener);
    }

    @Override
    protected void refreshView(MainListBean.ListBean data) {
        tvCommentCount.setText(String.valueOf(data.evacnt));
        tvTitle.setText(data.title);
        Glide.with(getContext()).load(data.picurl).into(ivTitle);
        tvDate.setText(data.createtime);

        clickListener.bean = data;
    }

    /**
     * 点击监听
     */
    private class ClickListener implements View.OnClickListener {

        MainListBean.ListBean bean;

        @Override
        public void onClick(View view) {
            getContext().startActivity(new Intent());
        }
    }
}
