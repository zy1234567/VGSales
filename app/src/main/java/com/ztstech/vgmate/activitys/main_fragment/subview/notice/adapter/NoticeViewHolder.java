package com.ztstech.vgmate.activitys.main_fragment.subview.notice.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.article_detail.ArticleDetailActivity;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.TimeUtils;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public class NoticeViewHolder extends SimpleViewHolder<MainListBean.ListBean> {


    @BindView(R.id.iv_title)
    ImageView ivTitle;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_content)
    TextView tvContent;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;

    private ClickListener clickListener;

    /** 长按处理 */
    private LongClickListener longClickListener;

    /** 长按回调 */
    private DialogUtils.EditInfoCallBack callBack;

    public NoticeViewHolder(View itemView,DialogUtils.EditInfoCallBack callBack) {
        super(itemView);
        clickListener = new ClickListener();
        longClickListener = new LongClickListener();
        itemView.setOnLongClickListener(longClickListener);
        itemView.setOnClickListener(clickListener);
        this.callBack = callBack;
    }


    @Override
    protected void refreshView(MainListBean.ListBean data) {
        Glide.with(getContext()).load(data.picurl).into(ivTitle);
        tvTitle.setText(data.title);
        tvContent.setText(data.summary);
        tvTime.setText("最后更新：" + TimeUtils.informationTime(data.updatetime));
        tvCommentCount.setText(String.valueOf(data.evacnt));
        longClickListener.bean =data;
        clickListener.bean = data;
    }

    /**
     * 点击监听
     */
    private class ClickListener implements View.OnClickListener {

        MainListBean.ListBean bean;

        @Override
        public void onClick(View view) {
            Intent it = new Intent(getContext(), ArticleDetailActivity.class);
            it.putExtra(ArticleDetailActivity.ARG_SHOW_EDIT, true);
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
                    UserRepository.getInstance().getUser().getUserBean().info.equals(bean.createuid)){
                DialogUtils.showEditDialog(getContext(),bean,callBack);
            }
            return false;
        }
    }
}
