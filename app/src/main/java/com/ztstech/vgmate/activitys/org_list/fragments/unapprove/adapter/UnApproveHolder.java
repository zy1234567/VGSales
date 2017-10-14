package com.ztstech.vgmate.activitys.org_list.fragments.unapprove.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.OrglistUnApproveBean;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/10/14.
 */

public class UnApproveHolder extends SimpleViewHolder<OrglistUnApproveBean.ListBean> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_header_location)
    TextView tvHeaderLocation;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_repeat)
    TextView tvRepeat;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.img)
    ImageView img;

    private String id;

    public UnApproveHolder(View itemView,
                           @Nullable SimpleRecyclerAdapter<OrglistUnApproveBean.ListBean> adapter) {
        super(itemView, adapter);

        tvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id != null) {
                    CommonUtil.copy(id, getContext());
                    ToastUtil.toastCenter(getContext(), "ID已成功复制到剪切板");
                }
            }
        });
    }


    @Override
    protected void refreshView(OrglistUnApproveBean.ListBean data) {
        super.refreshView(data);

        id = String.valueOf(data.rbiid);
        tvTitle.setText(data.oname);
        tvFrom.setText("来源：" + data.comefrom);
        tvHeaderLocation.setText(data.rbiaddress);
        tvTag.setText(data.rbiotype);
        tvLocation.setText(data.rbiaddress);
        tvPhone.setText("咨询电话：" + data.phone);
        tvId.setText(String.valueOf(data.rbiid));

        tvRepeat.setVisibility(View.INVISIBLE);

        Glide.with(getContext()).load(data.rbilogosurl).into(img);

    }
}
