package com.ztstech.vgmate.activitys.org_follow.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;

/**
 * @author smm
 * @date 2017/11/14
 */

public class OrgFollowViewHolder extends SimpleViewHolder<OrgFollowlistBean.ListBean> {

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
        tvFrom.setText("来源：".concat(data.comefrom));
        tvOtype.setText(CategoryUtil.findCategoryByOtype(data.rbiotype));
        tvAddress.setText(LocationUtils.getCName(data.rbicity).
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
        }else if (index == GetOrgFollow.STATUS_INDEX_CLAIM){
            tvStatus.setText("已认领");
        }else {
            tvStatus.setText("");
        }
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
