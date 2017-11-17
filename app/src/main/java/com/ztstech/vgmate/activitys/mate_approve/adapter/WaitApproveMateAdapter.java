package com.ztstech.vgmate.activitys.mate_approve.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

/**
 *
 * @author smm
 * @date 2017/11/13
 */

public class WaitApproveMateAdapter extends SimpleRecyclerAdapter<WaitApproveMateListBean.ListBean> {

    private WaitApproveViewHolder.ClickDetailCallBack callBack;

    public WaitApproveMateAdapter(WaitApproveViewHolder.ClickDetailCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public WaitApproveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WaitApproveViewHolder((LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wait_approve_mate, parent, false)),callBack);
    }

}
