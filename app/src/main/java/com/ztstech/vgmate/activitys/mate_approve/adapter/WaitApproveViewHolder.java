package com.ztstech.vgmate.activitys.mate_approve.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.user_info.edit_info.EditInfoActivity;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.SexUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.data;

/**
 * Created by smm on 2017/11/13.
 */

public class WaitApproveViewHolder extends SimpleViewHolder<WaitApproveMateListBean.ListBean> {

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_age)
    ImageView imgAge;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.age_layout)
    LinearLayout ageLayout;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_duty_name)
    TextView tvDutyName;
    @BindView(R.id.tv_intro_name)
    TextView tvIntroName;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    MyClickListener clickListener;

    public WaitApproveViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(WaitApproveMateListBean.ListBean data) {
        super.refreshView(data);
        if (clickListener == null){
            clickListener = new MyClickListener();
        }
        tvDetail.setOnClickListener(clickListener);
        tvPhone.setOnClickListener(clickListener);
        tvName.setText(data.uname);
        tvPhone.setText(data.phone);
        tvAge.setText(String.valueOf(data.age));
        tvDutyName.setText(data.responsible);
        tvIntroName.setText(data.fname);
        Glide.with(getContext())
                .load(data.picurl)
                .error(R.mipmap.ic_launcher)
                .into(imgHead);
       if ("01".equals(data.sex)){
           imgAge.setImageResource(R.mipmap.space_man);
           ageLayout.setBackgroundResource(R.drawable.zts_alter_sex_man);
       }else {
           imgAge.setImageResource(R.mipmap.space_woman);
           ageLayout.setBackgroundResource(R.drawable.zts_alter_sex_women);
       }
    }


    class MyClickListener implements View.OnClickListener{

        WaitApproveMateListBean.ListBean bean;

        @Override
        public void onClick(View v) {
            if (v == tvDetail){
                Intent intent = new Intent(getContext(), EditInfoActivity.class);
                intent.putExtra(EditInfoActivity.SHOW_TYPE, EditInfoActivity.FROM_APPROVE_MATE);
                getContext().startActivity(intent);
            }else if (v == tvPhone){
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:".concat("15901088314")));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        }
    }

}
