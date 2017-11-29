package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.RelativeLayout;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.weigets.MyGridView;

import butterknife.BindView;

/**
 *
 * @author smm
 * @date 2017/11/27
 * 多张纯图片
 */

public class PicsViewHolder extends BaseShareViewHolder {

    @BindView(R.id.line)
    View line;
    @BindView(R.id.gv_img)
    MyGridView gvImg;

    public PicsViewHolder(View itemView,ClickCallback callback) {
        super(itemView,callback);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
        ShareFragmentPicAdapter adapter = new ShareFragmentPicAdapter(getContext(), data, getPosition());
        gvImg.setAdapter(adapter);
        final String imgs[] = data.contentpicsurl.split(",");
//        initQuanWen(holder, position, bean.getSummary());
//        holder.tvQuanwen.setOnClickListener(new MyClickListener(position, holder));
        setGridViewWidth(imgs, gvImg);
    }
}
