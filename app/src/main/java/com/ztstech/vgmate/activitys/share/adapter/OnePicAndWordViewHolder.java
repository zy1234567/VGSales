package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;

/**
 * Created by smm on 2017/11/27.
 * 单张图片加文字
 */

public class OnePicAndWordViewHolder extends BaseShareViewHolder {

    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_quanwen)
    TextView tvQuanwen;
    @BindView(R.id.img_single)
    ImageView imgSingle;

    public OnePicAndWordViewHolder(View itemView,ClickCallback callback) {
        super(itemView,callback);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
        tvContent.setText(data.content);
        String url = ViewUtils.setSingleImageSize(getContext(),data.contentpicsurl,imgSingle);
        Glide.with(getContext()).load(url).error(R.mipmap.pre_default_image).into(imgSingle);

    }
}
