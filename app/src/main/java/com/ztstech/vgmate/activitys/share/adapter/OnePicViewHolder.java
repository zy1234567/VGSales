package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.ImageView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;

import butterknife.BindView;

/**
 * Created by smm on 2017/11/27.
 * 单张纯图片
 */

public class OnePicViewHolder extends BaseShareViewHolder {

    @BindView(R.id.line)
    View line;
    @BindView(R.id.img_single)
    ImageView imgSingle;

    public OnePicViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
    }
}
