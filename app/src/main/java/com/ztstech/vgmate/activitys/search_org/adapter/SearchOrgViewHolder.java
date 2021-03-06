package com.ztstech.vgmate.activitys.search_org.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;

/**
 * Created by smm on 2017/12/8.
 */

public class SearchOrgViewHolder extends SimpleViewHolder<OrgFollowlistBean.ListBean> {

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

    private String keyword;

    public SearchOrgViewHolder(View itemView, String keyword,SearchOrgAdapter adapter) {
        super(itemView,adapter);
        this.keyword = keyword;
    }

    @Override
    protected void refreshView(final OrgFollowlistBean.ListBean data) {
        super.refreshView(data);
        ViewUtils.setKeyWordLight(keyword,data.rbioname,tvName);
        Glide.with(getContext())
                .load(data.rbilogosurl)
                .error(R.mipmap.pre_default_image)
                .into(imgOrg);
        tvOtype.setText(CategoryUtil.findCategoryByOtype(data.rbiotype));
        tvAddress.setText(data.rbiaddress);
        String[] strs = new String[] {"咨询电话：",data.rbicontphone == null ? "暂无" : data.rbicontphone};
        int[] colors = new int[] {ContextCompat.getColor(getContext(), R.color.color_102),
                ContextCompat.getColor(getContext(), R.color.color_004)};
        SpannableStringBuilder spannableStringBuilder =
                ViewUtils.getDiffColorSpan(null, strs, colors);
        tvPhone.setText(spannableStringBuilder);

        //拨打电话
        if (!TextUtils.isEmpty(data.rbicontphone)) {
            tvPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + data.rbicontphone));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);
                }
            });
        }
    }

}
