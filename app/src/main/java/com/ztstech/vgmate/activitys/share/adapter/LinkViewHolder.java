package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;

import butterknife.BindView;

/**
 * Created by smm on 2017/11/27.
 * 纯链接
 */

public class LinkViewHolder extends BaseShareViewHolder {

    @BindView(R.id.line)
    View line;
    @BindView(R.id.img_link)
    ImageView imgLink;
    @BindView(R.id.tv_link_title)
    TextView tvLinkTitle;
    @BindView(R.id.layout_link)
    RelativeLayout layoutLink;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;

    public LinkViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
    }
}
