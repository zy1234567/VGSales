package com.ztstech.vgmate.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ztstech.vgmate.R;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/9/23.
 */

public class SimpleLoadingFooterHolder extends SimpleViewHolder<Object> {

    @BindView(R.id.tv_loading)
    TextView tvLoading;

    public SimpleLoadingFooterHolder(View itemView) {
        super(itemView);
    }

}
