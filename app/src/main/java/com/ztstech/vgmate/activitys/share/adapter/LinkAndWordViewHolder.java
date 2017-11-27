package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public LinkAndWordViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
    }
}
