package com.ztstech.vgmate.activitys.comment.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ViewUtils;

/**
 * Created by zhiyuan on 2017/10/12.
 */

public class CommentRecyclerAdapter extends SimpleRecyclerAdapter<CommentBean.ListBean> {

    private CommentRecyclerCallback callback;
    private CommentHolder holder;

    private Context context;

    public OnCommentClickListener commentClickListener;
    public OnCommentClickListener replayCommentListener;
    public OnCommentLongClickListener deleteListener;

    @Override
    public SimpleViewHolder<CommentBean.ListBean> onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        context = parent.getContext();
        return new CommentHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false));
    }

    public void setCallback(CommentRecyclerCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder<CommentBean.ListBean> holder, int position) {
        CommentHolder commentHolder = (CommentHolder) holder;
        CommentBean.ListBean data = mListData.get(position);

        if (commentClickListener == null) {
            commentClickListener = new OnCommentClickListener();
        }
        if (replayCommentListener == null) {
            replayCommentListener = new OnCommentClickListener();
        }

        commentClickListener.isReplay = false;
        replayCommentListener.isReplay = true;
        commentHolder.body.setOnClickListener(replayCommentListener);

//        commentHolder.tvComment.setOnClickListener(commentClickListener);
//        commentHolder.tvReComment.setOnClickListener(replayCommentListener);
        commentClickListener.bean = data;
        replayCommentListener.bean = data;

        commentHolder.tvName.setText(data.name);
        commentHolder.tvComment.setText(data.comment);
        commentHolder.tvDate.setText(TimeUtils.informationTime(data.createtime));

        if (TextUtils.isEmpty(data.touid)) {
            //如果不是回复某人
            commentHolder.tvReComment.setVisibility(View.GONE);
        }else {
            String[] strs = new String[] {"@".concat(data.touname).concat("："), data.lastcomment};

            SpannableStringBuilder spannableStringBuilder =
                    ViewUtils.getDiffColorSpan(null, strs, commentHolder.colors);
            commentHolder.tvReComment.setText(spannableStringBuilder);
            commentHolder.tvReComment.setVisibility(View.VISIBLE);
        }
        
        if (UserRepository.getInstance().getUser().getUserBean().info.uid.equals(data.uid) ||
                UserRepository.getInstance().getUser().enableDeleteComment()) {
            if (deleteListener == null){
                deleteListener = new OnCommentLongClickListener();
            }
            commentHolder.body.setOnLongClickListener(deleteListener);
            deleteListener.bean = data;
        }


        Glide.with(context).load(data.picsurl).into(commentHolder.imgHeader);
    }

    public interface CommentRecyclerCallback {

        /**
         * 回复某疼
         * @param bean 数据
         * @param isReplay 回复的是否为该条评论的回复
         */
        void onReplay(CommentBean.ListBean bean, boolean isReplay);


        /**
         * 删除评论
         * @param bean 数据
         */
        void onDelete(CommentBean.ListBean bean);
    }

    public class OnCommentClickListener implements View.OnClickListener {

        public CommentBean.ListBean bean;

        public boolean isReplay;

        @Override
        public void onClick(View v) {
            if (callback != null) {
                callback.onReplay(bean, isReplay);
            }
        }
    }

    public class OnCommentLongClickListener implements View.OnLongClickListener {

        public CommentBean.ListBean bean;

        @Override
        public boolean onLongClick(View view) {
            if (callback != null) {
                callback.onDelete(bean);
            }
            return false;
        }
    }
}
