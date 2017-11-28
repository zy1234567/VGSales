package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;

import butterknife.BindView;

/**
 * Created by smm on 2017/11/27.
 * 连接带文字
 */

public class LinkAndWordViewHolder extends BaseShareViewHolder {

    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_quanwen)
    TextView tvQuanwen;
    @BindView(R.id.img_link)
    ImageView imgLink;
    @BindView(R.id.tv_link_title)
    TextView tvLinkTitle;
    @BindView(R.id.layout_link)
    RelativeLayout layoutLink;

    public LinkAndWordViewHolder(View itemView,ClickCallback callback) {
        super(itemView,callback);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
        tvContent.setText(data.content);
//        holder.tvQuanwen.setOnClickListener(new MyClickListener(position, holder));
        tvLinkTitle.setText(data.title);
//        Glide.with(getContext()).load(data.)
    }
}
