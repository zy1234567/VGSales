package com.ztstech.vgmate.activitys.get_chance.adapter;

import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.vgmate.utils.TimeUtils;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class GetChanceViewHolder extends SimpleViewHolder<CommunicationHistoryBean.ListBean> {


    @BindView(R.id.tv_year)
    TextView tvYear;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.tv_day)
    TextView tvDay;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_name)
    TextView tvName;

    public GetChanceViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(CommunicationHistoryBean.ListBean data) {
        super.refreshView(data);
        tvName.setText(data.uname);
        tvYear.setText(TimeUtils.getYear(data.createtime) + "年");
        tvInfo.setText(data.msg);
        tvDay.setText(TimeUtils.getMonth(data.createtime) + "-" +
                TimeUtils.getDay(data.createtime));
        tvTime.setText(TimeUtils.getHours(data.createtime) + ":" +
                TimeUtils.getMinutes(data.createtime));
    }

}
