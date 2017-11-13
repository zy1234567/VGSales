package com.ztstech.vgmate.activitys.main_fragment.subview.notice.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.model.notice.NoticeModel;
import com.ztstech.vgmate.utils.DialogUtils;

/**
 * Created by zhiyuan on 2017/8/15.
 */

public class NoticeRecyclerAdapter extends SimpleRecyclerAdapter<MainListBean.ListBean> {

    /** 长按回调 */
    DialogUtils.EditInfoCallBack callBack;

    public NoticeRecyclerAdapter(DialogUtils.EditInfoCallBack callBack){
        this.callBack = callBack;
    }
    @Override
    public SimpleViewHolder<MainListBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notice, parent, false),callBack);
    }
}
