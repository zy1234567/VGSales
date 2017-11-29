package com.ztstech.vgmate.activitys.share.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.photo_browser.PhotoBrowserActivity;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.ViewUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *
 * @author smm
 * @date 2017/11/27
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
    protected void refreshView(final ShareListBean.ListBean data) {
        super.refreshView(data);
        tvContent.setText(data.content);
        String url = ViewUtils.setSingleImageSize(getContext(),data.contentpicsurl,imgSingle);
        Glide.with(getContext()).load(url).error(R.mipmap.pre_default_image).into(imgSingle);
        imgSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhotoBrowserActivity.class);
                intent.putExtra(PhotoBrowserActivity.KEY_POSITION,0);
                intent.putStringArrayListExtra(PhotoBrowserActivity.KEY_LIST,
                        (ArrayList<String>) CommonUtil.imgUrlsToList(data.contentpicurl));
                getContext().startActivity(intent);
            }
        });
        initQuanWen(data,tvQuanwen, tvContent,getPosition(), data.content);
    }
}
