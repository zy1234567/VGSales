package com.ztstech.vgmate.activitys.sell_chance.subview.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.SellChanceBean;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.TimeUtils;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/8/24.
 */

public class SellChanceAllHolder extends SimpleViewHolder<SellChanceBean.ListBean> {


    @BindView(R.id.tv_section_title)
    TextView tvSectionTitle;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_get)
    TextView tvGet;

    @BindView(R.id.img)
    ImageView img;

    @BindView(R.id.tv_tag)
    TextView tvTag;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_location)
    TextView tvLocation;

    public SellChanceAllHolder(View itemView,
                               @Nullable SimpleRecyclerAdapter<SellChanceBean.ListBean> adapter) {
        super(itemView, adapter);
    }

    @Override
    protected void refreshView(SellChanceBean.ListBean data) {
        super.refreshView(data);
        tvTitle.setText(data.oname);
        tvLocation.setText(LocationUtils.getFormedString(data.district));
        tvTime.setText(TimeUtils.informationTime(data.createtime));
        tvSectionTitle.setText(data.salename);
        Glide.with(getContext()).load(data.picurl).into(img);
    }
}
