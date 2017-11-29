package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.weigets.MyGridView;

import butterknife.BindView;

/**
 *
 * @author smm
 * @date 2017/11/27
 * 多张图片带文字
 */

public class PicsAndWordViewHolder extends BaseShareViewHolder {

    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_quanwen)
    TextView tvQuanwen;
    @BindView(R.id.gv_img)
    MyGridView gvImg;


    public PicsAndWordViewHolder(View itemView,ClickCallback callback) {
        super(itemView,callback);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        ShareFragmentPicAdapter adapter = new ShareFragmentPicAdapter(getContext(), data, getPosition());
        gvImg.setAdapter(adapter);
        tvContent.setText(data.content);
        final String imgs[] = data.contentpicsurl.split(",");
        initQuanWen(data,tvQuanwen, tvContent,getPosition(), data.content);

        setGridViewWidth(imgs, gvImg);
        super.refreshView(data);
    }



}
