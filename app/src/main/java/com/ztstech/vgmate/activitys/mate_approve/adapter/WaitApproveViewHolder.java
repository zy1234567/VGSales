package com.ztstech.vgmate.activitys.mate_approve.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.TimeUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

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
    @BindView(R.id.tv_time)
    TextView tvTime;

    private MyClickListener clickListener;

    private ClickDetailCallBack callBack;


    public WaitApproveViewHolder(View itemView,ClickDetailCallBack callBack) {
        super(itemView);
        this.callBack = callBack;
    }

    @Override
    protected void refreshView(WaitApproveMateListBean.ListBean data) {
        super.refreshView(data);
        if (clickListener == null){
            clickListener = new MyClickListener();
        }
        clickListener.bean = data;
        tvDetail.setOnClickListener(clickListener);
        tvPhone.setOnClickListener(clickListener);
        tvName.setText(data.uname);
        tvPhone.setText(data.phone);
        tvAge.setText(String.valueOf(data.age));
        tvDutyName.setText(data.responsible);
        tvIntroName.setText(data.fname);
        if (!TextUtils.isEmpty(data.lastuptime)){
            tvTime.setText(TimeUtils.informationTime(data.lastuptime));
            tvTime.setVisibility(View.VISIBLE);
        }else {
            tvTime.setVisibility(View.GONE);
        }

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
                callBack.clickDetail(bean.uid);
            }else if (v == tvPhone){
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:".concat(bean.phone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        }
    }

    public interface ClickDetailCallBack{
        void clickDetail(String saleid);
    }

}
