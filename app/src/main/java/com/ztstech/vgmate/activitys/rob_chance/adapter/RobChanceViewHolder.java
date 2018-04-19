package com.ztstech.vgmate.activitys.rob_chance.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.TimeUtils;

import org.w3c.dom.Text;

import butterknife.BindView;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChanceViewHolder extends SimpleViewHolder<RobChanceBean.ListBean> {

    @BindView(R.id.tv_add_type)
    TextView tvAddType;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_rob_type)
    TextView tvRobType;
    @BindView(R.id.img_org)
    ImageView imgOrg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_linkman)
    TextView tvLinkman;
    @BindView(R.id.tv_service)
    TextView tvService;
    private static final int PASSER_CHECK_IN = 11;
    private static final int ORG_CHECK_IN_OR_CALIM = 12;
    public RobChanceViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(final RobChanceBean.ListBean data) {
        super.refreshView(data);
        CommonUtil.orgfFromType(getContext(),tvAddType,data.cstatus,data.nowchancetype,data.chancetype);
        Glide.with(getContext())
                .load(data.rbilogosurl)
                .error(R.mipmap.ic_launcher)
                .into(imgOrg);
        tvTime.setText(TimeUtils.informationTime(data.createtime));
        tvName.setText(data.rbioname);
        tvAddress.setText("地区：".concat(data.rbiaddress));
        if (!TextUtils.isEmpty(data.contractname) && !TextUtils.isEmpty(data.contractphone)) {
            tvLinkman.setText("联系人：".concat(data.contractname).concat(data.contractphone));
        }else{
            tvLinkman.setText("联系人：暂无");
        }
        tvService.setText("客服：".concat(data.marketname));
        if (TextUtils.equals(data.locktype, Constants.LOCK_YES)) {
            tvRobType.setText("锁定中");
            tvRobType.setBackgroundResource(R.drawable.bg_c_1_f_002);
        }else {
            tvRobType.setText("抢单");
            tvRobType.setBackgroundResource(R.drawable.bg_c_1_f_006);
        }
        tvRobType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (identity(data.cstatus,data.nowchancetype,data.chancetype) == PASSER_CHECK_IN){
//                    DialogUtils.showdialogbottomtwobutton(this,"抢单","取消",)
                }
            }
        });
    }
    //判断是路人登记还是机构登记认领的
    private int identity(String cstatus,String nowchancetype, String chancetype){
        //返回路人
        if (TextUtils.equals(cstatus,Constants.CSTATUS_GRAY_UNVERIFIED) &&
                (TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_PASSER_CHECK_IN) ||
                TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_PASSER_CHECK_IN)) &&
                TextUtils.isEmpty(nowchancetype)){
            return PASSER_CHECK_IN;
        }
        if (TextUtils.equals(cstatus,Constants.CSTATUS_GRAY_UNVERIFIED) && TextUtils.isEmpty(nowchancetype)
                && (TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_ORG_REGISTER) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_ORG_REGISTER) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_ORG_CHECK_IN) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_ORG_CHECK_IN) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_MAP_REGISTER))){
            return ORG_CHECK_IN_OR_CALIM;
        }
        return PASSER_CHECK_IN;
    }
}
