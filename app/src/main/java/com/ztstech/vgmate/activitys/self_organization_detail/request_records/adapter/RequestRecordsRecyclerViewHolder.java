package com.ztstech.vgmate.activitys.self_organization_detail.request_records.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.request_record_detail.RequestRecordDialogActivity;
import com.ztstech.vgmate.base.SimpleViewHolder;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/9/5.
 */

public class RequestRecordsRecyclerViewHolder extends SimpleViewHolder<String> {

    @BindView(R.id.tv_full)
    TextView tvDetail;

    public RequestRecordsRecyclerViewHolder(View itemView) {
        super(itemView);
        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), RequestRecordDialogActivity.class));
            }
        });
    }
}
