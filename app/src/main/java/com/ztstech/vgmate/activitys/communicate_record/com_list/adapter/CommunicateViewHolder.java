package com.ztstech.vgmate.activitys.communicate_record.com_list.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.CommunicateListBean;

import butterknife.BindView;

/**
 * Created by smm on 2018/1/11.
 */

public class CommunicateViewHolder extends SimpleViewHolder<CommunicateListBean.ListBean> {

    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.img_point)
    ImageView imgPoint;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.rl_point)
    RelativeLayout rlPoint;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_way)
    TextView tvWay;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_manage)
    TextView tvManage;
    @BindView(R.id.tv_next_visit)
    TextView tvNextVisit;
    @BindView(R.id.tv_com_record)
    TextView tvComRecord;
    @BindView(R.id.tv_follow_way)
    TextView tvFollowWay;
    @BindView(R.id.tv_location)
    TextView tvLocation;

    public CommunicateViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(CommunicateListBean.ListBean data) {
        super.refreshView(data);
    }
}
