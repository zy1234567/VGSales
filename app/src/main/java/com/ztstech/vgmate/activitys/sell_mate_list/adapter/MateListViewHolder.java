package com.ztstech.vgmate.activitys.sell_mate_list.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.org_follow.OrgFollowActivity;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.MatelistBean;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.TimeUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by smm on 2017/11/23.
 */

public class MateListViewHolder extends SimpleViewHolder<MatelistBean.ListBean> {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_degree)
    TextView tvDegree;
    @BindView(R.id.tv_day)
    TextView tvDay;
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
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_duty_name)
    TextView tvDutyName;
    @BindView(R.id.tv_intro_name)
    TextView tvIntroName;
    @BindView(R.id.tv_num_org)
    TextView tvNumOrg;
    @BindView(R.id.ll_org_follow)
    LinearLayout llOrgFollow;
    @BindView(R.id.tv_num_invite_mate)
    TextView tvNumInviteMate;
    @BindView(R.id.ll_invite_mate)
    LinearLayout llInviteMate;
    @BindView(R.id.tv_num_mate_follow)
    TextView tvNumMateFollow;
    @BindView(R.id.ll_mate_follow)
    LinearLayout llMateFollow;
    @BindView(R.id.tv_num_money)
    TextView tvNumMoney;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_states_type)
    TextView tvStatesType;

    public MateListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(final MatelistBean.ListBean data) {
        super.refreshView(data);
        tvName.setText(data.uname);
        Glide.with(getContext())
                .load(data.picsurl)
                .error(R.mipmap.ic_launcher)
                .into(imgHead);
        tvAge.setText(CommonUtil.calculateAgeByBirth(data.birthday));
        if ("01".equals(data.sex)){
            imgAge.setImageResource(R.mipmap.space_man);
            ageLayout.setBackgroundResource(R.drawable.zts_alter_sex_man);
        }else {
            imgAge.setImageResource(R.mipmap.space_woman);
            ageLayout.setBackgroundResource(R.drawable.zts_alter_sex_women);
        }
        tvPhone.setText(data.phone);
        tvAddress.setText(LocationUtils.getFormedString(data.district));
        tvTime.setText("最近登录:".concat(TimeUtils.informationTime(data.lastlogintime)));
        /** 责任人 */
        tvDutyName.setText(data.principal);
        /** 介绍人 */
        tvIntroName.setText(data.oname);
        tvDegree.setText("周登录:".concat(String.valueOf(data.weeklogdays).concat("次")));
        tvDay.setText("成为伙伴:".concat(String.valueOf(data.todays).concat("天")));
        /** 客户跟进 */
        tvNumOrg.setText(String.valueOf(data.budgets).concat("家"));
        llOrgFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrgFollowActivity.class);
                intent.putExtra(OrgFollowActivity.KEY_UID,data.uid);
                getContext().startActivity(intent);
            }
        });
        /** 邀请伙伴 */
        tvNumInviteMate.setText(String.valueOf(data.firstcnt).concat("人"));
        /** 伙伴跟进 */
        tvNumMateFollow.setText(String.valueOf(data.firstbudgets).concat("人"));
        /** 预计收入 */
        tvNumMoney.setText("¥".concat(String.valueOf(data.maxmoney)));
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:".concat(data.phone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        if (TextUtils.equals(data.status, Constants.PASS_TYPE)){
            tvStatesType.setText("已通过");
            tvStatesType.setTextColor(getContext().getResources().getColor(R.color.weilai_color_1105));
        }else if (TextUtils.equals(data.status, Constants.USER_ID_CHECKING) || TextUtils.equals(data.status, Constants.USER_ID_WILL_CHECK)){
            tvStatesType.setText("审核中");
            tvStatesType.setTextColor(getContext().getResources().getColor(R.color.color_006));
        }else{
            tvStatesType.setText("已拒绝");
            tvStatesType.setTextColor(getContext().getResources().getColor(R.color.color_006));
        }
    }
}
