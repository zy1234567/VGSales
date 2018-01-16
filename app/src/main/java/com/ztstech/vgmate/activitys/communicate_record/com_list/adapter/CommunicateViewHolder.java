package com.ztstech.vgmate.activitys.communicate_record.com_list.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.communicate_record.add_communcate.AddComRecordActivity;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.GetComRecordBean;

import butterknife.BindView;

/**
 * Created by smm on 2018/1/11.
 */

public class CommunicateViewHolder extends SimpleViewHolder<GetComRecordBean.ListBean> {
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.img_point)
    ImageView imgPoint;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.rl_point)
    RelativeLayout rlPoint;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_way)
    TextView tvWay;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_manage)
    TextView tvManage;
    @BindView(R.id.tv_next_visit)
    TextView tvNextVisit;
    @BindView(R.id.tv_com_record)
    TextView tvComRecord;
    @BindView(R.id.tv_follow_way)
    TextView tvFollowWay;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;

    public CommunicateViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(GetComRecordBean.ListBean data) {
        super.refreshView(data);
        if (data == null){
            return;
        }
        if (TextUtils.equals(data.communicationtype, AddComRecordActivity.COMMUN_TYPE_PHONE)){
            tvWay.setText("电话咨询");
            tvContact.setText(data.contactsname+"("+data.rbicontphone+")");
            if (TextUtils.equals(data.roletype,AddComRecordActivity.IDEN_BOSS)){
                tvJob.setText("机构法人/老板/店长");
            }else if (TextUtils.equals(data.roletype,AddComRecordActivity.IDEN_MANAGER)){
                tvJob.setText("一般管理人员");
            }else{
                tvJob.setText("其他机构人员");
            }
            if (TextUtils.equals(data.backstage,AddComRecordActivity.BACKSTAGE_NO)){
                tvManage.setText("暂无");
            }else{
                tvManage.setText("已有");
            }
            if (TextUtils.equals(data.callon,AddComRecordActivity.CALLON_NO)){
                tvNextVisit.setText("未约");
            }else{
                tvNextVisit.setText("已约");
            }
            if (TextUtils.isEmpty(data.msg)){
                tvComRecord.setText("暂无");
            }else{
                tvComRecord.setText(data.msg);
            }
            if (TextUtils.equals(data.followtype,AddComRecordActivity.FLLOW_UP_TYPE_ACCELERATE)){
                tvFollowWay.setText("加速跟进（意向好，也迫切需要我们的产品）");
            }else if (TextUtils.equals(data.followtype,AddComRecordActivity.FLLOW_UP_TYPE_NORMAL)){
                tvFollowWay.setText("正常跟进（意向一般，但是肯定需要我们的产品）");
            }else if (TextUtils.equals(data.followtype,AddComRecordActivity.FLLOW_UP_TYPE_CONYINUED)){
                tvFollowWay.setText("长期跟进（意向一般或者差，而且不需要管理后台）");
            }else if (TextUtils.equals(data.followtype,AddComRecordActivity.FLLOW_UP_TYPE_NO)){
                tvFollowWay.setText("暂不跟进（意向差，有管理后台，最后再跟进此类客户）");
            }else{
                tvFollowWay.setText("暂无");
            }
            tvTime.setText(data.createtime);
        }else{
            tvWay.setText("上门拜访");
            llPhone.setVisibility(View.GONE);
        }

    }
}
