package com.ztstech.vgmate.activitys.question.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.AnwserListBean;
import com.ztstech.vgmate.utils.TimeUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by smm on 2017/11/22.
 */

public class AnwserViewHolder extends SimpleViewHolder<AnwserListBean.ListBean> {

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_prise_num)
    TextView tvPriseNum;
    @BindView(R.id.img_prise)
    ImageView imgPrise;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;

    public AnwserViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(AnwserListBean.ListBean data) {
        super.refreshView(data);
        tvName.setText(data.uname);
        tvPriseNum.setText(String.valueOf(data.likedCnt));
        tvTime.setText(TimeUtils.InformationTime(data.ansCreatetime));
        Glide.with(getContext())
                .load(data.picsurl)
                .error(R.mipmap.ic_launcher)
                .into(imgHead);
        tvContent.setText(data.content);
    }
}
