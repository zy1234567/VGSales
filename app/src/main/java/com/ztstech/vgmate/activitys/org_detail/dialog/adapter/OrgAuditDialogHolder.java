package com.ztstech.vgmate.activitys.org_detail.dialog.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.RepeatOrgBean;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/9/25.
 */

public class OrgAuditDialogHolder extends SimpleViewHolder<RepeatOrgBean.ListBean> {

    @BindView(R.id.img)
    ImageView imgSelection;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_tag)
    TextView tvTag;


    public OrgAuditDialogHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(RepeatOrgBean.ListBean data) {
        super.refreshView(data);
        tvTitle.setText(data.rbioname);
        tvId.setText(String.valueOf(data.rbiid));
    }
}
