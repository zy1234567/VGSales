package com.ztstech.vgmate.activitys.org_follow.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity;
import com.ztstech.vgmate.activitys.org_follow.claim_org.ClaimOrgDetailActivity;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;

/**
 * @author smm
 * @date 2017/11/14
 */

public class OrgFollowViewHolder extends SimpleViewHolder<OrgFollowlistBean.ListBean> {
    /**
     * 认领00 登记01
     */
    public static final String CLAIM_ORG_TYPE = "00";
    public static final String ADD_ORG_TYPE = "01";
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.img_org)
    ImageView imgOrg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_otype)
    TextView tvOtype;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.body)
    LinearLayout body;

    private int index;

    public OrgFollowViewHolder(View itemView, int index) {
        super(itemView);
        this.index = index;
    }

    @Override
    protected void refreshView(final OrgFollowlistBean.ListBean data) {
        super.refreshView(data);
        tvName.setText(data.rbioname);
        Glide.with(getContext())
                .load(data.rbilogosurl)
                .error(R.mipmap.ic_launcher)
                .into(imgOrg);
        tvOtype.setText(CategoryUtil.findCategoryByOtype(data.rbiotype));
        tvAddress.setText(LocationUtils.getPName(data.rbiprovince).concat(LocationUtils.getCName(data.rbicity)).
                concat(LocationUtils.getAName(data.rbidistrict))
                .concat(data.rbiaddress));
        String[] strs = new String[] {"咨询电话：",data.rbicontphone};
        int[] colors = new int[] {ContextCompat.getColor(getContext(), R.color.color_102),
                ContextCompat.getColor(getContext(), R.color.color_004)};
        SpannableStringBuilder spannableStringBuilder =
                ViewUtils.getDiffColorSpan(null, strs, colors);
        tvPhone.setText(spannableStringBuilder);

        if (index == GetOrgFollow.STATUS_INDEX_CONCERN){
            tvStatus.setText("已确认");
            tvFrom.setText("来源：".concat(data.comefrom));
        }else if (index == GetOrgFollow.STATUS_INDEX_CLAIM){
            tvStatus.setText("已认领");
            tvFrom.setText("来源：".concat(data.comefrom));
        }else if (index == GetOrgFollow.STATUS_INDEX_MANAGER){
            // 管理端
            tvStatus.setText("");
            tvFrom.setText("来源：".concat(data.comefrom));
        }else {
            // 机构反馈
            if (TextUtils.equals(data.type,CLAIM_ORG_TYPE)){
                tvFrom.setText("认领-待审批");
            }else if (TextUtils.equals(data.type,ADD_ORG_TYPE)){
                tvFrom.setText("登记-待审批");
            }
            tvStatus.setText(TimeUtils.informationTime(data.rbicreatetime));

            tvFrom.setTextColor(getContext().getResources().getColor(R.color.color_006));
        }
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == GetOrgFollow.STATUS_INDEX_FEEDBACK){
                    // 如果是待审批列表跳转至审批详情
                    Intent intent = new Intent(getContext(), ClaimOrgDetailActivity.class);
                    intent.putExtra(ClaimOrgDetailActivity.KEY_BEAN,data);
                    getContext().startActivity(intent);
                }else {
                    // 不是审批列表跳转至机构详情
                    Intent intent = new Intent(getContext(), OrgDetailV2Activity.class);
                    intent.putExtra(OrgDetailV2Activity.ARG_RBIID,data.rbiid);
                    intent.putExtra(OrgDetailV2Activity.ARG_RBIONAMW,data.rbioname);
                    intent.putExtra(OrgDetailV2Activity.ARG_ORGID,data.orgid);
                    getContext().startActivity(intent);
                }
            }
        });
        //拨打电话
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+data.rbicontphone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
    }



}
