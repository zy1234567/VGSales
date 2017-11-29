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
 * 纯文字布局
 */

public class WordViewHolder extends BaseShareViewHolder {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_quanwen)
    TextView tvQuanwen;

    public WordViewHolder(View itemView,ClickCallback callback) {
        super(itemView,callback);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
        tvContent.setText(data.content);
        initQuanWen(data,tvQuanwen, tvContent,getPosition(), data.content);
    }
}
