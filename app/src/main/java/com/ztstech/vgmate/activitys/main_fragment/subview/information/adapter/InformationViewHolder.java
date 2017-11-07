package com.ztstech.vgmate.activitys.main_fragment.subview.information.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.article_detail.ArticleDetailActivity;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.TimeUtils;

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

    /** 长按处理 */
    private LongClickListener longClickListener;

    /** 长按回调 */
    private DialogUtils.EditInfoCallBack callBack;

    public InformationViewHolder(View itemView,
                                 @Nullable SimpleRecyclerAdapter<MainListBean.ListBean> adapter,DialogUtils.EditInfoCallBack callBack) {
        super(itemView, adapter);
        this.callBack = callBack;
        clickListener = new ClickListener();
        longClickListener = new LongClickListener();
        itemView.setOnClickListener(clickListener);
        itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    protected void refreshView(MainListBean.ListBean data) {
        tvCommentCount.setText(String.valueOf(data.evacnt));
        tvTitle.setText(data.title);
        Glide.with(getContext()).load(data.picurl).into(ivTitle);
        tvDate.setText("最后更新：".concat(TimeUtils.InformationTime(data.createtime)));

        clickListener.bean = data;
        longClickListener.bean = data;
    }

    /**
     * 点击监听
     */
    private class ClickListener implements View.OnClickListener {

        MainListBean.ListBean bean;

        @Override
        public void onClick(View view) {
            Intent it = new Intent(getContext(), ArticleDetailActivity.class);
            it.putExtra(ArticleDetailActivity.ARG_SHOW_EDIT, false);
            it.putExtra(ArticleDetailActivity.ARG_LIST_DATA, new Gson().toJson(bean));
            getContext().startActivity(it);
        }
    }

    /**
     * 点击监听
     */
    private class LongClickListener implements View.OnLongClickListener {

        MainListBean.ListBean bean;

        @Override
        public boolean onLongClick(View view) {
            if (UserRepository.getInstance().getUser().enableDeleteComment() ||
                    UserRepository.getInstance().getUser().getUserBean().info.equals(bean.createuid)) {
                DialogUtils.showEditDialog(getContext(), bean, callBack);
            }
            return false;
        }
    }



}
