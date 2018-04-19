package com.ztstech.vgmate.activitys.rob_chance.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.rob_chance.RobChancePresenter;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.RobChanceBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChanceAdapter extends SimpleRecyclerAdapter<RobChanceBean.ListBean> {
    RobChanceViewHolder.lockorgCallBack callBack;
    public RobChanceAdapter(RobChanceViewHolder.lockorgCallBack callBack){
        this.callBack = callBack;
    }
    @Override
    public SimpleViewHolder<RobChanceBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RobChanceViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_rob_chance, parent, false),callBack);
    }
}
